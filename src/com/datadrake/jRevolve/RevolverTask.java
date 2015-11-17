package com.datadrake.jRevolve;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bryan on 11/17/15.
 */
public class RevolverTask extends TimerTask {

    private WebEngine engine;
    private RevolverRunner runner;

    public RevolverTask(WebEngine engine){
        super();
        this.engine = engine;
        this.runner = new RevolverRunner(engine);
    }

    @Override
    public void run(){
        Platform.runLater(runner);
    }

    private class RevolverRunner implements Runnable{

        private WebEngine engine;
        private List<String> urls = new ArrayList<String>();
        private Iterator<String> it;

        public RevolverRunner(WebEngine engine){
            this.engine = engine;
            urls.add("http://rc.rit.edu/notices");
            urls.add("http://xkcd.com");
            urls.add("http://quartermaster.ce.rit.edu/rackmap/RI_master.xml");
            urls.add("http://xdmod.rc.rit.edu");
        }

        @Override
        public void run() {
            if ((it == null) || !it.hasNext() ){
                it = urls.iterator();
            }
            engine.load(it.next());
            System.out.println(engine.getLocation());
        }
    }
}
