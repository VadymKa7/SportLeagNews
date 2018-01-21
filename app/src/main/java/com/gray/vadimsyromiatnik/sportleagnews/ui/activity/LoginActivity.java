package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
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
    @BindView(R.id.etEmailLogin) EditText etEmailLogin;
    @BindView(R.id.etPasswordLogin)EditText etPasswordLogin;
    @BindView(R.id.btnSignInLogin)Button btnSignInLogin;
    @BindView(R.id.btnRegistrationLogin)Button btnRegistrationLogin;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    CallbackManager callbackManager;
//    LoginButton  loginButton;
    @Inject
LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
//        loginButton = (LoginButton) findViewById(R.id.loginButton);
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                handelFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    toastMessage("Successfully signed in with: " + user.getEmail());
//                } else {
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    toastMessage("Successfully signed out.");
//                }
//            }
//        };
    }


//
//    private void handelFacebookAccessToken(AccessToken accessToken){
//        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
//        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(!task.isSuccessful()){
//                    Log.d(TAG, "Firebase Login Error");
//                }
//            }
//        });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        firebaseAuth.removeAuthStateListener(mAuthListener);
//    }



    @OnClick(R.id.btnSignInLogin)
    public void btnSignInLogin(Button btnSignInLogin){
        //getPresenter().signInWithEmailAndPassword(String.valueOf(etEmailLogin.getText()), String.valueOf(etPasswordLogin.getText()));
        getPresenter().signInWithEmailAndPassword(String.valueOf("bob@gmail.com"), String.valueOf("qwerty"));
    }

    @OnClick(R.id.btnRegistrationLogin)
    public void setBtnRegistrationLogin(Button btnRegistrationLogin){
        Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
        getPresenter().createUserWithEmailAndPassword(String.valueOf(etEmailLogin.getText()), String.valueOf(etPasswordLogin.getText()));
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
        startActivity(new Intent(this, ChooseLeagActivity.class));
        finish();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
