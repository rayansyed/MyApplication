package ca.sheridancollege.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;


public class SettingsActivity extends MainActivity{

    Switch switch_dark_theme, switch_large_font;

    public static final int DARK_THEME = 1;
    public static final int LARGE_FONT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        switch_dark_theme = findViewById(R.id.switch_dark_theme);
        switch_large_font = findViewById(R.id.switch_large_font);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.settings_menu, null);
        //Switch item = view.findViewById(R.id.switch_large_font);
//View.setBackgroundColor(Color.parseColor("#e7eecc"));




        switch_dark_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setTheme(R.style.AppTheme);
            }
        });

        switch_large_font.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

    }


}
