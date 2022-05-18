package edu.url.salle.spencerjames.johnson.proj;

public class NewUserContainer {

    private String username;
    private String password;
    private String confirmedPassword;
    private String email;
    private String firstName;
    private String lastName;

    public NewUserContainer(String username, String password, String confirmedPassword, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
