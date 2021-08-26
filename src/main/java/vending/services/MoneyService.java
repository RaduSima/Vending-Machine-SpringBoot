package vending.services;

import vending.entities.Item;
import vending.entities.Money;
import vending.exceptions.CurrencyInexistentException;

import java.util.List;

public interface MoneyService {
    public List<Money> findAll();

    public Money findById(int theId);

    public void save(Money theMoney);

    public void deleteById(int theId);
}
