package com.example.user.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.fyp.bean.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {
    User[] user;
    EditText pwd,userName;
    Button btnReset,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnReset= (Button) this.findViewById(R.id.btnReset);
        btnLogin = (Button) this.findViewById(R.id.btnLogin);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd = (EditText) findViewById(R.id.edtPwd);
                userName = (EditText) findViewById(R.id.edtUserid);
                boolean check = true;
                try {
                    getJson();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < user.length; i++) {
                    if (Integer.parseInt(userName.getText().toString()) == user[i].getId() && ((pwd.getText().toString()).equals(user[i].getPassword()))) {
                        Toast.makeText(getApplicationContext(), "Hello " + user[i].getUse_name() + "!!", Toast.LENGTH_SHORT).show();
                        check = false;
                        Intent Intent = new Intent(view.getContext(), MainMenuActivity.class);
                        startActivityForResult(Intent, 0);
                        break;
                    } else {
                        check = true;
                    }
                }
                if (check == true)
                    Toast.makeText(getApplicationContext(), "Incorrect username or password. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getJson() throws Exception{
        String url = "http://10.0.2.2/fyp_connect/get_all_user.php?";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String reply="";
        String line;
        while((line=reader.readLine()) != null)
            reply += line;
        JSONObject json = new JSONObject(reply);
        try {
            for(int i=0; i<json.getJSONArray("user").length();i++){
                int id 	               	= json.getJSONArray("user").getJSONObject(i).getInt("id");
                String password         = json.getJSONArray("user").getJSONObject(i).getString("password");
                String use_name         = json.getJSONArray("user").getJSONObject(i).getString("use_name");
                user[i] = new User(id, password, use_name);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
