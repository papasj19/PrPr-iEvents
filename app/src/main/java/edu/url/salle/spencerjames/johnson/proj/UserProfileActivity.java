package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

public class UserProfileActivity extends AppCompatActivity {

    private int id = 0;
    private ImageView picture;
    private TextView firstName;
    private TextView email;
    private TextView userID;
    private Button chatBt;
    private User userDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        id = getIntent().getIntExtra("id",748);

        chatBt = findViewById(R.id.userprofile_chat);
        chatBt.setOnClickListener(view -> {
            Intent intent = new Intent(UserProfileActivity.this, ChatActivity.class);
            intent.putExtra("id", userDisplay.id);
            UserProfileActivity.this.startActivity(intent);
        });

        Util.showProgressDialog(UserProfileActivity.this, UserProfileActivity.this.getString(R.string.obtainusers));
        API.getUserById(UserProfileActivity.this, id, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(UserProfileActivity.this, message);
                Log.e("USERPROFILE", "error: " + message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.i("USERPROFILE", "response: " + response.getJSONObject(0).toString());
                    JSONObject userJson = response.getJSONObject(0);
                    userDisplay = new User(userJson.getInt("id"),userJson.getString("name"), userJson.getString("last_name"),
                            userJson.getString("email"), "",
                            userJson.getString("image"));
                    Util.dismissProgressDialog();

                    picture = findViewById(R.id.userprofile_picture);

                    if(!userDisplay.Image.equals("")) {
                        Glide.with(UserProfileActivity.this).load(userDisplay.Image).placeholder(R.drawable.profilepicplaceholder).into(picture);
                    }

                    firstName = findViewById(R.id.userprofile_fullname);
                    firstName.setText(userDisplay.name + " " + userDisplay.last_name);
                    email = findViewById(R.id.userprofile_email);
                    email.setText(userDisplay.email);
                    userID = findViewById(R.id.userprofile_id);
                    userID.setText("id: " + userDisplay.id);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(UserProfileActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });
    }
}