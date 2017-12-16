package com.gray.vadimsyromiatnik.sportleagnews;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.LoginPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.ui.view.LoginView;
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
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Inject LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getPresenter().requestData();
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @OnClick(R.id.btnSignInLogin)
    public void btnSignInLogin(Button btnSignInLogin){
        setBtnSignInLogin();
    }

    @OnClick(R.id.btnRegistrationLogin)
    public void setBtnRegistrationLogin(Button btnRegistrationLogin){
        setBtnRegistrationLogin();
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


    public void setBtnSignInLogin(){
        String email  = "vad1mvs.ua@mail.ru";
        String password = "password";
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    currentUser = mAuth.getCurrentUser();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void setBtnRegistrationLogin(){
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Wait", "request_send", true);

        mAuth.createUserWithEmailAndPassword("vad1mvs.ua@mail.ru", "password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Authentication success.", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "onComplete: "+ task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
