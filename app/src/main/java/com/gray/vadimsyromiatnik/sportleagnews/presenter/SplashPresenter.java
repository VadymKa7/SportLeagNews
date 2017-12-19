package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.view.SplashView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by vadim on 13.12.17.
 */

public interface SplashPresenter extends MvpPresenter<SplashView> {
    void  addAuthStateListener();
}
