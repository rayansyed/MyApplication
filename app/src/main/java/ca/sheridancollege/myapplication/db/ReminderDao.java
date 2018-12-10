package ca.sheridancollege.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.sheridancollege.myapplication.model.Reminder;

@Dao
public interface ReminderDao {

    @Insert
    long insert(Reminder reminder);

    @Query("SELECT * FROM reminder_table")
    List<Reminder> getAllReminders();

    @Query("DELETE FROM reminder_table")
    int deleteAll();

}
