package ca.sheridancollege.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.sheridancollege.myapplication.model.Reminder;

public class AddReminder extends MainActivity {

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
    String desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        reminderName = findViewById(R.id.edt_name);
        edtHour = findViewById(R.id.edt_hour);
        edtMinute = findViewById(R.id.edt_minute);
        edtSeconds = findViewById(R.id.edt_seconds);
        desc =  reminderName.getText().toString();

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder();
                scheduleNotification(getNotification(desc), setAlarm());

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

    public int setAlarm() {
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
        return result;
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);

        PendingIntent goBack =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Reminder!");
        builder.setContentIntent(goBack);
        builder.setContentText(content);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        return builder.build();
    }






}
