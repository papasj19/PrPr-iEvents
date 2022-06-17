package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.home_btn_users).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, UsersActivity.class)));

        findViewById(R.id.home_btn_friends).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, FriendsActivity.class)));

        findViewById(R.id.home_btn_requests).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, FriendRequestsActivity.class)));

        findViewById(R.id.home_btn_edit).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, EditActivity.class)));

        findViewById(R.id.home_btn_signout).setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        findViewById(R.id.home_btn_createevent).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, CreateEventActivity.class)));

        findViewById(R.id.home_btn_getevents).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AllEventsActivity.class)));

        findViewById(R.id.home_btn_geteventbyid).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, SpecificEventActivity.class)));

        findViewById(R.id.home_btn_getbestevents).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, BestEventActivity.class)));

        findViewById(R.id.home_btn_chatusers).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ChatUsersActivity.class)));



    }
}