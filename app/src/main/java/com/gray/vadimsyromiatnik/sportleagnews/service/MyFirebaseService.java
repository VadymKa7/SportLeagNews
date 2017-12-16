package com.gray.vadimsyromiatnik.sportleagnews.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by vadimsyromiatnik on 12/2/17.
 */

public class MyFirebaseService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseService";
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh: "+ refreshedToken);

    }

}
