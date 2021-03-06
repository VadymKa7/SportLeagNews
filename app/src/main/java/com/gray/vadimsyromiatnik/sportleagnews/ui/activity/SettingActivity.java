package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.helpers.LocalData;
import com.gray.vadimsyromiatnik.sportleagnews.service.AlarmReceiver;
import com.gray.vadimsyromiatnik.sportleagnews.service.NotificationScheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    String TAG = "RemindMe";
    LocalData localData;

    SwitchCompat reminderSwitch;

    @BindView(R.id.tvReminderLabel) TextView tvReminderLabel;
    @BindView(R.id.imageBackSetting) Button imageBackSetting;

    LinearLayout ll_set_time;

    int hour, min;

    ClipboardManager myClipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        localData = new LocalData(getApplicationContext());

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        ll_set_time = (LinearLayout) findViewById(R.id.ll_set_time);

        reminderSwitch = (SwitchCompat) findViewById(R.id.timerSwitch);

        hour = localData.get_hour();
        min = localData.get_min();

        tvReminderLabel.setText(getFormatedTime(hour, min));
        reminderSwitch.setChecked(localData.getReminderStatus());

        if (!localData.getReminderStatus())
            ll_set_time.setAlpha(0.4f);

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                localData.setReminderStatus(isChecked);
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    NotificationScheduler.setReminder(SettingActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);
                } else {
                    Log.d(TAG, "onCheckedChanged: false");
                    NotificationScheduler.cancelReminder(SettingActivity.this, AlarmReceiver.class);
                    ll_set_time.setAlpha(0.4f);
                }
            }
        });

        ll_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (localData.getReminderStatus())
                    showTimePickerDialog(localData.get_hour(), localData.get_min());
            }
        });
    }


    private void showTimePickerDialog(int h, int m) {
        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.d(TAG, "onTimeSet: hour " + hour + " : " + min);
                        localData.set_hour(hour);
                        localData.set_min(min);
                        tvReminderLabel.setText(getFormatedTime(hour, min));
                        NotificationScheduler.setReminder(SettingActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());


                    }
                }, h, m, true);
        builder.show();

    }

    @OnClick(R.id.imageBackSetting)
    public void clickImageBackSetting(ImageView imageBackSetting){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            return getResources().getConfiguration().locale;
        }
    }
}
