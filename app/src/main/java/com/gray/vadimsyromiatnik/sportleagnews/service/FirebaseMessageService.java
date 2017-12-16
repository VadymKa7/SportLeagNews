package com.gray.vadimsyromiatnik.sportleagnews.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by vadimsyromiatnik on 12/2/17.
 */

public class FirebaseMessageService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessageService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}
