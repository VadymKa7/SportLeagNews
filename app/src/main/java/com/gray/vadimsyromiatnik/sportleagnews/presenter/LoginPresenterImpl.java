package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gray.vadimsyromiatnik.sportleagnews.App;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.interactor.LoginInteractor;
import com.gray.vadimsyromiatnik.sportleagnews.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


import javax.inject.Inject;


public class LoginPresenterImpl extends MvpBasePresenter<LoginView> implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private LoginInteractor interactor;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Inject
    LoginPresenterImpl(LoginInteractor interactor) {
        this.interactor = interactor;
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }
    
    @Override
    public void requestData() {
        ifViewAttached(view -> {
            view.showToast(interactor.getMessage());
        });
    }

    // sign in
    @Override
    public void signInWithEmailAndPassword(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d(TAG, "createUserWithEmail:success");
                    ifViewAttached(LoginView::intentMainActivity);
                    currentUser = mAuth.getCurrentUser();
                } else {

                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    ifViewAttached((LoginView view) -> {
                        view.showToast(App.getInstance().getString(R.string.text_error_login_password));
                    });
                }
            }
        });
    }

    // registration
    @Override
    public void createUserWithEmailAndPassword(String email, String password) {
        //final ProgressDialog progressDialog = ProgressDialog.show(this, "Wait", "request_send", true);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              //  progressDialog.dismiss();
                if(task.isSuccessful()) {
                    ifViewAttached(LoginView::intentChooseCommandActivity);
                }else {
                    ifViewAttached((LoginView view) -> {
                        view.showToast(App.getInstance().getString(R.string.text_error_login_password));
                    });

                }
            }
        });
    }

}
