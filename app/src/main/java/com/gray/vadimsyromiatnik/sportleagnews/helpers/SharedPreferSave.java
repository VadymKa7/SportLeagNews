package com.gray.vadimsyromiatnik.sportleagnews.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by vadim on 18.12.17.
 */

public class SharedPreferSave {
    private SharedPreferences mMyPreferences;

    public SharedPreferSave(Context context) {
        mMyPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void writePreference(String key, String value){
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putString(key, value);
        e.commit();
    }

    public String readPreference(String key){
        String values = mMyPreferences.getString(key, "");
        return values;
    }


    public void writePreferenceInt(String key, int value){
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putInt(key, value);
        e.commit();
    }

    public int readPreferenceInt(String key){
        int values = mMyPreferences.getInt(key, 0);
        return values;
    }

}
