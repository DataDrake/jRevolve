package com.datadrake.jRevolve;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.TimerTask;

/**
 * Created by bryan on 11/17/15.
 */
public class RevolverTask extends TimerTask {

    private RevolverRunner runner;

    public RevolverTask(WebEngine engine, String configFile){
        super();
        this.runner = new RevolverRunner(engine,configFile);
    }

    @Override
    public void run(){
        Platform.runLater(runner);
    }

    private class RevolverRunner implements Runnable{

        private WebEngine engine;
        private RevolverTimeline timeline;

        public RevolverRunner(WebEngine engine, String configFile){
            this.engine = engine;
            this.timeline = new RevolverTimeline(configFile);
        }

        @Override
        public void run() {
            timeline.increment();
            if( timeline.changed() ){
                engine.load( timeline.getURL() );
            }
        }
    }
}
