package com.example.user.fyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ImageView imgLogo= (ImageView) findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.jtg_logo);


    }
}
