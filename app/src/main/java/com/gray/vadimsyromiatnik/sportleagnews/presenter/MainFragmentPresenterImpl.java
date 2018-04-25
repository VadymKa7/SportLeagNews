package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.Function;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.KeyClass;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.SharedPreferSave;
import com.gray.vadimsyromiatnik.sportleagnews.models.BestEvent;
import com.gray.vadimsyromiatnik.sportleagnews.models.CommandNews;
import com.gray.vadimsyromiatnik.sportleagnews.models.NewsList;
import com.gray.vadimsyromiatnik.sportleagnews.models.Plan;
import com.gray.vadimsyromiatnik.sportleagnews.models.Weather;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.adapters.NewsAdapter;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpQueuingBasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainFragmentPresenterImpl  extends MvpQueuingBasePresenter<MainFragmentView> implements MainFragmentPresenter {
    private static final String TAG = "MainFragmentPresenterIm";

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<NewsList> newsDataList = new ArrayList<>();
    RecyclerView.Adapter arrayAdapter;
    SharedPreferSave sharedPreferSave;

    @Inject
    public MainFragmentPresenterImpl(SharedPreferSave sharedPreferSave) {
        this.sharedPreferSave = sharedPreferSave;
    }

    @Override
    public void showCurrentCityWeather(String lat, String lng) {
        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise) {

                ifViewAttached((MainFragmentView view) -> {
                    view.showCurrentCityWeather(weather_city, weather_description, weather_temperature, weather_updatedOn, weather_iconText, sun_rise);
                });
            }
        });

        asyncTask.execute(lat, lng);
    }

    // ----- today events ------
    @Override
    public void getTodayTeamEventFromDatabase(String sport) {
        DatabaseReference databaseReferenceEvents = database.getReference(KeyClass.TEXT_EVENTS_DAY).child(sport);

        databaseReferenceEvents.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CommandNews changedPost = dataSnapshot.getValue(CommandNews.class);
                ifViewAttached((MainFragmentView view) -> {
                    view.showEventFormServer(changedPost);
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    // ----- today plan -----
    @Override
    public void getTodayPlanEventFromDatabase(String sport) {

        DatabaseReference databaseReferenceEventsSport = database.getReference(KeyClass.TEXT_PLAN_EVENT_DAY).child(sport);

        databaseReferenceEventsSport.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Plan plan = dataSnapshot.getValue(Plan.class);
                ifViewAttached((MainFragmentView view) -> {
                    view.showPlanEventFormServer(plan);
                });
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    // ----- weather -----
    @Override
    public void getTodayWeatherEventFromDatabase(String sport) {

        DatabaseReference databaseReferenceEventsWeather = database.getReference(KeyClass.TEXT_EVENT_WEATHER_DAY).child(sport);

        databaseReferenceEventsWeather.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Weather weather = dataSnapshot.getValue(Weather.class);
                ifViewAttached((MainFragmentView view) -> {
                    view.showWeatherEventFormServer(weather);
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    // ----- best event -----
    @Override
    public void getTodayBestEventFromDatabase(String sport) {

        DatabaseReference databaseReferenceEventsWeather = database.getReference(KeyClass.TEXT_EVENT_SPORT_DAY).child(sport);

        databaseReferenceEventsWeather.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BestEvent weather = dataSnapshot.getValue(BestEvent.class);
                ifViewAttached((MainFragmentView view) -> {
                    view.showBestEventFormServer(weather);
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // ----- list events -----
    @Override
    public void getTodayNewsFromDatabase() {
        DatabaseReference databaseReferenceNews = database.getReference(KeyClass.TEXT_COMMAND_NEWS_DAY);
        arrayAdapter = new NewsAdapter(newsDataList);
        databaseReferenceNews.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ifViewAttached((MainFragmentView view) -> {
                    view.showTodayNewList(arrayAdapter);
                });
                NewsList post = dataSnapshot.getValue(NewsList.class);
                newsDataList.add(post);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void getCommandAndLeagueRequestToServer() {
        String command = sharedPreferSave.readPreference(KeyClass.TEXT_COMMAND_CHOOSE);
        String league = sharedPreferSave.readPreference(KeyClass.TEXT_LEAGUE_CHOOSE);


        onceViewAttached((MainFragmentView view) -> {
            view.showCommandAndLeague(league);
        });

        getTodayWeatherEventFromDatabase(league);
        getTodayTeamEventFromDatabase(league);
        getTodayBestEventFromDatabase(league);
        getTodayPlanEventFromDatabase(league);

    }
}

