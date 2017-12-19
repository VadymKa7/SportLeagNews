package com.gray.vadimsyromiatnik.sportleagnews.dagger;

import com.gray.vadimsyromiatnik.sportleagnews.dagger.module.LoginModule;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class AndroidBindingModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity contributeLoginActivity();

}
