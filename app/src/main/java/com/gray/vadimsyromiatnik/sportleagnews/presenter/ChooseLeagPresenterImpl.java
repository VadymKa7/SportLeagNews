package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseLeagView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by vadim on 18.12.17.
 */

public class ChooseLeagPresenterImpl extends MvpBasePresenter<ChooseLeagView> implements ChooseLeagPresenter {

    @Override
    public void chooseCommand(String league) {
        ifViewAttached((ChooseLeagView view) -> {
            view.intentMainActivity(league);
        });
    }
}
