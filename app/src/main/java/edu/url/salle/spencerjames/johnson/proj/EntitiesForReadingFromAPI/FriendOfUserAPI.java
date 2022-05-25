package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class FriendOfUserAPI {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String image;

    public FriendOfUserAPI(int id, String name, String lastName, String email, String image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
