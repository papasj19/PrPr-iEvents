package edu.url.salle.spencerjames.johnson.proj.configs;

public abstract class Config {
    private static String accesstoken ;

    public static String getAccesstoken() {
        return accesstoken;
    }

    public static void setAccesstoken(String at) {
        Config.accesstoken = at;
    }
}
