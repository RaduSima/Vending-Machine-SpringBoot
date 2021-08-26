package vending.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vending.daos.ItemsRepository;
import vending.entities.Item;
import vending.exceptions.ItemInexistentException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Item findById(int theId) {
        Optional<Item> result = itemsRepository.findById(theId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ItemInexistentException(
                "Item with id " + theId + " does not " +
                        "exist.");
    }

    @Override
    public void save(Item theItem) {
        itemsRepository.save(theItem);
    }

    @Override
    public void deleteById(int theId) {
        itemsRepository.deleteById(theId);
    }
}
