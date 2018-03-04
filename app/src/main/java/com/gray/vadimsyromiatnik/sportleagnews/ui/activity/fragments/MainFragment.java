package com.gray.vadimsyromiatnik.sportleagnews.ui.activity.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.gray.vadimsyromiatnik.sportleagnews.ListenerMainActivity;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.models.BestEvent;
import com.gray.vadimsyromiatnik.sportleagnews.models.CommandNews;
import com.gray.vadimsyromiatnik.sportleagnews.models.Plan;
import com.gray.vadimsyromiatnik.sportleagnews.models.Weather;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainFragmentPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.MainActivity;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.ReadAllEventActivity;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;


public class MainFragment extends MvpFragment<MainFragmentView, MainFragmentPresenter> implements MainFragmentView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.detailsField)TextView detailsField;
    @BindView(R.id.currentTemperatureField)TextView currentTemperatureField;
    @BindView(R.id.weather_icon)TextView weatherIcon;
    @BindView(R.id.tvTodayEventMain)TextView tvTodayEventMain;
    @BindView(R.id.tvTodayWeatherEventMain)TextView tvTodayWeatherEventMain;
    @BindView(R.id.tvTodayPlanMain)TextView tvTodayPlanMain;
    @BindView(R.id.tvTodayEventMainClick)TextView tvTodayEventMainClick;
    @BindView(R.id.tvTodayBestEventMain)TextView tvTodayBestEventMain;
    @BindView(R.id.tvTodayWeatherEventMainClick)TextView tvTodayWeatherEventMainClick;
    @BindView(R.id.tvTodayPlanMainClick)TextView tvTodayPlanMainClick;
    @BindView(R.id.tvTodayBestEventMainClick)TextView tvTodayBestEventMainClick;
    @BindView(R.id.switchTitle)Switch switchTitle;
    @BindView(R.id.recyclerViewNews)RecyclerView recyclerViewNews;
    @BindView(R.id.imageMenuMain)ImageView imageMenuMain;
    private ListenerMainActivity mListenerActivity;
    private RecyclerView.LayoutManager mLayoutManager;
    Typeface weatherFont;
    @Inject MainFragmentPresenter mainFragmentPresenter;

    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return view;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());
                    getPresenter().showCurrentCityWeather(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
                    // Logic to handle location object
                }
            }
        });

        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        weatherIcon.setTypeface(weatherFont);
//        recyclerViewNews.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewNews.setLayoutManager(mLayoutManager);

        getPresenter().getTodayNewsFromDatabase();
        getPresenter().getTodayTeamEventFromDatabase();
        getPresenter().getTodayWeatherEventFromDatabase("Football");
        getPresenter().getTodayBestEventFromDatabase();
        getPresenter().getTodayPlanEventFromDatabase();

        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListenerActivity = ((ListenerMainActivity) activity);
    }

    @Override
    public void showEventFormServer(CommandNews event) {
        tvTodayEventMain.setText(event.getTitle());
        tvTodayEventMainClick.setPaintFlags(tvTodayEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvTodayEventMainClick.setText(event.getSubtitle());
    }

    @Override
    public void showWeatherEventFormServer(Weather weather) {
        tvTodayWeatherEventMain.setText(weather.getTitle());
        tvTodayWeatherEventMainClick.setPaintFlags(tvTodayWeatherEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTodayWeatherEventMainClick.setText(weather.getSubtitle());

    }

    @Override
    public void showPlanEventFormServer(Plan plan) {
        tvTodayPlanMain.setText(plan.getTitle());
        tvTodayPlanMainClick.setPaintFlags(tvTodayPlanMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvTodayPlanMainClick.setText(plan.getSubtitle());

    }

    @Override
    public void showBestEventFormServer(BestEvent bestEvan) {
        tvTodayBestEventMain.setText(bestEvan.getTitle());
        tvTodayBestEventMainClick.setPaintFlags(tvTodayBestEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvTodayBestEventMainClick.setText(bestEvan.getSubtitle());
    }

    @Override
    public void showTodayNewList( RecyclerView.Adapter arrayAdapter) {
        recyclerViewNews.setAdapter(arrayAdapter);
    }

    @Override
    public void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise) {
        detailsField.setText(weather_city);
        weatherIcon.setText(Html.fromHtml(weather_iconText));
        if(weather_temperature!=null){
            currentTemperatureField.setText(splitWeatherTemperatureView(weather_temperature));
        }
    }

    @Override
    public void showCommandAndLeague(String league, String command) {

        Toast.makeText(getContext(), league, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showCommandAndLeague: " + league);
        Log.d(TAG, "showCommandAndLeague: " + command);
    }

    @Override
    public String splitWeatherTemperatureView(String temperature) {
        String string = temperature;
        String[] parts = string.split(",");
        String part1 = parts[0];
        String part2 = parts[1];
        return part1 ;
    }

    @OnClick(R.id.imageMenuMain)
    public void showImageMenuNavigation(ImageView imageMenuMain){
        mListenerActivity.onOpenNavigation();
    }


    @OnClick(R.id.tvTodayEventMainClick)
    public void tvTodayEventMainClick(TextView tvTodayEventMainClick){
        startActivity(new Intent(getContext(), ReadAllEventActivity.class));
    }

    @OnClick(R.id.tvTodayWeatherEventMainClick)
    public void tvTodayWeatherEventMainClick(TextView tvTodayWeatherEventMainClick){
        startActivity(new Intent(getContext(), ReadAllEventActivity.class));
    }

    @OnClick(R.id.tvTodayPlanMainClick)
    public void tvTodayPlanMainClick(TextView tvTodayPlanMainClick){
        startActivity(new Intent(getContext(), ReadAllEventActivity.class));
    }

    @OnClick(R.id.tvTodayBestEventMainClick)
    public void tvTodayBestEventMainClick(TextView tvTodayBestEventMainClick){
        startActivity(new Intent(getContext(), ReadAllEventActivity.class));
    }

    public void sendDataInReadEventActivity(String title, String body){
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("body", body);
        startActivity(intent);
    }

    @Override
    public MainFragmentPresenter createPresenter() {
        return mainFragmentPresenter;
    }
}
