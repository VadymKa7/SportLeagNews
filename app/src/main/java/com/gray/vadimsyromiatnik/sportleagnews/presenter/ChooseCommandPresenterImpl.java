package com.gray.vadimsyromiatnik.sportleagnews.presenter;


import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseCommandView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by vadim on 18.12.17.
 */

public class ChooseCommandPresenterImpl extends MvpBasePresenter<ChooseCommandView> implements ChooseCommandPresenter {

    @Override
    public void chooseCommand() {
        ifViewAttached(ChooseCommandView::intentMainActivity);
    }
}
