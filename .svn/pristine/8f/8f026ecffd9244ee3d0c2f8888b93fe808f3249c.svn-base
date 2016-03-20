package com.leocai.beaconlocalization.localization;

import android.content.Intent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by leocai on 15-3-30.
 */
public class LocalizationEvaluation extends Observable {

    private int rightAnswers;
    private int wrongAnswers;

    public LocalizationEvaluation(Observer observer){
        addObserver(observer);
    }

    public void addCollect() {
        rightAnswers += 1;
        setChanged();
        notifyObservers();
    }

    public void addFalse() {
        wrongAnswers += 1;
        setChanged();
        notifyObservers();
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
        setChanged();
        notifyObservers();
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
        setChanged();
        notifyObservers();
    }

    public double getRightPercent() {
        return 1.0 * rightAnswers / (rightAnswers + wrongAnswers);
    }

    public void clearAnswers(){
        rightAnswers = 0;
        wrongAnswers = 0;
        setChanged();
        notifyObservers();
    }
}
