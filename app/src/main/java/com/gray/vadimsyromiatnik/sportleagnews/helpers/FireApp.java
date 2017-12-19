package com.gray.vadimsyromiatnik.sportleagnews.helpers;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by vadim on 19.12.17.
 */

public class FireApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
