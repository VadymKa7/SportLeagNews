package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface LoginPresenter extends MvpPresenter<LoginView> {
    void requestData();
    void signInWithEmailAndPassword(String email, String password);
    void createUserWithEmailAndPassword(String email, String password);
}
