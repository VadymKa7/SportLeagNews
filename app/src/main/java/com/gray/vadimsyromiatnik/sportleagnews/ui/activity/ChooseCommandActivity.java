package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseCommandView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ChooseCommandActivity extends MvpActivity<ChooseCommandView, ChooseCommandPresenter> implements ChooseCommandView {

    @BindView(R.id.imageFootballChoose)ImageView imageFootballChoose;
    @BindView(R.id.imageAmericanChoose)ImageView imageAmericanChoose;
    @BindView(R.id.imageHockeyChoose)ImageView imageHockeyChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_leag_and_command);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageFootballChoose)
    public void imageFootballChoose(ImageView imageFootballChoose){
        getPresenter().chooseCommand();
    }

    @OnClick(R.id.imageAmericanChoose)
    public void imageAmericanChoose(ImageView imageAmericanChoose){
        getPresenter().chooseCommand();
    }

    @OnClick(R.id.imageHockeyChoose)
    public void imageHockeyChoose(ImageView imageHockeyChoose){
        getPresenter().chooseCommand();
    }


    @NonNull
    @Override
    public ChooseCommandPresenter createPresenter() {
        return new ChooseCommandPresenterImpl();
    }

    @Override
    public void intentMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
