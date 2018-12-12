package ca.sheridancollege.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends MainActivity{

    Switch switch_dark_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        switch_dark_theme = findViewById(R.id.switch_dark_theme);
    }


}
