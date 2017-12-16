package com.gray.vadimsyromiatnik.sportleagnews.dagger.module;

import com.gray.vadimsyromiatnik.sportleagnews.interactor.LoginInteractor;
import com.gray.vadimsyromiatnik.sportleagnews.interactor.LoginInteractorImpl;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.LoginPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.LoginPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class LoginModule {
    @Binds
    abstract LoginPresenter presenter(LoginPresenterImpl presenter);

    @Provides
    static LoginInteractor interactor() {
        return new LoginInteractorImpl();
    }
}
