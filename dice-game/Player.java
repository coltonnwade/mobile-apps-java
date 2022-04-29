package com.example.wade_colton_assignment10;

public class Player {


    // Class Fields
    private String name;
    private int totalScore, totalDoubles, totalTriples, totalRolls;


    // Class Constructor
    public Player(String n, int s, int d, int t, int r) {
        this.name = n;
        this.totalScore = s;
        this.totalDoubles = d;
        this.totalTriples = t;
        this.totalRolls = r;
    }


    // Getters
    public String getName() {
        return name;
    }
    public void setName(String x) {this.name = x;}

    public int getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(int x) {this.totalScore = x;}

    public int getTotalDoubles() {
        return totalDoubles;
    }
    public void setTotalDoubles(int x) {this.totalDoubles = x;}

    public int getTotalTriples() { return totalTriples; }
    public void setTotalTriples(int x) {this.totalTriples = x;}

    public int getTotalRolls() {return totalRolls; }
    public void setTotalRolls(int x) {this.totalRolls = x;}

}
