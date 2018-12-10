package ca.sheridancollege.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.sheridancollege.myapplication.model.Reminder;


public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

    private List<Reminder> mReminders;

    public ReminderAdapter(List<Reminder> reminders){
        this.mReminders = reminders;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNames;

        public MyViewHolder(TextView itemView){
            super(itemView);
            mTxtNames = itemView;
        }
    }



    @NonNull
    @Override
    public ReminderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        TextView txtReminder = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_display,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(txtReminder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.MyViewHolder holder, int position) {


        Reminder reminder = mReminders.get(position);
        holder.mTxtNames.setText(reminder.getDescription());



    }

    @Override
    public int getItemCount() {

        return mReminders == null? 0:mReminders.size();
    }


}
