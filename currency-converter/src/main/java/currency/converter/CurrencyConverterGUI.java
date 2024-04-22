package currency.converter;

public class CurrencyConverterGUI {
    public static void main(String[] args) {
        String fileName = "exchangeRateData.txt";
        // ExchangeRateWriter.generateFile(fileName);
        ExchangeRateFileReader.refreshCurrencyRates(fileName);
    }

    // @Override
    // public void start(Stage arg0) throws Exception {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'start'");
    // }
}
