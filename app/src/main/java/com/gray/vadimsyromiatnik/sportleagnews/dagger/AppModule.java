package com.gray.vadimsyromiatnik.sportleagnews.dagger;

import android.content.Context;

import com.gray.vadimsyromiatnik.sportleagnews.App;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    @Provides
    Context providerContext(App app) {
        return app;
    }
}
