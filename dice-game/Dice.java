package com.example.wade_colton_assignment10;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Random;

public class Dice implements Parcelable {

    // Fields
    private int diceOne, diceTwo, diceThree;
    private int currentScore = 0, totalScore = 0, bonusScore = 0;
    private boolean tripleScore = true, doubleScore = true;
    private int rolls = 0, doubles = 0, triples = 0;

    // Dice Constructor
    public Dice() {
        this.diceOne = 0;
        this.diceTwo = 0;
        this.diceThree = 0;
    }

    // rollDice Method
    public void rollDice(){
        Random r = new Random();
        diceOne = r.nextInt(6) + 1;
        diceTwo = r.nextInt(6) + 1;
        diceThree = r.nextInt(6) + 1;
        currentScore = diceOne + diceTwo + diceThree + getBonusScore(); // calculates currentScore
        totalScore += currentScore; // calculates totalScore
        counterStats(); // calls counterStats

    }

    // keeps a running total for each time user gets a double, triple, or rolls the dice
    public void counterStats() {
        if(diceOne == diceTwo && diceTwo == diceThree) {
            rolls++;
            triples++;
        }
        else if(diceOne == diceTwo || diceOne == diceThree || diceTwo == diceThree) {
            rolls++;
            doubles++;
        }
        else {
            rolls++;
        }
    }

    // Getters/Setters
    public void setTripleScore(boolean x) {
        this.tripleScore = x;
    }
    public boolean getTripleScore() {
        return tripleScore;
    }

    public void setDoubleScore(boolean x) {
        this.doubleScore = x;
    }
    public boolean getDoubleScore() {
        return this.doubleScore;
    }

    public int getDiceOne(){
        return diceOne;
    }
    public int getDiceTwo(){
        return diceTwo;
    }
    public int getDiceThree(){
        return diceThree;
    }

    public int getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(int x) {this.currentScore = x; }

    public int getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(int x) {this.totalScore = x; }

    public int getRolls() {
        return rolls;
    }
    public void setRolls(int x) {this.rolls = x;}
    public int getDoubles() {
        return doubles;
    }
    public int getTriples() {
        return triples;
    }


    public int getBonusScore() {
        bonusScore = 0;

        // If isDoubleScore is true bonusScore is 50
        if(isDoubleScore()) {
            bonusScore = 50;
        }
        // If isTripleScore is true bonusScore is 100.
        if(isTripleScore()) {
            bonusScore = 100;
        }
        return bonusScore;
    }

    // isDoubleScore checks to see if there is a double
    public boolean isDoubleScore() {
        if (doubleScore) {
            return diceOne == diceTwo || diceOne == diceThree || diceTwo == diceThree;
        }
        else {
            return false;
        }
    }

    // isTripleScore checks to see if there is a triple
    public boolean isTripleScore() {
        if (tripleScore) {
            return diceOne == diceTwo && diceTwo == diceThree;
        }
        else {
            return false;
        }
    }

    // Resets Stats back to default
    public void resetStats() {
        this.diceTwo = 0;
        this.diceThree = 0;
        this.currentScore = 0;
        this.totalScore = 0;
        this.bonusScore = 0;
        this.tripleScore = true;
        this.doubleScore = true;
        this.rolls = 0;
        this.doubles = 0;
        this.triples = 0;
    }


    // region Parcelable
// Parcelable Methods
    protected Dice(Parcel in) {
        diceOne = in.readInt();
        diceTwo  = in.readInt();
        diceThree = in.readInt();
        currentScore = in.readInt();
        totalScore = in.readInt();
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(diceOne);
        parcel.writeInt(diceTwo);
        parcel.writeInt(diceThree);
        parcel.writeInt(currentScore);
        parcel.writeInt(totalScore);

    }
    // endregion Parcelable
}
