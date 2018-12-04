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


public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

    ArrayList <String> mReminders;

    public ReminderAdapter(ArrayList<String> reminders){
        this.mReminders = reminders;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNames;
        private CheckBox checkBox;

        public MyViewHolder(LinearLayout layout){
            super(layout);
            mTxtNames = layout.findViewById(R.id.txt_listName);
            checkBox =layout.findViewById(R.id.chk_name);
        }
    }




    @Override
    public ReminderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_display,parent,false);
        MyViewHolder holder = new MyViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.MyViewHolder holder, int position) {
        holder.mTxtNames.setText(mReminders.get(position));
        holder.checkBox.setOnCheckedChangeListener(null);



    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }
}
