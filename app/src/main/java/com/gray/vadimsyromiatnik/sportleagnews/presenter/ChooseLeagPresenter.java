package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseLeagView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by vadim on 18.12.17.
 */

public interface ChooseLeagPresenter extends MvpPresenter<ChooseLeagView> {
    void chooseCommand(String league);
}
