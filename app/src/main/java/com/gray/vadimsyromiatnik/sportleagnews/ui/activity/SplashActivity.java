package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.SplashPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.SplashPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.SplashView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class SplashActivity extends MvpActivity<SplashView, SplashPresenter> implements SplashView, EasyPermissions.PermissionCallbacks {


    String[] perms = {ACCESS_FINE_LOCATION, READ_CONTACTS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getPresenter().addAuthStateListener();
        requiresAllPermission();
    }



    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenterImpl();
    }

    @Override
    public void intentMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void intentLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        requiresAllPermission();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        requiresAllPermission();
    }

    private void requiresAllPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            getPresenter().addAuthStateListener();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permission_dialog_text), 0,  perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
