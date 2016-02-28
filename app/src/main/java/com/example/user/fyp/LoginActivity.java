package com.example.user.fyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
