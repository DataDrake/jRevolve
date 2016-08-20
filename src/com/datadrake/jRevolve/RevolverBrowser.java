package com.datadrake.jRevolve;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;

import java.awt.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.Timer;

/**
 * Created by bryan on 11/16/15.
 */
public class RevolverBrowser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    private RevolverTask task;
    private Timer time;

    public final static long FIVE = 5000;

    public RevolverBrowser(String configFile) {
        //apply the styles
        getStyleClass().add("browser");
        //add the web view to the scene
        getChildren().add(browser);
        task = new RevolverTask(webEngine, configFile);
        time = new Timer();
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }

    public void start() {
        time.scheduleAtFixedRate(task,0,FIVE);
    }

    public void stop() {
        time.cancel();
    }

}