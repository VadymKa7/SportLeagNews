package com.gray.vadimsyromiatnik.sportleagnews.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;


public interface LoginView extends MvpView {
    void showToast(String message);
    void showDialog();
    void intentMainActivity();
    void intentChooseCommandActivity();

}
