package ca.sheridancollege.myapplication;

import android.os.Bundle;
import android.widget.TextView;

public class AboutPageActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);

        TextView text = findViewById(R.id.txt_about);
    }
}
