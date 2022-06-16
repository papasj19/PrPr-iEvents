package edu.url.salle.spencerjames.johnson.proj.models;

public class Message {
    public int id, user_id_send, user_id_recived;
    public String content;

    public Message(int id, int user_id_send, int user_id_recived, String content) {
        this.id = id;
        this.user_id_send = user_id_send;
        this.user_id_recived = user_id_recived;
        this.content = content;
    }
}
