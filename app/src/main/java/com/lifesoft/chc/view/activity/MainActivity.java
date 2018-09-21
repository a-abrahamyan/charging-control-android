package com.lifesoft.chc.view.activity;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.model.CCTransactions;
import com.lifesoft.chc.utils.NetworkUtils;
import com.lifesoft.chc.utils.Permissions;
import com.lifesoft.chc.view.fragment.AllSmsFragment;
import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.SmsObject;
import com.lifesoft.chc.viewmodel.CreatedTransactionVM;
import com.lifesoft.chc.viewmodel.PostSmsDataVM;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName();
    // Objects
    private CreatedTransactionVM createdTransactionVM;
    private PostSmsDataVM smsDataVM;
    private SmsObject smsObject;
    private Permissions permissions;
    private FragmentTransaction fragmentTransaction;
    // Views
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBarDrawerToggle toggle;
    // Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        initViews();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // click drawer item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sms_info:
                        createFragment(R.id.mainContainer, new AllSmsFragment());
                        navigationView.setSelected(true);
                        drawer.closeDrawers();
                        break;
                }
                return false;
            }
        });

        //TODO: need optimization
        NetworkUtils.isConnected(this);
        permissions = new Permissions(MainActivity.this);
        permissions.connectSMSPermissions();
        smsObject = SmsObject.INSTANCE();
        createViewModels();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            SmsModel smsModel = (SmsModel) extras.getSerializable(AppConstants.SMS_OBJECT);
            if (smsModel != null) {
                synchronized (this) {
                    postSmsData(smsModel);
                }
            }
            if (getSmsModel() != null) {
                Log.i(TAG, "onCreate:---------> " + getSmsModel().toString());
            }
        }
        getNetworkData();
    }

    private void initViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createFragment(int container, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

    private void postSmsData(SmsModel smsModel) {
        smsDataVM.postRequset(smsModel);
    }

    public SmsModel getSmsModel() {
        return SmsObject.INSTANCE().getSmsModel();
    }

    private void createViewModels() {
        createdTransactionVM = ViewModelProviders.of(this).get(CreatedTransactionVM.class);
        smsDataVM = new PostSmsDataVM();
    }

    private void getNetworkData() {
        createdTransactionVM.getModelTrasactionsMutableLiveData().observe(MainActivity.this, new Observer<CCTransactions>() {
            @Override
            public void onChanged(@Nullable CCTransactions appResponse) {
                if (appResponse != null) {
                    Log.i(TAG, "onChanged: Successfully");
                    smsObject.setCcTransactions(appResponse);
                } else {
                    Log.i(TAG, "onChanged: Successfully");
                }
            }
        });
    }
}
