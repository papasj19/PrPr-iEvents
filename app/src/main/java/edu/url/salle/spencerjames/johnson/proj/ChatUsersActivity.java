package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import edu.url.salle.spencerjames.johnson.proj.adapters.UsersListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ChatUsersActivity extends AppCompatActivity {

    private ListView usersLv;
    private final ArrayList<User> usersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        init();
        Util.showProgressDialog(ChatUsersActivity.this, ChatUsersActivity.this.getString(R.string.obtainusers));
        API.getAllChatsUsers(ChatUsersActivity.this, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(ChatUsersActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject userJson = response.getJSONObject(i);
                        usersList.add(new User(userJson.getInt("id"), userJson.getString("name"), userJson.getString("last_name"),
                                userJson.getString("email"), "",
                                userJson.getString("image")));

                    }
                    Util.dismissProgressDialog();
                    setListview();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(ChatUsersActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });

    }

    private void init(){
        usersLv = findViewById(R.id.chatusers_lv_users);
    }

    private void setListview(){
        UsersListviewAdapter usersListviewAdapter = new UsersListviewAdapter(ChatUsersActivity.this, usersList, 3);
        usersLv.setAdapter(usersListviewAdapter);
    }
}