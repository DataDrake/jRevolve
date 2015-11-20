package com.datadrake.jRevolve;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class jRevolver extends Application {

    private RevolverBrowser browser;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Web View");
        String configFile = getParameters().getNamed().get("config");
        if(configFile == null){
            System.out.println("ERROR: no config specified.");
            System.exit(1);
        }
        browser = new RevolverBrowser(configFile);
        Scene scene = new Scene(browser, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
        try{
          int height = Integer.parseInt(getParameters().getNamed().get("height"));
          int width = Integer.parseInt(getParameters().getNamed().get("width"));
          stage.setHeight(height);
          stage.setWidth(width);
          stage.setX(0);
          stage.setY(0);
        }catch(Exception ex){
          stage.setFullScreen(true);
        }
        browser.start();
    }

    @Override
    public void stop() throws Exception{
        browser.stop();
    }


    public static void main(String[] args) {
        if(args.length > 0) {
            launch(args);
        } else {
            System.out.println("Usage: java -jar jRevolver.jar --config=<path> [--height=<int> --width=<int>]");
            System.exit(1);
        }
    }
}
