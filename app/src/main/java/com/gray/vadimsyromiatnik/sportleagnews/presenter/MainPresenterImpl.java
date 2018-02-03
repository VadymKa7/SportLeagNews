package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.helpers.SharedPreferSave;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by vadim on 18.12.17.
 */

public class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";
    SharedPreferSave sharedPreferSave;

    @Inject
    public MainPresenterImpl(SharedPreferSave sharedPreferSave) {
        this.sharedPreferSave = sharedPreferSave;
    }

}
