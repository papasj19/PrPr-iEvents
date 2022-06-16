package edu.url.salle.spencerjames.johnson.proj.models;

public class DataHolder {

    public static DataHolder instance;

    public String userEmail;

    private DataHolder(){

    }

    public static DataHolder getInstance(){
        if(instance==null)
            instance = new DataHolder();
        return instance;
    }
}
