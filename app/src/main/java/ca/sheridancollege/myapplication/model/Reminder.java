package ca.sheridancollege.myapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "reminder_table")
public class Reminder implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long reminderId;

    private String description;

    public Reminder(long reminderId, String description) {
        this.reminderId = reminderId;
        this.description = description;
    }

    @Ignore
    public Reminder(String description) {
        this.description = description;
    }

    protected Reminder(Parcel in) {
        reminderId = in.readLong();
        description = in.readString();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(reminderId);
        dest.writeString(description);
    }
}
