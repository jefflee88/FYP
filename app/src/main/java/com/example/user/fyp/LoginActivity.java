package com.example.user.fyp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public static User[] user2 = new User[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //new  LoadAllUsers(LoginActivity.this).execute();
        Button btnReset = (Button) this.findViewById(R.id.btnReset);
        Button btnLogin = (Button) this.findViewById(R.id.btnLogin);

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD)
        {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
        }
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
                //new  LoadAllUsers(LoginActivity.this).execute();
                aaa();
                Log.d("All Products: ", "userid : " + user2[0].getId());
                for (int i = 0; i < 2; i++) {



                    boolean userNameOK = Integer.parseInt(userName.getText().toString()) == user2[i].getId();
                    boolean passwordOK = pwd.getText().toString().equals(user2[i].getPassword());
                    if (userNameOK && passwordOK) {
                        Toast.makeText(getApplicationContext(), "Hello " + user2[i].getUse_name() + "!!", Toast.LENGTH_SHORT).show();
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
    public void aaa(){
        User [] user = new User[8];
        try {
            Log.d("All Products: ", "in asynctask ---0000000000");
            String url = "http://10.0.2.2/fyp_connect/get_all_user.php";
            URL urlObj = new URL(url);
            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            Log.d("All Products: ", "in asynctask --- 33333333333");
            client.setRequestMethod("GET");
            Log.d("All Products: ", "in asynctask --- 33333333333434333434");
            //client.setFixedLengthStreamingMode(request.toString().getBytes("UTF-8").length);
            client.connect();
            // Log.d("doInBackground(Request)", request.toString());
            Log.d("All Products: ", "in asynctask --- 444444444444");
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            StringBuilder result = new StringBuilder();
            String line;
            Log.d("All Products: ", "in asynctask --- 5555555555");

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String reply = result.toString();

            Log.d("All Products: ", "ireply : " + reply);
            JSONObject json = new JSONObject(reply);
            Log.d("All Products: ", "ireply : " + reply);
            try {
                for(int i=0; i<json.getJSONArray("user").length();i++){
                    JSONObject jsonObj = json.getJSONArray("user").getJSONObject(i);
                    int id 	               	= jsonObj.getInt("id");
                    String password         = jsonObj.getString("password");
                    String use_name         = jsonObj.getString("use_name");

                    // ===

                    user[i] = new User(id,password,use_name);
                }
                user2 = user;
                Log.d("All Products: ", Integer.toString(user[0].getId()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
