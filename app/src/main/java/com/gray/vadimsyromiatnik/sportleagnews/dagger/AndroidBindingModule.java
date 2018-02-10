package com.gray.vadimsyromiatnik.sportleagnews.dagger;

import com.gray.vadimsyromiatnik.sportleagnews.dagger.module.CommandModule;
import com.gray.vadimsyromiatnik.sportleagnews.dagger.module.LoginModule;
import com.gray.vadimsyromiatnik.sportleagnews.dagger.module.MainModule;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.ChooseCommandActivity;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.LoginActivity;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.MainActivity;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.fragments.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class AndroidBindingModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainFragment contributeMainFragment();


    @ContributesAndroidInjector(modules = CommandModule.class)
    abstract ChooseCommandActivity contributeChooseCommandActivity();

}
