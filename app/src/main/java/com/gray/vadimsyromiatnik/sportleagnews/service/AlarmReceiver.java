package com.gray.vadimsyromiatnik.sportleagnews.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gray.vadimsyromiatnik.sportleagnews.helpers.LocalData;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.MainActivity;

/**
 * Created by vadim on 07.02.18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }
        NotificationScheduler.showNotification(context, MainActivity.class,
                "You have 5 unwatched videos", "Watch them now?");
    }
}
