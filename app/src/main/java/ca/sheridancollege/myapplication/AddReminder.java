package ca.sheridancollege.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.sheridancollege.myapplication.model.Reminder;

public class AddReminder extends Activity {

    private static final int notificationId = 1;
    private static final String CHANNEL_ID = "123" ;
    EditText reminderName;
    EditText edtHour;
    EditText edtMinute;
    EditText edtSeconds;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    BroadcastReceiver mReceiver;

    int hr = 0;
    int min = 0;
    int sec = 0;
    int result = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        reminderName = findViewById(R.id.edt_name);
        edtHour = findViewById(R.id.edt_hour);
        edtMinute = findViewById(R.id.edt_minute);
        edtSeconds = findViewById(R.id.edt_seconds);

        RegisterAlarmBroadcast();


        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder();



                }
        });
        findViewById(R.id.btn_setAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });



    }


    private void addReminder(){

        String description =  reminderName.getText().toString();

        Reminder reminder = new Reminder(description);

        Intent intent = new Intent();
        intent.putExtra("reminder", reminder);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void setAlarm() {
        String shr = edtHour.getText().toString();
        String smin = edtMinute.getText().toString();
        String ssec = edtSeconds.getText().toString();

        if(shr.equals(""))
            hr = 0;
        else {
            hr = Integer.parseInt(edtHour.getText().toString());
            hr=hr*60*60*1000;
        }

        if(smin.equals(""))
            min = 0;
        else {
            min = Integer.parseInt(edtMinute.getText().toString());
            min = min*60*1000;
        }

        if(ssec.equals(""))
            sec = 0;
        else {
            sec = Integer.parseInt(edtSeconds.getText().toString());
            sec = sec * 1000;
        }
        result = hr+min+sec;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), result , pendingIntent);
    }

    private void notification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Title")
                .setContentText("content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    private void RegisterAlarmBroadcast() {

        mReceiver = new BroadcastReceiver() {
            // private static final String TAG = "Alarm Example Receiver";
            @Override
            public void onReceive(Context context, Intent intent) {
                notification();

            }
        };

        registerReceiver(mReceiver, new IntentFilter("sample"));
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("sample"), 0);
        alarmManager = (AlarmManager)(this.getSystemService(Context.ALARM_SERVICE));
    }



}
