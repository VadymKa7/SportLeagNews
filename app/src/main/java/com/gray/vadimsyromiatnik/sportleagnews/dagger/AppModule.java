package com.gray.vadimsyromiatnik.sportleagnews.dagger;

import android.content.Context;

import com.gray.vadimsyromiatnik.sportleagnews.App;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.SharedPreferSave;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    @Provides
    Context providerContext(App app) {
        return app;
    }

    @Provides
    SharedPreferSave sharedPreferSave(Context context){
        return new SharedPreferSave(context);
    }
}
