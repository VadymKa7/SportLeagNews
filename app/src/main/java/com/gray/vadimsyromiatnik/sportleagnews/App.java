package com.gray.vadimsyromiatnik.sportleagnews;

import com.gray.vadimsyromiatnik.sportleagnews.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by vadimsyromiatnik on 12/9/17.
 */

public class App  extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
