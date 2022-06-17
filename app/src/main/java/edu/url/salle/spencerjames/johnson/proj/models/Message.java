package edu.url.salle.spencerjames.johnson.proj.models;

public class Message {
    public final int id;
    public final int user_id_send;
    public final int user_id_recived;
    public final String content;

    public Message(int id, int user_id_send, int user_id_recived, String content) {
        this.id = id;
        this.user_id_send = user_id_send;
        this.user_id_recived = user_id_recived;
        this.content = content;
    }
}
