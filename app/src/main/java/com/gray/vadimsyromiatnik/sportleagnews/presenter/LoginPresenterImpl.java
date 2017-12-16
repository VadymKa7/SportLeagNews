package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import com.gray.vadimsyromiatnik.sportleagnews.interactor.LoginInteractor;
import com.gray.vadimsyromiatnik.sportleagnews.ui.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by vadimsyromiatnik on 12/9/17.
 */

public class LoginPresenterImpl extends MvpBasePresenter <LoginView> implements LoginPresenter {

    LoginInteractor interactor;

    @Inject
    LoginPresenterImpl(LoginInteractor interactor) {
        this.interactor = interactor;
    }
    
    @Override
    public void requestData() {
        ifViewAttached(view -> {
            view.showToast(interactor.getMessage());
        });
    }
}
