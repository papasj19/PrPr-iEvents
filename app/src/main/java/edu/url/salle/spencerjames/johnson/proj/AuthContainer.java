package edu.url.salle.spencerjames.johnson.proj;

public class AuthContainer {
    private String token;

    public AuthContainer(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
