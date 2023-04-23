package dev.converter.cunit;

import dev.converter.cunit.rest.CurrencyLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class JfxMain extends Application {
    // currency loader class
    CurrencyLoader cl;
    // Program startup method from JavaFX: Application class
    @Override
    public void start(Stage stage) throws Exception {
        // Initialise the currency loader.
        cl = new CurrencyLoader();

        // Load the XML scene
        FXMLLoader fxmlLoader = new FXMLLoader(JfxMain.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Set window properties
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