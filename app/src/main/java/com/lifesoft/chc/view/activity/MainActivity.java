package com.lifesoft.chc.view.activity;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.model.CCTransactions;
import com.lifesoft.chc.view.adapter.GeneralRecyclerViewAdapter;
import com.lifesoft.chc.view.fragment.AllSmsFragment;
import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.SmsObject;
import com.lifesoft.chc.viewmodel.CreatedTransactionVM;
import com.lifesoft.chc.viewmodel.PostSmsDataVM;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static String TAG = MainActivity.class.getName();
    private CreatedTransactionVM createdTransactionVM;
    private PostSmsDataVM smsDataVM;
    private SmsObject smsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//----------------------------------------------------------------------------------------------//
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction;
        if (id == R.id.sms_info) {
            // Handle the camera action
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, new AllSmsFragment());
            fragmentTransaction.commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
