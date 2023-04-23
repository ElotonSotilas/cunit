package dev.converter.cunit;

import dev.converter.cunit.rest.CurrencyLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class JfxMain extends Application {
    CurrencyLoader cl;
    @Override
    public void start(Stage stage) throws Exception {
        cl = new CurrencyLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(JfxMain.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Cunit Currency Converter");
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}