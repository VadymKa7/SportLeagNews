package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gray.vadimsyromiatnik.sportleagnews.ui.presenter.SplashPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.ui.presenter.SplashPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.ui.view.SplashView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;


public class SplashActivity extends MvpActivity<SplashView, SplashPresenter> implements SplashView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().addAuthStateListener();

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
}
