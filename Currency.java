package CurrencyConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Currency {
    private String name;
    private String shortName;
    private HashMap<String, Double> exchangeValues = new HashMap<>();

    /* Currency Constructor */
    public Currency(String nameValue, String shortNameValue) {
        this.name = nameValue;
        this.shortName = shortNameValue;
    }

    /* Getter for name */
    public String getName() {
        return this.name;
    }

    /* Getter for shortName */
    public String getShortName() {
        return this.shortName;
    }

    /* Getter for exchangeValues */
    public HashMap<String, Double> getExchangeValues() {
        return this.exchangeValues;
    }

    /* Set default values for a currency */
    public void defaultValues() {
        String currency = this.name;

        switch (currency) {
            case "US Dollar":
                this.exchangeValues.put("USD", 1.00);
                this.exchangeValues.put("EUR", 0.90);
                this.exchangeValues.put("GBP", 0.75);
                this.exchangeValues.put("GHA", 15.78);
                break;

            case "Euro":
                this.exchangeValues.put("USD", 1.12);
                this.exchangeValues.put("EUR", 1.00);
                this.exchangeValues.put("GBP", 0.83);
                this.exchangeValues.put("GHA", 17.62);
                break;

            case "British Pound":
                this.exchangeValues.put("USD", 1.34);
                this.exchangeValues.put("EUR", 1.20);
                this.exchangeValues.put("GBP", 1.00);
                this.exchangeValues.put("GHA", 21.15);
                break;

            case "Ghana Cedis":
                this.exchangeValues.put("USD", 0.063);
                this.exchangeValues.put("EUR", 0.057);
                this.exchangeValues.put("GBP", 0.047);
                this.exchangeValues.put("GHA", 1.00);
                break;
        }
    }

    /* Initialize currencies */
    public static ArrayList<Currency> init() {
        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("US Dollar", "USD"));
        currencies.add(new Currency("Euro", "EUR"));
        currencies.add(new Currency("British Pound", "GBP"));
        currencies.add(new Currency("Ghana Cedis", "GHA"));
        for (Currency currency : currencies) {
            currency.defaultValues();
        }
        return currencies;
    }

    /* Convert a currency to another */
    public static Double convert(Double amount, Double exchangeValue) {
        return Math.round(amount * exchangeValue * 100d) / 100d;
    }

    /* Main method to run the currency converter */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Currency> currencies = init();

        System.out.println("Available currencies:");
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println((i + 1) + ". " + currencies.get(i).getShortName());
        }

        // Select currency to convert from
        System.out.print("Select the currency you want to convert from (1-" + currencies.size() + "): ");
        int fromCurrencyIndex = scanner.nextInt() - 1;

        // Input amount to convert
        System.out.print("Enter the amount to convert: ");
        double amountToConvert = scanner.nextDouble();

        // Select currency to convert to
        System.out.print("Select the currency you want to convert to (1-" + currencies.size() + "): ");
        int toCurrencyIndex = scanner.nextInt() - 1;

        // Get exchange rate
        double exchangeValue = currencies.get(fromCurrencyIndex).getExchangeValues()
                .get(currencies.get(toCurrencyIndex).getShortName());

        // Perform conversion
        double convertedAmount = convert(amountToConvert, exchangeValue);
        System.out.printf("Converted amount: %.2f %s\n", convertedAmount, currencies.get(toCurrencyIndex).getShortName());

        scanner.close();
    }
}
