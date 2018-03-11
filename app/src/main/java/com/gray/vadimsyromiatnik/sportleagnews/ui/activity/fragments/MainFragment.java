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
import android.widget.CompoundButton;
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
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.ReadAllEventActivity;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
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
    @BindView(R.id.imageMainTitle)ImageView imageMainTitle;

    private String titleBestNews, bodyBestNews, titlePlanNews, bodyPlanNews, titleWeatherNews,
            bodyWeatherNews, titleEventNews, bodyEventNews;
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
        showCurrentWeatherInCity();
        getPresenter().getCommandAndLeagueRequestToServer();
        getPresenter().getTodayNewsFromDatabase();

        switchTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setText(getString(R.string.text_do_switch_ok));
                }else {
                    buttonView.setText(getString(R.string.text_do_switch));
                }
            }
        });
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListenerActivity = ((ListenerMainActivity) activity);
    }

    @Override
    public void showCommandAndLeague(String league) {
        Toast.makeText(getActivity(), league , Toast.LENGTH_SHORT).show();

        if(league.equals(getString(R.string.title_football_choose))){
            imageMainTitle.setImageResource(R.drawable.player_image);
        }else if(league.equals(getString(R.string.title_american_football_choose))){
            imageMainTitle.setImageResource(R.drawable.nfl_main);
        }else {
            imageMainTitle.setImageResource(R.drawable.nhl_main);
        }
    }

    @Override
    public void showEventFormServer(CommandNews event) {
        tvTodayEventMain.setText(event.getTitle());
        tvTodayEventMainClick.setPaintFlags(tvTodayEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTodayEventMainClick.setText(event.getSubtitle());
        titleEventNews = event.getTitle();
        bodyEventNews = event.getBody();
    }

    @Override
    public void showWeatherEventFormServer(Weather weather) {
        tvTodayWeatherEventMain.setText(weather.getTitle());
        tvTodayWeatherEventMainClick.setPaintFlags(tvTodayWeatherEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTodayWeatherEventMainClick.setText(weather.getSubtitle());
        titleWeatherNews = weather.getTitle();
        bodyWeatherNews = weather.getBody();
    }

    @Override
    public void showPlanEventFormServer(Plan plan) {
        tvTodayPlanMain.setText(plan.getTitle());
        tvTodayPlanMainClick.setPaintFlags(tvTodayPlanMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTodayPlanMainClick.setText(plan.getSubtitle());
        titlePlanNews = plan.getTitle();
        bodyPlanNews = plan.getBody();
    }

    @Override
    public void showBestEventFormServer(BestEvent bestEvan) {
        tvTodayBestEventMain.setText(bestEvan.getTitle());
        tvTodayBestEventMainClick.setPaintFlags(tvTodayBestEventMainClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTodayBestEventMainClick.setText(bestEvan.getSubtitle());
        titleBestNews = bestEvan.getTitle();
        bodyBestNews = bestEvan.getBody();
    }

    @Override
    public void showTodayNewList(RecyclerView.Adapter arrayAdapter) {
        recyclerViewNews.setAdapter(arrayAdapter);
    }

    @Override
    public void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise) {
        detailsField.setText(weather_city);
        weatherIcon.setText(Html.fromHtml(weather_iconText));
        if (weather_temperature != null) {
            currentTemperatureField.setText(splitWeatherTemperatureView(weather_temperature));
        }
    }


    @Override
    public String splitWeatherTemperatureView(String temperature) {
        String string = temperature;
        String[] parts = string.split(",");
        String part1 = parts[0];
        String part2 = parts[1];
        return part1;
    }

    @OnClick(R.id.imageMenuMain)
    public void showImageMenuNavigation(ImageView imageMenuMain) {
        mListenerActivity.onOpenNavigation();
    }


    @OnClick(R.id.tvTodayEventMainClick)
    public void tvTodayEventMainClick(TextView tvTodayEventMainClick) {
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", titleEventNews);
        intent.putExtra("body", bodyEventNews);
        startActivity(intent);
    }

    @OnClick(R.id.tvTodayWeatherEventMainClick)
    public void tvTodayWeatherEventMainClick(TextView tvTodayWeatherEventMainClick) {
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", titleWeatherNews);
        intent.putExtra("body", bodyWeatherNews);
        startActivity(intent);
    }

    @OnClick(R.id.tvTodayPlanMainClick)
    public void tvTodayPlanMainClick(TextView tvTodayPlanMainClick) {
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", titlePlanNews);
        intent.putExtra("body", bodyPlanNews);
        startActivity(intent);
    }

    @OnClick(R.id.tvTodayBestEventMainClick)
    public void tvTodayBestEventMainClick(TextView tvTodayBestEventMainClick) {
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", titleBestNews);
        intent.putExtra("body", bodyBestNews);
        startActivity(intent);
    }


    public void sendDataInReadEventActivity(String title, String body) {
        Intent intent = new Intent(getContext(), ReadAllEventActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("body", body);
        startActivity(intent);
    }

    // get and show weather
    public void showCurrentWeatherInCity() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    getPresenter().showCurrentCityWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                    // Logic to handle location object
                }
            }
        });

        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        weatherIcon.setTypeface(weatherFont);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewNews.setLayoutManager(mLayoutManager);
    }

    @Override
    public MainFragmentPresenter createPresenter() {
        return mainFragmentPresenter;
    }
}
