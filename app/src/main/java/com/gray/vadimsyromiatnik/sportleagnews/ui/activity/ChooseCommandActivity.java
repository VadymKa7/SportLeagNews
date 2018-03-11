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
    @BindView(R.id.tableHockey)TableLayout tableHockey;
    @BindView(R.id.tableNFL)TableLayout tableNFL;

    @BindView(R.id.imageBackTeam)ImageView imageBackTeam;

    @Inject
    ChooseCommandPresenterImpl chooseCommandPresenter;

    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String league;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
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

        getIntentDataFromLeague();
    }

    // Image Button Click Listener
    // Football

    @OnClick(R.id.btnPl1Command)void btnPl1Command(){
        chooseCommand(league,"Kristal Palace");
    }
    @OnClick(R.id.btnPl2Command)void btnPl2Command(){
        chooseCommand(league,"Southamptomn");
    }
    @OnClick(R.id.btnPl3Command)void btnPl3Command(){
        chooseCommand(league,"Arsenal");
    }
    @OnClick(R.id.btnPl4Command)void btnPl4Command(){
        chooseCommand(league,"Newcastle");
    }
    @OnClick(R.id.btnPl5Command)void btnPl5Command(){
        chooseCommand(league,"Bernly");
    }
    @OnClick(R.id.btnPl6Command)void btnPl6Command(){
        chooseCommand(league,"Watford");
    }
    @OnClick(R.id.btnPl7Command)void btnPl7Command(){
        chooseCommand(league,"Liverpool");
    }
    @OnClick(R.id.btnPl8Command)void btnPl8Command(){
        chooseCommand(league,"Bournemouth");
    }
    @OnClick(R.id.btnPl9Command)void btnPl9Command(){
        chooseCommand(league,"Stoke City");
    }
    @OnClick(R.id.btnPl10Command)void btnPl10Command(){
        chooseCommand(league,"Tottenham");
    }
    @OnClick(R.id.btnPl11Command)void btnPl11Command(){
        chooseCommand(league,"Chelsea");
    }
    @OnClick(R.id.btnPl12Command)void btnPl12Command() {chooseCommand(league,"West Brom");}
    @OnClick(R.id.btnPl13Command)void btnPl13Command(){
        chooseCommand(league,"West Hem");
    }
    @OnClick(R.id.btnPl14Command)void btnPl14Command(){
        chooseCommand(league,"Everton");
    }
    @OnClick(R.id.btnPl15Command)void btnPl15Command(){
        chooseCommand(league,"Man Utd");
    }
    @OnClick(R.id.btnPl16Command)void btnPl16Command(){
        chooseCommand(league,"Man City");
    }
    @OnClick(R.id.btnPl17Command)void btnPl17Command(){
        chooseCommand(league,"Brighton");
    }
    @OnClick(R.id.btnPl18Command)void btnPl18Command(){
        chooseCommand(league,"Swansea");
    }
    @OnClick(R.id.btnPl19Command)void btnPl19Command(){
        chooseCommand(league,"Leicester");
    }
    @OnClick(R.id.btnPl20Command)void btnPl20Command(){
        chooseCommand(league,"Haddersfield");
    }

    // NFL
    @OnClick(R.id.btnNFL1Command)void btnNFL1Command(){
        chooseCommand(league,"Arizona Cardinals");
    }


    @OnClick(R.id.btnH1Command)void btnH1Command(){
        chooseCommand(league,"Buffalo Sabres");
    }
    @OnClick(R.id.imageBackTeam)void imageBackTeam(){
       startActivity(new Intent(this, ChooseLeagActivity.class));
       finish();
    }

    @NonNull
    @Override
    public ChooseCommandPresenter createPresenter() {
        return chooseCommandPresenter;
    }

    public void getIntentDataFromLeague(){
        Intent intent = getIntent();
        league = intent.getStringExtra("league");
        getPresenter().saveUserLeague(league);
        if(league.equals(getString(R.string.title_football_choose))){
            tableFootball.setVisibility(View.VISIBLE);
        } else if(league.equals(getString(R.string.title_hockey_choose))){
            tableHockey.setVisibility(View.VISIBLE);
        } else if(league.equals(getString(R.string.title_american_football_choose))){
            tableNFL.setVisibility(View.VISIBLE);
        }
    }


    public void chooseCommand(String league, String command){
        String commands = command;
        String strLeague = league;

        getPresenter().saveUserCommand(command);
        getPresenter().saveUserLeague(league);
        if(!commands.equals("") ){
            UserInformation userInformation = new UserInformation(strLeague, command);
            myRef.child("users").child(userID).setValue(userInformation);
        }else{

        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
