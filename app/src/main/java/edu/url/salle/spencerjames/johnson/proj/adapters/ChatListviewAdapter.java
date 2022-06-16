package edu.url.salle.spencerjames.johnson.proj.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import edu.url.salle.spencerjames.johnson.proj.EventParticipantsActivity;
import edu.url.salle.spencerjames.johnson.proj.R;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.models.Message;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONObject;

import java.util.ArrayList;


public class ChatListviewAdapter extends ArrayAdapter<Message> {
    Context context;
    int userId;
    public ChatListviewAdapter(Context context, ArrayList<Message> chatList, int userId) {
        super(context, 0, chatList);
        this.context = context;
        this.userId = userId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_viewholder, parent, false);
        }

        TextView contentTv = convertView.findViewById(R.id.message_vh_tv_content);

        if(message.user_id_send==userId){
            contentTv.setText("Send by You\n"+ message.content);
            contentTv.setGravity(Gravity.RIGHT);
        }else{
            contentTv.setText("Send by User\n"+ message.content);
            contentTv.setGravity(Gravity.LEFT);
        }





        return convertView;
    }
}
