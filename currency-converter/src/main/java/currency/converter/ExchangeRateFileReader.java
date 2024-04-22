package currency.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

public class ExchangeRateFileReader {

    private static HashMap<String, Double> currencyRates = new HashMap<>();
    private static String jsonData;

    public static HashMap<String, Double> getCurrencyRates() {
        return currencyRates;
    }

    public static void refreshCurrencyRates(String fileName) {
        readCurrencyRates(fileName);
    }

    // Returns the last refresh time for real time exchange data
    public static String getLastUpdate(String fileName) {
        JSONObject parser = new JSONObject(jsonData);
        return parser.getString("time_last_update_utc");
    }

    // Updates the currencyRates field data using the JSON data present in fileName
    private static void readCurrencyRates(String fileName) {
        // clear current exchange rates
        currencyRates.clear();

        // Updates jsonData String field
        updateFileJSONData(fileName);

        // Parse JSON data and extract exchange rates
        JSONObject parser = new JSONObject(jsonData);
        JSONObject conversionRates = parser.getJSONObject("conversion_rates");

        // Iterate through JSON map data and place currency exchange data into currencyRates static field
        for (String currencyCode : conversionRates.keySet()) {
            double rate = conversionRates.getDouble(currencyCode);
            currencyRates.put(currencyCode, rate);
        }
    }

    private static void updateFileJSONData(String fileName) {

        // Read JSON data from text file and assign it to a StringBuilder
        StringBuilder jsonDataBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonDataBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonData = jsonDataBuilder.toString();
    }
}
