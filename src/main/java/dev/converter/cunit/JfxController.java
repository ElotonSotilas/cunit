package dev.converter.cunit;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JfxController implements Initializable {
    public Separator Separator1;
    public Separator Separator11;
    public Label myLabel;
    public Label myLabel4;
    @FXML
    private TextField myTextField1, myTextField2;
    @FXML
    ChoiceBox<String> myChoiceBox = new ChoiceBox<>();
    @FXML
    ListView<String> myListView = new ListView<>();
    @FXML
    ListView<String> myListView2 = new ListView<>();
    ObservableList<String> currencyOptions = FXCollections.observableArrayList();
    ObservableList<String> lengthOptions = FXCollections.observableArrayList("Meter", "Kilometer", "Centimeter","Decimeter", "Millimeter", "Mile", "Foot", "Inch", "Yard");
    ObservableList<String> volumeOptions = FXCollections.observableArrayList("Liter", "Milliliter", "Gallon");
    ObservableList<String> temperatureOptions = FXCollections.observableArrayList("Celsius", "Kelvin", "Fahrenheit");
    ObservableList<String> weightOptions = FXCollections.observableArrayList("Kilogram", "Gram", "Milligram", "Pound", "Ounce", "Ton");
    ObservableList<String> nullOptions = FXCollections.observableArrayList("");

    ObservableList<String> currencyOptions2 = currencyOptions;
    ObservableList<String> lengthOptions2 = lengthOptions;
    ObservableList<String> volumeOptions2 = volumeOptions;
    ObservableList<String> temperatureOptions2 = temperatureOptions;
    ObservableList<String> weightOptions2 = weightOptions;
    ObservableList<String> nullOptions2 = nullOptions;

    @FXML
    private Button convertButton;

    @FXML
    private void convertLeftToRight(ActionEvent event) throws Exception{

        switch (myChoiceBox.getSelectionModel().getSelectedItem()) {
            // Depending on user's input a different method is executed
            // switch/case has no default because the program will always provide category and units of measurement
            // Model for methods:
            case "Length" -> myTextField2.setText(getLength(myListView.getSelectionModel().getSelectedItem(), myListView2.getSelectionModel().getSelectedItem(), Double.parseDouble(myTextField1.getText())));
            case "Temperature" -> myTextField2.setText(getTemperature(myListView.getSelectionModel().getSelectedItem(), myListView2.getSelectionModel().getSelectedItem(), Double.parseDouble(myTextField1.getText())));
            case "Weight" -> myTextField2.setText(getWeight(myListView.getSelectionModel().getSelectedItem(), myListView2.getSelectionModel().getSelectedItem(), Double.parseDouble(myTextField1.getText())));
            case "Volume" -> myTextField2.setText(getVolume(myListView.getSelectionModel().getSelectedItem(), myListView2.getSelectionModel().getSelectedItem(), Double.parseDouble(myTextField1.getText())));
            case "Currency" -> myTextField2.setText(getCurrency(myListView.getSelectionModel().getSelectedItem(), myListView2.getSelectionModel().getSelectedItem(), Double.parseDouble(myTextField1.getText())));
        }


    }
    public String getTemperature(String initialType, String secondType, double value) {
        double valueToC = 0;    // Variable that will store the input transformed into Celsius
        double valueToSecondType = 0;
        switch (initialType) {
            case "Fahrenheit" -> valueToC = (value - 32) * 0.5556; // Fahrenheit
            case "Celsius" -> valueToC = value; // Celsius
            case "Kelvin" -> valueToC = value - 273.15; // Kelvin
        }
        switch (secondType) {
            case "Fahrenheit" -> valueToSecondType = valueToC * 1.8 + 32;
            case "Celsius" -> valueToSecondType = valueToC;
            case "Kelvin" -> valueToSecondType = valueToC + 273.15;
        }
        return Double.toString(valueToSecondType);
    }
    public String getWeight(String initialType, String secondType, double value)
    {
        double valueToMG = 0;   // Variable that will store the input transformed into milligrams
        double valueToSecondType = 0;
        switch (initialType) {
            case "Milligram" -> valueToMG = value;
            case "Gram" -> valueToMG = value * Math.pow(10,3);
            case "Kilogram" -> valueToMG = value * Math.pow(10, 6);
            case "Ton" -> valueToMG = value * Math.pow(10,9);
            case "Ounce" -> valueToMG = value * 28349.5;
            case "Pound" -> valueToMG = value * 453592;
        }
        switch (secondType) {
            case "Milligram" -> valueToSecondType = valueToMG;
            case "Gram" -> valueToSecondType = valueToMG / Math.pow(10,3);
            case "Kilogram" -> valueToSecondType = valueToMG / Math.pow(10,6);
            case "Ton" -> valueToSecondType = valueToMG / Math.pow(10,9);
            case "Ounce" -> valueToSecondType = valueToMG / 28349.5;
            case "Pound" -> valueToSecondType = valueToMG / 453592;
        }
        return Double.toString(valueToSecondType);
    }

    public String getLength(String initialType, String secondType, double value)
    {

        double valueToMM = 0; // Variable that will store the input transformed into millimeters
        double valueToSecondType = 0; // Variable that will store the end result of the transformation
        switch (initialType) {
            // User's input is firstly transformed into millimeters
            case "Millimeter" -> valueToMM = value;
            case "Centimeter" -> valueToMM = value * 10;
            case "Decimeter" -> valueToMM = value * 100;
            case "Meter" -> valueToMM = value * 1000;
            case "Kilometer" -> valueToMM = value * Math.pow(10,6);
            case "Inch" -> valueToMM = value * 25.4;
            case "Foot" -> valueToMM = value * 304.8;
            case "Yard" -> valueToMM = value * 914.4;
            case "Mile" -> valueToMM = value * 1609344;
        }
        switch (secondType) {
            // The input that was transformed into millimeters is transformed into the desired unit of measurement
            case "Millimeter" -> valueToSecondType = valueToMM;
            case "Centimeter" -> valueToSecondType = valueToMM / 10;
            case "Decimeter" -> valueToSecondType = valueToMM / 100;
            case "Meter" -> valueToSecondType = valueToMM / 1000;
            case "Kilometer" -> valueToSecondType = valueToMM / Math.pow(10,6);
            case "Inch" -> valueToSecondType = valueToMM / 25.4;
            case "Foot" -> valueToSecondType = valueToMM / 304.8;
            case "Yard" -> valueToSecondType = valueToMM / 914.4;
            case "Mile" -> valueToSecondType = valueToMM / 1609344;
        }
        return Double.toString(valueToSecondType);
    }

    public String getVolume(String initialType, String secondType, double value)
    // add fluid ounce, gallon
    {
        double valueToML = 0;
        double valueToSecondType = 0;
        switch (initialType)
        {
            case "Milliliter" -> valueToML = value;
            case "Liter" -> valueToML = value * 1000;
            case "Fluid Ounce" -> valueToML = value * 29.5735;
            case "Gallon" -> valueToML = value * 3785.41;
        }
        switch (secondType)
        {
            case "Milliliter" -> valueToSecondType = valueToML;
            case "Liter" -> valueToSecondType = valueToML / 1000;
            case "Fluid Ounce" -> valueToSecondType = valueToML / 29.5735296875;
            case "Gallon" -> valueToSecondType = valueToML / 3785.411784;
        }
        return Double.toString(valueToSecondType);
    }

    public String getCurrency (String initialType, String secondType, double value) throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("cached_rates.txt"));
        StringBuilder sb = new StringBuilder();

        String line = bufferedReader.readLine();
        while (line != null)
        {
            sb.append(line).append("\n");
            line = bufferedReader.readLine();
        }

        String currencies = sb.toString();
        bufferedReader.close();

        String rate1 = "", rate2 = "";
        double rateOne = 0, rateTwo = 0, valueToFirstCurrency, valueToSecondCurrency;
        rateOne = getRateOne(initialType, currencies, rate1, rateOne);
        valueToFirstCurrency = value / rateOne;
        rateTwo = getRateOne(secondType, currencies, rate2, rateTwo);
        valueToSecondCurrency = valueToFirstCurrency * rateTwo;
        return Double.toString(valueToSecondCurrency);
    }

    private double getRateOne(String initialType, String currencies, String rate1, double rateOne) {
        for(int i = 0; i < currencies.length(); i++)
        {
            if((currencies.charAt(i) + "" + currencies.charAt(i+1) + "" + currencies.charAt(i+2)).equals(initialType))
            {
                int br = i + 6;
                while (Character.isDigit(currencies.charAt(br)) || ((currencies.charAt(br)=='.')))
                {
                    rate1 = rate1 + "" + currencies.charAt(br);
                    br++;
                }
                rateOne = Double.parseDouble(rate1);
                break;
            }
        }
        return rateOne;
    }

    @FXML
    private Button btn1,btn2;

    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Object root;

        if(event.getSource() == btn1){
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")));
        }
        else {
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        }
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Stream<String> lines = Files.lines(Paths.get("cached_rates.txt"))){
            currencyOptions = lines
                    .filter(line -> line.length() >= 3)
                    .map(line -> line.substring(0,3))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            currencyOptions = currencyOptions.stream().sorted()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            currencyOptions2 = currencyOptions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        myChoiceBox.getItems().addAll("Length", "Volume", "Temperature", "Weight", "Currency");
        myChoiceBox.setValue("Length");

        myListView.itemsProperty()
                .bind(
                        Bindings
                                .when(myChoiceBox.valueProperty()
                                        .isEqualTo("Length"))
                                .then(lengthOptions)
                                .otherwise(
                Bindings
                        .when(myChoiceBox.valueProperty()
                                .isEqualTo("Volume"))
                        .then(volumeOptions)
                        .otherwise(
                        Bindings
                                .when(myChoiceBox.valueProperty()
                                        .isEqualTo("Temperature"))
                                .then(temperatureOptions)
                                .otherwise(
                                Bindings
                                        .when(myChoiceBox.valueProperty()
                                                .isEqualTo("Weight"))
                                        .then(weightOptions)
                                        .otherwise(
                                        Bindings
                                                .when(myChoiceBox.valueProperty()
                                                        .isEqualTo("Currency"))
                                                .then(currencyOptions)
                                                .otherwise(nullOptions))))));

        myListView2.itemsProperty()
                .bind(
                    Bindings
                        .when(myChoiceBox.valueProperty()
                            .isEqualTo("Length"))
                        .then(lengthOptions2)
                        .otherwise(
                Bindings
                    .when(myChoiceBox.valueProperty()
                        .isEqualTo("Volume"))
                    .then(volumeOptions2)
                    .otherwise(
                        Bindings
                            .when(myChoiceBox.valueProperty()
                                .isEqualTo("Temperature"))
                            .then(temperatureOptions2)
                            .otherwise(
                                Bindings
                                    .when(myChoiceBox.valueProperty()
                                        .isEqualTo("Weight"))
                                    .then(weightOptions2)
                                    .otherwise(
                                            Bindings
                                                .when(myChoiceBox.valueProperty()
                                                    .isEqualTo("Currency"))
                                                .then(currencyOptions2)
                                                .otherwise(nullOptions2))))));

        myChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if     (newValue.equals("Length") ||
                        newValue.equals("Volume") ||
                        newValue.equals("Temperature") ||
                        newValue.equals("Weight") ||
                        newValue.equals("Currency")) {
                    myListView.setVisible(true);
                    myListView2.setVisible(true);
                } else {
                    myListView.setVisible(false);
                    myListView2.setVisible(false);
                }
            }
        });
    }

}