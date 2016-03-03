package com.example.user.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    User[] user = new User[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnReset = (Button) this.findViewById(R.id.btnReset);
        Button btnLogin = (Button) this.findViewById(R.id.btnLogin);

        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("All Products: ", "11111111111111");
                EditText pwd = (EditText) findViewById(R.id.edtPwd);
                EditText userName = (EditText) findViewById(R.id.edtUserid);
                boolean check = true;
                try {
                    getJson();
                    Log.d("All Products: ", "77777777777777777777");
                } catch (Exception e) {
                    Log.d("All Products: ", "22222222222222");
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
        // String url = "http://10.0.2.2/fyp_connect/get_all_user.php?";
        // HttpClient client = new DefaultHttpClient();

//        HttpGet request = new HttpGet(url);
//        HttpResponse response = client.execute(request);
       // BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        //=============
        String url = "http://10.0.2.2/fyp_connect/get_all_user.php?";
        URL urlObj = new URL(url);
        HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
//        client.setDoOutput(true);
//        client.setDoInput(true);
//        client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        client.setRequestMethod("GET");
        //client.setFixedLengthStreamingMode(request.toString().getBytes("UTF-8").length);
        client.connect();
        // Log.d("doInBackground(Request)", request.toString());

        InputStream input = client.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String reply = result.toString();
        //=============

        JSONObject json = new JSONObject(reply);
        Log.d("All Products: ",json.toString());
        try {
            for(int i=0; i<json.getJSONArray("user").length();i++){
                int id 	               	= json.getJSONArray("user").getJSONObject(i).getInt("id");
                String password         = json.getJSONArray("user").getJSONObject(i).getString("password");
                String use_name         = json.getJSONArray("user").getJSONObject(i).getString("use_name");
                user[i] = new User(id,password,use_name);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
