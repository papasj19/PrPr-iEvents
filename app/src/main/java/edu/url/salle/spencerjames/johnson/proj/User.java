package edu.url.salle.spencerjames.johnson.proj;

public class User {

    private int id;
    private String nameFirst;
    private String nameLast;
    private String email;
    private String imagePath;

    public User(int id, String nameFirst, String nameLast, String email, String imagePath) {
        this.id = id;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.email = email;
        this.imagePath = imagePath;
    }

    public User(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getEmail() {
        return email;
    }

    public String getImagePath() {
        return imagePath;
    }
}
