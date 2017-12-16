package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import com.gray.vadimsyromiatnik.sportleagnews.ui.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by vadimsyromiatnik on 12/9/17.
 */

public interface LoginPresenter extends MvpPresenter<LoginView> {
    void requestData();
}
