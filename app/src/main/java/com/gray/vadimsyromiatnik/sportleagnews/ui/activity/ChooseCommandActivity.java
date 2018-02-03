package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.models.UserInformation;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.ChooseCommandPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.ChooseCommandView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ChooseCommandActivity extends MvpActivity<ChooseCommandView, ChooseCommandPresenter> implements ChooseCommandView {

    @BindView(R.id.tableFootball)TableLayout tableFootball;
    @BindView(R.id.btnPl1Command)ImageView btnPl1Command;
    @BindView(R.id.btnPl2Command)ImageView btnPl2Command;
    @BindView(R.id.btnPl3Command)ImageView btnPl3Command;
    @BindView(R.id.btnPl4Command)ImageView btnPl4Command;
    @BindView(R.id.btnPl5Command)ImageView btnPl5Command;
    @BindView(R.id.btnPl6Command)ImageView btnPl6Command;
    @BindView(R.id.btnPl7Command)ImageView btnPl7Command;
    @BindView(R.id.btnPl8Command)ImageView btnPl8Command;
    @BindView(R.id.btnPl9Command)ImageView btnPl9Command;
    @BindView(R.id.btnPl10Command)ImageView btnPl10Command;
    @BindView(R.id.btnPl11Command)ImageView btnPl11Command;
    @BindView(R.id.btnPl12Command)ImageView btnPl12Command;
    @BindView(R.id.btnPl13Command)ImageView btnPl13Command;
    @BindView(R.id.btnPl14Command)ImageView btnPl14Command;
    @BindView(R.id.btnPl15Command)ImageView btnPl15Command;
    @BindView(R.id.btnPl16Command)ImageView btnPl16Command;
    @BindView(R.id.btnPl17Command)ImageView btnPl17Command;
    @BindView(R.id.btnPl18Command)ImageView btnPl18Command;
    @BindView(R.id.btnPl19Command)ImageView btnPl19Command;
    @BindView(R.id.btnPl20Command)ImageView btnPl20Command;

    @BindView(R.id.tableHockey)TableLayout tableHockey;
    @BindView(R.id.tableNFL)TableLayout tableNFL;
    @Inject
    ChooseCommandPresenterImpl chooseCommandPresenter;

    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_command);
        ButterKnife.bind(this);
        //tvLeagueCommand.setText(getIntentDataFromLeague());

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    // Image Button Click Listener
    @OnClick(R.id.btnPl1Command)void btnPl1Command(){
        chooseCommand(getIntentDataFromLeague(),"Kristal Palace");
    }

    @OnClick(R.id.btnPl2Command)void btnPl2Command(){
        chooseCommand(getIntentDataFromLeague(),"Southamptomn");
    }
    @OnClick(R.id.btnPl3Command)void btnPl3Command(){
        chooseCommand(getIntentDataFromLeague(),"Arsenal");
    }
    @OnClick(R.id.btnPl4Command)void btnPl4Command(){
        chooseCommand(getIntentDataFromLeague(),"Newcastle");
    }
    @OnClick(R.id.btnPl5Command)void btnPl5Command(){
        chooseCommand(getIntentDataFromLeague(),"Bernly");
    }
    @OnClick(R.id.btnPl6Command)void btnPl6Command(){
        chooseCommand(getIntentDataFromLeague(),"Watford");
    }
    @OnClick(R.id.btnPl7Command)void btnPl7Command(){
        chooseCommand(getIntentDataFromLeague(),"Liverpool");
    }
    @OnClick(R.id.btnPl8Command)void btnPl8Command(){
        chooseCommand(getIntentDataFromLeague(),"Bournemouth");
    }

    @OnClick(R.id.btnPl9Command)void btnPl9Command(){
        chooseCommand(getIntentDataFromLeague(),"Stoke City");
    }

    @OnClick(R.id.btnPl10Command)void btnPl10Command(){
        chooseCommand(getIntentDataFromLeague(),"Tottenham");
    }
    @OnClick(R.id.btnPl11Command)void btnPl11Command(){
        chooseCommand(getIntentDataFromLeague(),"Chelsea");
    }
    @OnClick(R.id.btnPl12Command)void btnPl12Command(){
        chooseCommand(getIntentDataFromLeague(),"West Brom");
    }
    @OnClick(R.id.btnPl13Command)void btnPl13Command(){
        chooseCommand(getIntentDataFromLeague(),"West Hem");
    }
    @OnClick(R.id.btnPl14Command)void btnPl14Command(){
        chooseCommand(getIntentDataFromLeague(),"Everton");
    }
    @OnClick(R.id.btnPl15Command)void btnPl15Command(){
        chooseCommand(getIntentDataFromLeague(),"Man Utd");
    }
    @OnClick(R.id.btnPl16Command)void btnPl16Command(){
        chooseCommand(getIntentDataFromLeague(),"Man City");
    }
    @OnClick(R.id.btnPl17Command)void btnPl17Command(){
        chooseCommand(getIntentDataFromLeague(),"Brighton");
    }
    @OnClick(R.id.btnPl18Command)void btnPl18Command(){
        chooseCommand(getIntentDataFromLeague(),"Swansea");
    }
    @OnClick(R.id.btnPl19Command)void btnPl19Command(){
        chooseCommand(getIntentDataFromLeague(),"Leicester");
    }
    @OnClick(R.id.btnPl20Command)void btnPl20Command(){
        chooseCommand(getIntentDataFromLeague(),"Haddersfield");
    }





    @NonNull
    @Override
    public ChooseCommandPresenter createPresenter() {
        return chooseCommandPresenter;
    }

    public String getIntentDataFromLeague(){
        Intent intent = getIntent();
        String league = intent.getStringExtra("league");
        getPresenter().saveUserLeague(league);
        if(league.equals(getString(R.string.title_football_choose))){
            tableFootball.setVisibility(View.VISIBLE);
        } else if(league.equals(getString(R.string.title_hockey_choose))){
            tableHockey.setVisibility(View.VISIBLE);
        } else if(league.equals(getString(R.string.title_american_football_choose))){
            tableNFL.setVisibility(View.VISIBLE);
        }
        return league;
    }


    public void chooseCommand(String league, String command){
        String commands = command;
        String strLeague = league;

        getPresenter().saveUserCommand(command);

        if(!commands.equals("") ){
            UserInformation userInformation = new UserInformation(strLeague, command);
            myRef.child("users").child(userID).setValue(userInformation);
        }else{

        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
