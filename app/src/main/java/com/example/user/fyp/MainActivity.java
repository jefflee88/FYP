package com.example.user.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReset12 = (Button) this.findViewById(R.id.button2);
        btnReset12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(Intent, 0);
            }
        });
    }
}

//terry's testing

//chuen's testing

//chuen's testing again

//hin's testing
