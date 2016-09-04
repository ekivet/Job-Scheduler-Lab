package com.example.erickivet.jobschedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erickivet on 9/4/16.
 */
public class DataSingleton {

    private static DataSingleton instance;

    private String firstString = "Time";
    private String secondString = "Random #";
    private String thirdString = "#000000";

    public interface DataSingletonListener{
        void onFirstStringChanged(String oldString);
        void onSecondStringChanged(String oldString);
        void onThirdStringChanged(String oldString);
    }

    private List<DataSingletonListener> listeners;

    private DataSingleton(){
        listeners = new ArrayList<>();
    }

    public static DataSingleton getInstance(){
        if (instance == null){
            instance = new DataSingleton();
        }
        return instance;
    }

    public void addDataChangeListener(DataSingletonListener listener){
        listeners.add(listener);
    }

    public void updateFirstString(String newString){
        String oldString = firstString;
        firstString = newString;
        notifyFirstStringChanged(oldString);
    }

    public void updateSecondString(String newString){
        String oldString = secondString;
        secondString = newString;
        notifySecondStringChanged(oldString);
    }

    public void updateThirdString(String newString){
        String oldString = thirdString;
        thirdString = newString;
        notifyThirdStringChanged(oldString);
    }

    public String getFirstString(){return firstString;}

    public String getSecondString(){return secondString;}

    public String getThirdString(){return thirdString;}

    private void notifyFirstStringChanged(String oldString){
        for(DataSingletonListener listener : listeners){
            listener.onFirstStringChanged(oldString);
        }
    }

    private void notifySecondStringChanged(String oldString){
        for (DataSingletonListener listener :listeners){
            listener.onSecondStringChanged(oldString);
        }
    }

    private void notifyThirdStringChanged(String oldString){
        for (DataSingletonListener listener : listeners){
            listener.onThirdStringChanged(oldString);
        }
    }







}
