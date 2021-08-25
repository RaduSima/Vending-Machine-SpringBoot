package vending.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vending.components.IVendingMachine;
import vending.entities.Item;
import vending.entities.Money;
import vending.exceptions.*;

import java.util.List;

// TODO HANDLE EXCEPTIONS
// TODO REFRACTOR LINKS
// TODO CREATE MONEY CONTROLLER
// TODO

@RestController
public class VendingMachineRestController {
    private final IVendingMachine vendingMachine;

    @Autowired
    public VendingMachineRestController(IVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return vendingMachine.showItems();
    }

    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable int itemId)
            throws ItemInexistentException {

        return vendingMachine.showItem(itemId);
    }

    @GetMapping("/money")
    public List<Money> getMoney() {
        return vendingMachine.showMoney();
    }

    @GetMapping("/items/select")
    public Item selectItem(@RequestBody int itemId)
            throws ItemInexistentException, SoldOutException,
            ItemAlreadySelectedException {
        return vendingMachine.selectProduct(itemId);
    }

    @GetMapping("/money/add")
    public String processPayment(@RequestBody double moneyValue)
            throws NoItemSelectedException, InvalidCurrencyException,
            NotSufficientChangeException {
        return vendingMachine.processPayment(moneyValue);
    }
}
