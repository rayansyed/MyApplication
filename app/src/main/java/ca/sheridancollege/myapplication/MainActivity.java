package ca.sheridancollege.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import ca.sheridancollege.myapplication.db.ReminderDao;
import ca.sheridancollege.myapplication.db.ReminderRoomDatabase;
import ca.sheridancollege.myapplication.model.Reminder;


public class MainActivity extends Activity {


    RecyclerView mRecyclerView;
    Switch sw;
    private static final int ADD_REMINDER = 1;
    public static final int NOTIFICATION_DISMISSED = 2;
    public static final int DARK_THEME = 3;

    public static boolean isDark = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sw = findViewById(R.id.switch_dark_theme);
        if (isDark == true){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminders();
            }


        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        loadReminders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(intent, DARK_THEME);
            return true;
        }
        if(id == R.id.action_about){
            Intent intent = new Intent(getApplicationContext(), AboutPageActivity.class);
            startActivityForResult(intent, DARK_THEME);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clear() {
        DeleteReminders deleteReminders = new DeleteReminders();
        deleteReminders.execute();
        loadReminders();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADD_REMINDER && resultCode == RESULT_OK) {

            Reminder reminder = data.getParcelableExtra("reminder");

            InsertTask myTask = new InsertTask();

            myTask.execute(reminder);
        }

    }


    private void loadReminders(){
        RetrieveTask myTask = new RetrieveTask();
        myTask.execute();
    }

    private void addReminders(){

        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivityForResult(intent, ADD_REMINDER);

    }

    private class InsertTask extends AsyncTask<Reminder, Void, Long>{


        @Override
        protected Long doInBackground(Reminder... reminders) {
            ReminderRoomDatabase db = ReminderRoomDatabase.getDatabase(getApplicationContext());
            ReminderDao dao = db.reminderDao();

            long id = dao.insert(reminders[0]);

            return id;
        }

        @Override
        protected void onPostExecute(Long s) {
            loadReminders();
        }
    }

    private class RetrieveTask extends AsyncTask<Void, Void, List<Reminder>>{
        @Override
        protected List<Reminder> doInBackground(Void... voids) {
            ReminderRoomDatabase db = ReminderRoomDatabase.getDatabase(getApplicationContext());

            ReminderDao dao = db.reminderDao();

            List<Reminder> reminders = dao.getAllReminders();
            return reminders;
        }

        @Override
        protected void onPostExecute(List<Reminder> reminders) {
            ReminderAdapter adapter = new ReminderAdapter(reminders);
            mRecyclerView.setAdapter(adapter);
        }
    }

    private class DeleteReminders extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            ReminderRoomDatabase db = ReminderRoomDatabase.getDatabase(getApplicationContext());
            ReminderDao dao = db.reminderDao();
            int numRowsDeleted;
            numRowsDeleted = dao.deleteAll();
            return numRowsDeleted;
        }

        @Override
        protected void onPostExecute(Integer numRowsDeleted) {
            String format = "%d rows deleted";
            Toast.makeText(getApplicationContext(),String.format(format, numRowsDeleted),Toast.LENGTH_LONG).show();
        }
    }

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    // ...

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }
}
