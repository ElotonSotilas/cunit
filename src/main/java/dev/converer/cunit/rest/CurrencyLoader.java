package dev.converer.cunit.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

public class CurrencyLoader {

    private static final String API_KEY = "DM7ynvC1Qs5LCmOH1D1rk1iebRtocKgq"; // put this .env file
    private static final String API_URL = "https://api.apilayer.com/fixer/latest?base=USD";
    private static final String CACHED_RATES = "cached_rates.txt";

    private HashMap<String, Double> currencyRates;

    public CurrencyLoader() throws Exception {
        currencyRates = new HashMap<>();
        loadCurrencyRates();
    }

    public void loadCurrencyRates() throws Exception {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            connection.setRequestProperty("apikey", API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONObject response = new JSONObject(builder.toString());
            JSONObject rates = response.getJSONObject("rates");

            // Iterate over the rates object and add the currency codes and rates to the map
            for (String code : rates.keySet()) {
                Double rate = rates.getDouble(code);
                this.currencyRates.put(code, rate);
            }

            // Write the currency rates to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CACHED_RATES))) {
                for (String code : currencyRates.keySet()) {
                    writer.write(code + " - " + currencyRates.get(code) + "\n");
                }
            }

        } catch (Exception e) {
            // this code block will be entered when there is no internet 
            // or an exception has occured
            try (Scanner scanner = new Scanner(new File(CACHED_RATES))) {
                HashMap<String, Double> data = new HashMap<>();

                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split("-");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        Double value = Double.parseDouble(parts[1].trim());
                        data.put(key, value);
                    }
                }

                // overwrite leftover values within rates if an exception has occured
                // so that currencyRates matches cached rates
                setCurrencyRates(data); 
            };
        }
    }

    // load rates within other parts of the program by invoking this method
    public HashMap<String, Double> getCurrencyRates() {
        return currencyRates;
    }

    // setter shouldn't be publicly available
    private void setCurrencyRates(HashMap<String, Double> currencyRates) {
        this.currencyRates = currencyRates;
    }

    public static void main(String[] args) throws Exception {
        // this code is redundant, I've used it for testing the class
        // you need to do something similar within the entrypoint of the program
        // in order to try and load newest data on program start up

        CurrencyLoader loader = new CurrencyLoader();
        HashMap<String, Double> currencyRates = loader.getCurrencyRates();
        System.out.println(currencyRates);
    }
}
