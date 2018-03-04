package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ReadEventPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ReadEventPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.ReadEventView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.ButterKnife;

public class ReadAllEventActivity extends MvpActivity<ReadEventView, ReadEventPresenter> implements ReadEventView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_event);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public ReadEventPresenter createPresenter() {
        return new ReadEventPresenterImpl();
    }
}
