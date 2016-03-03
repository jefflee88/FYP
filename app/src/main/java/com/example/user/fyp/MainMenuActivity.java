package com.example.user.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ImageView imgLogo= (ImageView)findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.jtg_logo);

        Button btnLoginPage = (Button) this.findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(Intent, 0);
            }
        });

        TextView txtRegister = (TextView) this.findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivityForResult(Intent, 0);
            }
        });
    }
}
