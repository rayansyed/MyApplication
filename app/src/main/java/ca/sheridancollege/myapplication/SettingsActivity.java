package ca.sheridancollege.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import ca.sheridancollege.myapplication.MainActivity;


public class SettingsActivity extends MainActivity{

    Switch switch_dark_theme, switch_large_font;

    public static final int DARK_THEME = 1;
    public static final int LARGE_FONT = 2;
    static boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.DarkTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        switch_dark_theme = findViewById(R.id.switch_dark_theme);
        switch_large_font = findViewById(R.id.switch_large_font);

        if(isChecked == true){
            switch_dark_theme.setChecked(true);
        }else {
            switch_dark_theme.setChecked(false);
        }

        //LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.settings_menu, null);
        //Switch item = view.findViewById(R.id.switch_large_font);
//View.setBackgroundColor(Color.parseColor("#e7eecc"));

        switch_dark_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(isChecked == false) {
                    MainActivity.isDark = true;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    isChecked = true;
                }else{
                    MainActivity.isDark = false;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    isChecked = false;
                }
            }
        });

        switch_large_font.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

    }


}
