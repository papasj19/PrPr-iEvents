package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import edu.url.salle.spencerjames.johnson.proj.adapters.ChatListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.adapters.UsersListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.DataHolder;
import edu.url.salle.spencerjames.johnson.proj.models.Message;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ListView messagesLv;
    private ArrayList<Message> messagesList = new ArrayList<>();
    int id = 0, userId;
    EditText messageEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        id = getIntent().getIntExtra("id",0);

        init();
        Util.showProgressDialog(ChatActivity.this, ChatActivity.this.getString(R.string.obtainmessag));
        API.getChatsById(ChatActivity.this,id, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(ChatActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject msgJson = response.getJSONObject(i);
                        messagesList.add(new Message(msgJson.getInt("id"), msgJson.getInt("user_id_send"),
                                msgJson.getInt("user_id_recived"), msgJson.getString("content")));

                    }
//                    Util.dismissProgressDialog();
                    getCurrentUserId();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(ChatActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });

    }

    private void init(){
        messagesLv = findViewById(R.id.chat_lv_chats);
        messageEt = findViewById(R.id.chat_et_message);

        findViewById(R.id.chat_btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.isEditTextEmpty(messageEt)){
                    Util.showToast(ChatActivity.this,ChatActivity.this.getString(R.string.Writemessfirst));
                }else{
                    Util.showProgressDialog(ChatActivity.this, ChatActivity.this.getString(R.string.sendmessag));
                    String con = id+"  " + userId + "  " + messageEt.getText().toString();
                    API.sendChatMessage(ChatActivity.this,messageEt.getText().toString(),
                            userId,id, new VolleyInterfaceObject() {
                        @Override
                        public void onError(String message) {
                            Util.showToast(ChatActivity.this, message);
                            Util.dismissProgressDialog();
                        }

                        @Override
                        public void onResponse(JSONObject response) {
                            if(response!=null){
                                Util.showToast(ChatActivity.this, ChatActivity.this.getString(R.string.successful_sendmess));
                                Util.dismissProgressDialog();
                                Intent intent = new Intent(ChatActivity.this, ChatActivity.class);
                                intent.putExtra("id",id);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    private void setListview(){
        ChatListviewAdapter chatListviewAdapter = new ChatListviewAdapter(ChatActivity.this, messagesList, userId );
        messagesLv.setAdapter(chatListviewAdapter);
    }

    private void getCurrentUserId(){
//        Util.showProgressDialog(ChatActivity.this,"Please wait");
        API.getUserBySearch(ChatActivity.this, DataHolder.getInstance().userEmail, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(ChatActivity.this, message);
                Util.dismissProgressDialog();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    if(response.length()>0){
                        JSONObject user = response.getJSONObject(0);
                        userId = user.getInt("id");
                        Util.dismissProgressDialog();
                        setListview();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(ChatActivity.this,"error: "+ e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });
    }
}