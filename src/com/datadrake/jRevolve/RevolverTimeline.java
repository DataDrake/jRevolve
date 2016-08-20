package com.datadrake.jRevolve;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bryan on 11/17/15.
 */
public class RevolverTimeline {

    private String configFile;
    private String oldLocation;

    private List<Integer> times;
    private List<String> urls;

    private int currentTime;

    public RevolverTimeline(String configFile){
        this.configFile = configFile;
        this.currentTime = 0;
        this.oldLocation = "";
        updateConfig();
    }

    public void updateConfig(){
        times = new ArrayList<Integer>();
        urls = new ArrayList<String>();
        int time;
        String url;
        try {
            Scanner scan = new Scanner(new File(configFile));
            while(scan.hasNext()){
                time = scan.nextInt();
                url = scan.nextLine();
                if( (time != 0) && (url != null) && (url.length() != 0)){
                    times.add(time);
                    urls.add(url);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void increment(){
        currentTime += 5;
        updateConfig();
    }

    private String getURL(int time){
        int total = 0;
        int i = 0;
        for( ; i < times.size(); i++){
            if( time < (total + times.get(i))){
                break;
            } else {
                total += times.get(i);
            }
        }
        if( ( (i == (times.size()-1)) && (time > (total + times.get(i))) || (i == times.size()))) {
            i = 0;
            if( time == currentTime){
                currentTime = 0;
            }
        }
        return urls.get(i);
    }

    public String getURL(){
        oldLocation = getURL(currentTime);
        return oldLocation;
    }

    public boolean changed(){
        return ! (getURL(currentTime).equals(oldLocation));
    }
}
