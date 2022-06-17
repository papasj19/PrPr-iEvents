package edu.url.salle.spencerjames.johnson.proj.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.url.salle.spencerjames.johnson.proj.R;
import edu.url.salle.spencerjames.johnson.proj.models.Message;

import java.util.ArrayList;


public class ChatListviewAdapter extends ArrayAdapter<Message> {
    final Context context;
    final int userId;
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
            contentTv.setGravity(Gravity.END);
        }else{
            contentTv.setText("Send by User\n"+ message.content);
            contentTv.setGravity(Gravity.START);
        }





        return convertView;
    }
}
