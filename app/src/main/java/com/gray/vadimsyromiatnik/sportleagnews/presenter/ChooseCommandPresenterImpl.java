package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import com.gray.vadimsyromiatnik.sportleagnews.helpers.KeyClass;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.SharedPreferSave;
import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseCommandView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by vadimsyromiatnik on 12/23/17.
 */

public class ChooseCommandPresenterImpl  extends MvpBasePresenter<ChooseCommandView> implements ChooseCommandPresenter {
    SharedPreferSave sharedPreferSave;

    @Inject
    public ChooseCommandPresenterImpl(SharedPreferSave sharedPreferSave) {
        this.sharedPreferSave = sharedPreferSave;
    }

    @Override
    public void saveUserCommand(String command){
        sharedPreferSave.writePreference(KeyClass.TEXT_COMMAND_CHOOSE, command);
    }

    @Override
    public void saveUserLeague(String league){
        sharedPreferSave.writePreference(KeyClass.TEXT_LEAGUE_CHOOSE, league);
    }
}
