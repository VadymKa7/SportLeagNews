package com.gray.vadimsyromiatnik.sportleagnews.dagger.module;

import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vadim on 17.01.18.
 */

@Module
public abstract class MainModule {
    @Binds
    abstract MainPresenter presenter(MainPresenterImpl presenter);

}
