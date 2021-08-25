package vending.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vending.entities.Item;
import vending.entities.Money;
import vending.exceptions.*;

import java.util.List;

@Component
public class VendingMachineImpl implements IVendingMachine {
    BankStorage bank;
    VendingStorage vendingStorage;
    boolean isSellingItem = false;

    @Autowired
    public VendingMachineImpl(BankStorage bank, VendingStorage vendingStorage) {
        this.bank = bank;
        this.vendingStorage = vendingStorage;
    }

    @Override
    public List<Item> showItems() {
        return vendingStorage.showItems();
    }

    @Override
    public Item showItem(int itemId) throws ItemInexistentException {
        return vendingStorage.showItem(itemId);
    }

    @Override
    public List<Money> showMoney() {
        return bank.showMoney();
    }

    @Override
    public Item selectProduct(int UID)
            throws SoldOutException, ItemInexistentException,
            ItemAlreadySelectedException {
        if (isSellingItem) {
            throw new ItemAlreadySelectedException("There is already an item " +
                    "selected. You cannot select another until payment is " +
                    "completed.");
        }
        Item item = vendingStorage.getItem(UID);
        isSellingItem = true;
        vendingStorage.setItemCurrentlySelling(item);
        return item;
    }


    @Override
    public String processPayment(double moneyValue)
            throws NoItemSelectedException, InvalidCurrencyException,
            NotSufficientChangeException {
        System.out.println("kysssssssss");
        if (!isSellingItem) {
            throw new NoItemSelectedException("No item selected: please " +
                    "select an item first.");
        }
        if (!bank.isValidMoney(moneyValue)) {
            throw new InvalidCurrencyException(
                    "Currency of " + String.format("%.2f", moneyValue) +
                            " does not exist.");
        }

        bank.addMoney(moneyValue);
        System.out.println(bank.getCurrentSold() + " " +
                vendingStorage.getItemCurrentlySelling().getPrice());

        if (bank.getCurrentSold() >=
                vendingStorage.getItemCurrentlySelling().getPrice()) {
            System.out.println("kys");
            try {
                double change =
                        bank.giveChange(vendingStorage.getItemCurrentlySelling());
                Item item = returnProduct(vendingStorage.getItemCurrentlySelling());
                return "Here is your product: " + item.toStringWithoutQunatity() +
                        "\nHere is your change: " + String.format("%.2f", change);
            } catch (NotSufficientChangeException e) {
                resetProcessingState();
                throw e;
            }
        }

        return "You have introduced " + moneyValue + ". You need to " +
                "introduce " + String.format("%.2f",
                vendingStorage.getItemCurrentlySelling().getPrice() -
                        moneyValue) + " more.";
    }

    @Override
    public Item returnProduct(Item item) {
        resetProcessingState();
        return vendingStorage.removeItem(item);
    }

    public void resetProcessingState() {
        isSellingItem = false;
        bank.resetProcessingState();
        vendingStorage.resetProcessingState();
    }
}
