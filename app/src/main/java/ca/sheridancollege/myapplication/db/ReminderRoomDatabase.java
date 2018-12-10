package ca.sheridancollege.myapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.sheridancollege.myapplication.model.Reminder;

@Database(entities = {Reminder.class}, version = 1)
public abstract class ReminderRoomDatabase extends RoomDatabase {

    private static ReminderRoomDatabase INSTANCE;
    public abstract ReminderDao reminderDao();

    public static ReminderRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, ReminderRoomDatabase.class, "reminder_db").build();

        }
        return INSTANCE;
    }

}
