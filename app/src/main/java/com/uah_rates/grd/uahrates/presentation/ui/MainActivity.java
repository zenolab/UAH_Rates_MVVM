package com.uah_rates.grd.uahrates.presentation.ui;

import android.content.Intent;
import android.content.res.Configuration;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.presentation.ui.halpers.LocalStorage;
import com.uah_rates.grd.uahrates.presentation.ui.pager.HostFragment;
import com.uah_rates.grd.uahrates.presentation.ui.screens.*;
import com.uah_rates.grd.uahrates.presentation.ui.screens.dialog.InfoDialogFragment;
import com.uah_rates.grd.uahrates.presentation.ui.halpers.settings.SettingsFragment;

public  class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();
    private Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new LocalStorage(this);

    }//End onCreate()

    private void mainView() {

        fragment = new AllRatesFragmentView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragment = new HostFragment();
            setTitle("Currencies/Metals");
        } else if (id == R.id.nav_gallery) {
            fragment = new AllRatesFragmentView();
            setTitle("All quotes");
        } else if (id == R.id.nav_slideshow) {
             fragment = new XDRFragmentView();
            setTitle("XDR");
        } else if (id == R.id.nav_bond) {
            setTitle("Bond");
            fragment = new BondFragment();
        } else if (id == R.id.nav_manage) {
            fragment = new SettingsFragment();
            setTitle("Menu");
        } else if (id == R.id.nav_share) {
            fragment = new InfoAppFragmentView();
            setTitle("Info");
        } else if (id == R.id.nav_send) {
            setTitle("Google Play");
            fragment = new PlayStoreFragmentView();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.e("LOG_TAG", "Error in creating fragment");
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_info) {

            AppCompatDialogFragment infoDialog = new InfoDialogFragment();
            infoDialog.show(getSupportFragmentManager(), "Informer");
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
        mainView();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected  void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(LOG_TAG, "onActivityResult ");
    }

    /** https://developer.android.com/guide/topics/resources/runtime-changes?hl=ru
     *  https://developer.android.com/guide/topics/resources/runtime-changes?hl=uk
     *  Required to install attribute for work!
     *  android:configChanges="orientation|keyboardHidden|screenSize in Manifest.xml
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(LOG_TAG, "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d(LOG_TAG, "portrait");
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG_TAG, "onBackPressed()");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}