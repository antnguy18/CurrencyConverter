package currency.converter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExchangeRateFileWriter {

    public static void generateFile(String fileName) {

        // API endpoint for currency exchange rates
        String apiUrl = "https://v6.exchangerate-api.com/v6/50d92358a56f5a4c49a618ac/latest/USD";

        try {
            // Make an HTTP request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            Scanner scan = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scan.hasNextLine()) {
                response.append(scan.nextLine()).append("\n"); // Append a newline after each line
            }
            scan.close();

            // Parse JSON response
            String exchangeRateData = response.toString();

            // Write the response onto a local txt file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(exchangeRateData);
            writer.close();

            System.out.println("Exchange rates have been written to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}