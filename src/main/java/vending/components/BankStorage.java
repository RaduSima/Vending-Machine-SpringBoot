package vending.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vending.entities.Item;
import vending.entities.Money;
import vending.exceptions.InvalidCurrencyException;
import vending.exceptions.NotSufficientChangeException;
import vending.services.MoneyService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Component
public class BankStorage {
    private static final List<Double> validCurrencies = List.of(500D, 100D, 50D,
            10D, 5D,
            1D, 0.5, 0.1, 0.01);

    private List<Money> money;

    private final MoneyService moneyService;

    private final List<Double> moneyCurrentlyProcessing = new ArrayList<>();

    private double currentSold = 0;

    @Autowired
    public BankStorage(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    public double getCurrentSold() {
        return currentSold;
    }

    public void setCurrentSold(double currentSold) {
        this.currentSold = currentSold;
    }

    private void syncMoneyWithDatabase() {
        money = moneyService.findAll();
    }

    public List<Money> showMoney() {
        syncMoneyWithDatabase();
        return money;
    }

    // Adds 1 bill or coin to the vending machine
    public void addMoney(double moneyValue) {
        if (!isValidMoney(moneyValue)) {
            throw new InvalidCurrencyException(
                    "Currency of: " + moneyValue + " does not exist.");
        }

        moneyCurrentlyProcessing.add(moneyValue);
        currentSold += moneyValue;
    }

    public boolean isValidMoney(double moneyValue) {
        for (var currency : validCurrencies) {
            if (moneyValue == currency) {
                return true;
            }
        }
        return false;
    }

    public double giveChange(Item itemCurrentlySelling) {
        syncMoneyWithDatabase();

        // initialise needed change
        double price = itemCurrentlySelling.getPrice();
        double neededChange = currentSold - price;
        double currentChange = 0;
        if (neededChange < 0.01) {
            putMoneyInBank();
            return 0;
        }

        //initialise temporary array
        List<Double> neededMoney = new ArrayList<>();

        for (var currentMoney : money) {
            double currentBillOrCoinValue = currentMoney.getValue();
            int billCount = currentMoney.getQuantity();
            int billsOrCoinsNeeded = (int) ((neededChange - currentChange) /
                    currentBillOrCoinValue);
            int billsConsumed = Math.min(billsOrCoinsNeeded, billCount);

            for (int i = 0; i < billsConsumed; i++) {
                neededMoney.add(currentBillOrCoinValue);
            }
            currentChange += billsConsumed * currentBillOrCoinValue;
            if (isEnoughChange(neededChange, currentChange)) {
                putMoneyInBank();
                spitOutMoney(neededMoney);
                return neededChange;
            }
        }

        throw new NotSufficientChangeException(
                "No sufficient change. Change needed: " +
                        String.format("%1.2f", neededChange) +
                        ". Maximum change acquired: " +
                        String.format("%1.2f", currentChange) + ".");
    }

    private void putMoneyInBank() {
        for (var m : moneyCurrentlyProcessing) {
            for (var currentMoney : money) {
                if (m == currentMoney.getValue()) {
                    currentMoney.setQuantity(currentMoney.getQuantity() + 1);
                    moneyService.save(currentMoney);
                }
            }
        }
    }

    private void spitOutMoney(List<Double> needeedMoney) {
        for (var currentNeededMoney : needeedMoney) {
            for (var currentMoney : money) {
                if (currentNeededMoney == currentMoney.getValue()) {
                    currentMoney.setQuantity(currentMoney.getQuantity() - 1);
                }
            }
        }

        for (var currentMoney : money) {
            moneyService.save(currentMoney);
        }
    }

    // Checks if "currentChange" is enough for the client
    private boolean isEnoughChange(double neededChange, double currentChange) {
        return abs(neededChange - currentChange) < 0.01;
    }

    public void resetProcessingState() {
        currentSold = 0;
        moneyCurrentlyProcessing.clear();
    }
}
