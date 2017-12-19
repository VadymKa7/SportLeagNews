package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.LoginPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.etLogin) EditText etLogin;
    @BindView(R.id.etPasswordLogin)EditText etPasswordLogin;
    @BindView(R.id.btnSignInLogin)Button btnSignInLogin;
    @BindView(R.id.btnRegistrationLogin)Button btnRegistrationLogin;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignInLogin)
    public void btnSignInLogin(Button btnSignInLogin){
        getPresenter().signInWithEmailAndPassword(String.valueOf(etLogin.getText()), String.valueOf(etPasswordLogin.getText()));
    }

    @OnClick(R.id.btnRegistrationLogin)
    public void setBtnRegistrationLogin(Button btnRegistrationLogin){
        getPresenter().createUserWithEmailAndPassword(String.valueOf(etLogin.getText()), String.valueOf(etPasswordLogin.getText()));
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showDialog() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Wait", "request_send", true);
    }

    @Override
    public void intentMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void intentChooseCommandActivity() {
        startActivity(new Intent(this, ChooseCommandActivity.class));
        finish();
    }

}
