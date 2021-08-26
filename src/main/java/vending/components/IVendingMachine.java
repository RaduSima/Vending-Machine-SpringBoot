package vending.components;

import vending.entities.Item;
import vending.entities.Money;
import vending.exceptions.*;

import java.util.List;

public interface IVendingMachine {
    // Shows available items
    List<Item> showItems();

    Item showItem(int itemId);

    // Shows currently stored bills, coins and total money
    List<Money> showMoney();

    // Selects a product
    Item selectProduct(int UID);

    // Enter payment processing mode
    String processPayment(double moneyValue);

    // Returns an item to the client
    Item returnProduct(Item item);

    void resetProcessingState();
}
