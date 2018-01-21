package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseCommandView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by vadimsyromiatnik on 12/23/17.
 */

public interface ChooseCommandPresenter extends MvpPresenter<ChooseCommandView> {
    void saveUserCommand(String command);
    void saveUserLeague(String league);
}
