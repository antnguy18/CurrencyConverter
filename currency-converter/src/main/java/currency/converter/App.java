package currency.converter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
        String fileName = "exchangeRateData.txt";
        // ExchangeRateWriter.generateFile(fileName);
        ExchangeRateFileReader.refreshCurrencyRates(fileName);
        System.out.println("Currency rates have been refreshed.");
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
