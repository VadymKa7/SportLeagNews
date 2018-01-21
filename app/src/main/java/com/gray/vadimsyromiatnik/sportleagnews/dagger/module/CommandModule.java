package com.gray.vadimsyromiatnik.sportleagnews.dagger.module;


import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vadimsyromiatnik on 1/20/18.
 */

@Module
public abstract class CommandModule {
    @Binds
    abstract ChooseCommandPresenter presenter(ChooseCommandPresenterImpl presenter);

}
