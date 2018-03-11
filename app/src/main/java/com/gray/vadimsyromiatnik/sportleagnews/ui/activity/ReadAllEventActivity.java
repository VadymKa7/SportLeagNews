package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ReadEventPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ReadEventPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.ReadEventView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadAllEventActivity extends MvpActivity<ReadEventView, ReadEventPresenter> implements ReadEventView {

    @BindView(R.id.tvTitleTodayEvent)TextView tvTitleTodayEvent;
    @BindView(R.id.tvBodyTodayEvent)TextView tvBodyTodayEvent;
    @BindView(R.id.imageBackAllEvent)ImageView imageBackAllEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_event);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        tvTitleTodayEvent.setText(intent.getStringExtra("title"));
        tvBodyTodayEvent.setText(intent.getStringExtra("body"));

    }
    @OnClick(R.id.imageBackAllEvent)
    public void imageBackAllEvent(ImageView imageBackAllEvent){
       startActivity(new Intent(this, MainActivity.class));
       finish();
    }

    @NonNull
    @Override
    public ReadEventPresenter createPresenter() {
        return new ReadEventPresenterImpl();
    }
}
