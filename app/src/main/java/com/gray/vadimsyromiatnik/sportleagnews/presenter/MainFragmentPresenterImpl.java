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
import com.gray.vadimsyromiatnik.sportleagnews.models.NewsList;
import com.gray.vadimsyromiatnik.sportleagnews.ui.activity.adapters.NewsAdapter;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainFragmentPresenterImpl  extends MvpBasePresenter<MainFragmentView> implements MainFragmentPresenter {
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


    @Override
    public void getTodayEventFromDatabase() {
        DatabaseReference databaseReferenceEvents = database.getReference(KeyClass.TEXT_EVENTS_DAY).child("Arsenal");

        databaseReferenceEvents.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String changedPost = dataSnapshot.getValue(String.class);
                Log.d(TAG, "onChildAdded: " + changedPost);
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
    public void getCommandAndLeague() {
        String command = sharedPreferSave.readPreference(KeyClass.TEXT_COMMAND_CHOOSE);
        String league = sharedPreferSave.readPreference(KeyClass.TEXT_LEAGUE_CHOOSE);
        ifViewAttached((MainFragmentView view) -> {
            view.showCommandAndLeague(league, command);
        });
    }
}

