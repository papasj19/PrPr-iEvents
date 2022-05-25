package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class UserStatistics {

    private float avgScore;
    private int numComments;
    private float percentageCommmentors;

    public UserStatistics() {
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public void setPercentageCommmentors(float percentageCommmentors) {
        this.percentageCommmentors = percentageCommmentors;
    }
}
