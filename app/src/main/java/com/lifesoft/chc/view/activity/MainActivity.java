package com.lifesoft.chc.view.activity;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.database.engine.DBEngine;
import com.lifesoft.chc.listener.CCFilter;
import com.lifesoft.chc.utils.CCAnimation;
import com.lifesoft.chc.utils.NetworkUtils;
import com.lifesoft.chc.utils.Permissions;
import com.lifesoft.chc.view.fragment.AllSmsFragment;
import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.SmsObject;
import com.lifesoft.chc.viewmodel.CreatedTransactionVM;
import com.lifesoft.chc.viewmodel.PostSmsDataVM;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static String TAG = MainActivity.class.getName();
    // Objects
    private CreatedTransactionVM createdTransactionVM;
    private PostSmsDataVM smsDataVM;
    private SmsObject smsObject;
    private DBEngine engine;
    private Permissions permissions;
    private FragmentTransaction fragmentTransaction;
    private CCFilter ccFilter;
    // Views
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView fab;
    private ActionBarDrawerToggle toggle;
    private Window window;
    private TextView dialogCancel;
    private TextView dialogApply;
    private Spinner spinnerSuccess;
    private ProgressBar progressBar;
    // Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        initViews();
        //change window and navigation bar color
        window = getWindow();
        windowConfiguration(window);

        // change toolbar color and title
        toolbarConfiguration(toolbar);
        fab.setOnClickListener(view -> showCCFilterDialog());

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // click drawer item
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.sms_info:
                    createFragment(R.id.mainContainer, new AllSmsFragment());
                    navigationView.setSelected(true);
                    drawer.closeDrawers();
                    break;
            }
            return false;
        });

        //TODO: need optimization
        NetworkUtils.isConnected(this);
        permissions = new Permissions(MainActivity.this);
        permissions.connectSMSPermissions();
        smsObject = SmsObject.INSTANCE();
        engine = DBEngine.INSTANCE();
        createViewModels();
        Bundle extras = getIntent().getExtras();
        listenSmsBroadcastReceiver(extras);
        getNetworkData();
    }

    private synchronized void listenSmsBroadcastReceiver(Bundle bundle) {
        if (bundle != null && getSmsModel() != null) {
            String isNetworkAvailable = (String) bundle.get(AppConstants.IS_NETWORK_AVAILABLE);
            if (isNetworkAvailable != null) {
                if (isNetworkAvailable.equals("true")) {
                    // request sms model in server
                    postSmsData(getSmsModel());
                } else if (isNetworkAvailable.equals("false")) {
                    //save sms model in database
                    engine.getServices(this).save(getSmsModel());
                }
            }
        }
    }

    private void initViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (ImageView) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        progressBar = findViewById(R.id.progressBarID);
    }

    private void initDialogViews(View view) {
        dialogCancel = view.findViewById(R.id.cancel_dialogID);
        dialogApply = view.findViewById(R.id.apply_dialogID);
        spinnerSuccess = (Spinner) view.findViewById(R.id.planets_spinner);
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
        smsDataVM.postRequest(smsModel);
    }

    public SmsModel getSmsModel() {
        return SmsObject.INSTANCE().getSmsModel();
    }

    private void createViewModels() {
        createdTransactionVM = ViewModelProviders.of(this).get(CreatedTransactionVM.class);
        smsDataVM = new PostSmsDataVM();
    }

    private void getNetworkData() {
        createdTransactionVM.getModelTrasactionsMutableLiveData().observe(MainActivity.this, appResponse -> {
            if (appResponse != null) {
                Log.i(TAG, "onChanged: Successfully");
                smsObject.setCcTransactions(appResponse);
                createFragment(R.id.mainContainer, new AllSmsFragment());
            } else {
                Log.i(TAG, "onChanged: Successfully");
            }
        });
    }

    private void toolbarConfiguration(Toolbar toolbar) {
        toolbar.setTitle(AppConstants.APP_NAME);
        toolbar.setBackgroundColor(getResources().getColor(R.color.action_bar));
    }

    private void windowConfiguration(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.window));
            window.setNavigationBarColor(getResources().getColor(R.color.action_bar));
        }
    }

    private void showCCFilterDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_filter, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        //init views
        initDialogViews(dialogView);
        //spinner on click
        spinnerSuccess.setOnItemSelectedListener(this);
        //init spinner adapter
        initSpinnerAdapter();
        //dialog animation
        CCAnimation.dialogShowAnimation(this, dialogView, R.anim.alert_dialog_filter);
        //show dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        //tap dialog view
        dialogCancel.setOnClickListener(v -> alertDialog.dismiss());
        dialogApply.setOnClickListener(v -> {
            alertDialog.dismiss();
            ccFilter = new CCFilter(this);
            progressBar.setVisibility(View.VISIBLE);
            ccFilter.sortSuccess(smsObject.getCcTransactions(), true);
//            if (transaction !=null){
//                progressBar.setVisibility(View.INVISIBLE);
//                Fragment fragment = new AllSmsFragment();
//                Bundle bundle = new Bundle();
//
//                fragment.setArguments(bundle);
//            }
        });
    }

    private void initSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSuccess.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
