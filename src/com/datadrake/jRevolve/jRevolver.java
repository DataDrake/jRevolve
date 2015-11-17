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
        browser = new RevolverBrowser();
        Scene scene = new Scene(browser, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        browser.start();
    }

    @Override
    public void stop() throws Exception{
        browser.stop();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
