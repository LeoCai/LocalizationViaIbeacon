package com.leocai.beaconlocalization.test;

import java.util.Observable;

/**
 * Created by leocai on 15-3-30.
 */
public class ILogger extends Observable{
    private static ILogger ourInstance = new ILogger();
    private  String logInfo="";
    private int lineNum = 0;

    public static ILogger getInstance() {
        return ourInstance;
    }

    private ILogger() {
    }

    public  String getLogInfo() {
        return logInfo;
    }

    public  void setLogInfo(String logInfo) {
        lineNum=1;
        this.logInfo = logInfo+"\n";
        setChanged();
        notifyObservers();
    }
    public void log(String logInfo){
        if(lineNum>4)
            clearLog();
        this.logInfo +=logInfo+"\n";
        setChanged();
        notifyObservers();
        lineNum+=1;
    }
    public void clearLog(){
        this.logInfo = "";
        setChanged();
        notifyObservers();
        lineNum = 0;
    }
}
