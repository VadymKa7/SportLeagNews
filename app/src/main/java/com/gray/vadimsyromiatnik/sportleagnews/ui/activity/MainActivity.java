package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.gray.vadimsyromiatnik.sportleagnews.ListenerMainActivity;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.fragments.MainFragment;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener, ListenerMainActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.drawer_layout)DrawerLayout drawerLayoutMain;
    @BindView(R.id.nav_view)NavigationView navigationView;
    private FragmentManager mFragmentManager;
    @Inject MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        MainFragment mainF = new MainFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.mainFragment, mainF);
        ft.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.setting) {
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "in developing", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "in developing", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "in developing", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    private void setInitialDateTime() {
//
//       String  currentDateTime ;
//        currentDateTime = DateUtils.formatDateTime(this,
//                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
//
//        Log.d(TAG, "setInitialDateTime: " + currentDateTime);
//    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mainPresenter;
    }


    @Override
    public void onBackPressed() {

        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onOpenNavigation() {
        if (!drawerLayoutMain.isDrawerOpen(GravityCompat.START))
            drawerLayoutMain.openDrawer(GravityCompat.START);

    }
}
