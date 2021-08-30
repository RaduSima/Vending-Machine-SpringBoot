package vending.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vending.components.IVendingMachine;
import vending.entities.Item;
import vending.entities.Money;

import java.util.List;

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
    public Item getItem(@PathVariable int itemId) {

        return vendingMachine.showItem(itemId);
    }

    @GetMapping("/money")
    public List<Money> getMoney() {
        return vendingMachine.showMoney();
    }

    @DeleteMapping("/items")
    public Item selectItem(@RequestBody int itemId) {
        return vendingMachine.selectProduct(itemId);
    }

    @PutMapping("/money")
    public String processPayment(@RequestBody double moneyValue) {
        return vendingMachine.processPayment(moneyValue);
    }
}
