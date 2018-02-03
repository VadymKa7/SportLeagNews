package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import com.gray.vadimsyromiatnik.sportleagnews.AlarmActivity;
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


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.drawerLayoutMain)DrawerLayout drawerLayoutMain;
    FragmentManager mFragmentManager;
    @Inject
    MainPresenter mainPresenter;
    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        MainFragment mainF = new MainFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.mainFragment, mainF);
        ft.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navMainView);
        navigationView.setNavigationItemSelectedListener(this);

        setInitialDateTime();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.setting:
                TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        dateAndTime.set(Calendar.MINUTE, minute);
                        setInitialDateTime();
                    }
                };
                break;
            case R.id.alarm:
                startActivity(new Intent(this, AlarmActivity.class));
                break;

        }
        drawerLayoutMain.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setTime(View v) {
        new TimePickerDialog(MainActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }


    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };


    private void setInitialDateTime() {

       String  currentDateTime ;
        currentDateTime = DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);

        Log.d(TAG, "setInitialDateTime: " + currentDateTime);
    }


    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mainPresenter;
    }

}
