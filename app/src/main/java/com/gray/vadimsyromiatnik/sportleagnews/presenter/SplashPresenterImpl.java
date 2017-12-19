package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gray.vadimsyromiatnik.sportleagnews.view.SplashView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


/**
 * Created by vadim on 13.12.17.
 */

public class SplashPresenterImpl extends MvpBasePresenter<SplashView> implements SplashPresenter {
    private static final String TAG = "SplashPresenterImpl";
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public SplashPresenterImpl() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void addAuthStateListener() {
        ifViewAttached(view -> {
            mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() != null){
                        Log.d(TAG, "onAuthStateChanged: success");
                        view.intentMainActivity();
                    }else {
                        view.intentLoginActivity();
                        Log.d(TAG, "onAuthStateChanged: ----");
                    }
                }
            });
        });
    }


}
