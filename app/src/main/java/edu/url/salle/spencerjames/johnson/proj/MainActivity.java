package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

//  String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTY0MCwibmFtZSI6Ik5ldyIsImxhc3RfbmFtZSI6IlVzZXIiLCJlbWFpbCI6Im5ld3VzZXIxMjM0ZDhjeGRzQGdtYWlsLmNvbSIsImltYWdlIjoiY2RzYyJ9.3CZ5iyvqibj4dAebF0YwA3aYae7q_gnEBOHTuVdd1Qo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_btn_signup).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignupActivity.class)));

        findViewById(R.id.main_btn_signin).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SigninActivity.class)));

    }

}