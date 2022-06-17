package edu.url.salle.spencerjames.johnson.proj.models;

public class User {
    public final String name;
    public final String last_name;
    public final String email;
    public final String password;
    public final String Image;
    public int id;

    public User(String name, String last_name, String email, String password, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        Image = image;
    }

    public User(int id, String name, String last_name, String email, String password, String image) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        Image = image;
    }


}
