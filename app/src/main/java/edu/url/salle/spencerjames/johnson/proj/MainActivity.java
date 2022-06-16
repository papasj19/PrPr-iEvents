package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.configs.Config;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

//  String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTY0MCwibmFtZSI6Ik5ldyIsImxhc3RfbmFtZSI6IlVzZXIiLCJlbWFpbCI6Im5ld3VzZXIxMjM0ZDhjeGRzQGdtYWlsLmNvbSIsImltYWdlIjoiY2RzYyJ9.3CZ5iyvqibj4dAebF0YwA3aYae7q_gnEBOHTuVdd1Qo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

        findViewById(R.id.main_btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SigninActivity.class));
            }
        });

//        API.loginUser(MainActivity.this, "qwerty123@gmail.com",
//                "password", new VolleyInterfaceObject() {
//            @Override
//            public void onError(String message) {
//                Util.showToast(MainActivity.this, message);
//            }
//
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    if(response.getString("accessToken")!=null){
//                        Config.accesstoken = response.getString("accessToken");
//                        Util.showToast(MainActivity.this,"User Signup "+response.getString("accessToken"));
//                        API.getUsers(MainActivity.this, new VolleyInterfaceArray() {
//                            @Override
//                            public void onError(String message) {
//                                Util.showToast(MainActivity.this, message);
//                            }
//
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                Util.showToast(MainActivity.this,"Users List "+response.toString());
//
//                            }
//                        });
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Util.showToast(MainActivity.this,e.getMessage());
//                }
//            }
//        });

    }

}