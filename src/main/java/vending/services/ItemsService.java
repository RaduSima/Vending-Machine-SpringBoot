package vending.services;

import vending.entities.Item;
import vending.exceptions.*;

import java.util.List;

public interface ItemsService {
    public List<Item> findAll();

    public Item findById(int theId);

    public void save(Item theItem);

    public void deleteById(int theId);
}
