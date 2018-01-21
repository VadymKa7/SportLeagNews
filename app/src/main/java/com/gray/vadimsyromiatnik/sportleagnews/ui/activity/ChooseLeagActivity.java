package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseLeagPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseLeagPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseLeagView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLeagActivity extends MvpActivity<ChooseLeagView, ChooseLeagPresenter> implements ChooseLeagView {
    private static final String TAG = "ChooseLeagActivity";
    @BindView(R.id.imageFootballChoose)ImageView imageFootballChoose;
    @BindView(R.id.imageAmericanChoose)ImageView imageAmericanChoose;
    @BindView(R.id.imageHockeyChoose)ImageView imageHockeyChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_leag);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.imageFootballChoose)
    public void imageFootballChoose(ImageView imageFootballChoose){
        getPresenter().chooseCommand(getString(R.string.title_football_choose));
    }

    @OnClick(R.id.imageAmericanChoose)
    public void imageAmericanChoose(ImageView imageAmericanChoose){
        getPresenter().chooseCommand(getString(R.string.title_american_football_choose));
    }

    @OnClick(R.id.imageHockeyChoose)
    public void imageHockeyChoose(ImageView imageHockeyChoose){
        getPresenter().chooseCommand(getString(R.string.title_hockey_choose));
    }

    @NonNull
    @Override
    public ChooseLeagPresenter createPresenter() {
        return new ChooseLeagPresenterImpl();
    }

    @Override
    public void intentMainActivity(String league) {
        Intent intent = new Intent(this, ChooseCommandActivity.class);
        intent.putExtra("league", league);
        startActivity(intent);
        finish();
    }


}
