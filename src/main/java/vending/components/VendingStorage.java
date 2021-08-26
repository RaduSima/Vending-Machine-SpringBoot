package vending.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vending.entities.Item;
import vending.exceptions.ItemInexistentException;
import vending.exceptions.SoldOutException;
import vending.services.ItemsService;

import java.util.List;

@Component
public class VendingStorage {
    private List<Item> items;
    private Item itemCurrentlySelling;

    private final ItemsService itemsService;

    @Autowired
    public VendingStorage(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    public Item getItemCurrentlySelling() {
        return itemCurrentlySelling;
    }

    public void setItemCurrentlySelling(Item itemCurrentlySelling) {
        this.itemCurrentlySelling = itemCurrentlySelling;
    }

    // shows info about all available items
    public List<Item> showItems() {
        syncItemsWithDatabase();
        return items;
    }

    public Item showItem(int itemId) {
        return itemsService.findById(itemId);
    }

    // gets an item from the items map, based on its UID
    public Item getItem(int UID) {
        syncItemsWithDatabase();
        for (var currentItem : items) {
            // if UID exists
            if (UID == currentItem.getId()) {
                // if the item is still in stock
                if (currentItem.getQuantity() > 0) {
                    return currentItem;
                }
                throw new SoldOutException(
                        "Item with ID " + UID + " sold out.");
            }
        }

        throw new ItemInexistentException(
                "Item with ID " + UID + " does not exist.");
    }

    // removes an item from the items map
    public Item removeItem(Item item) {
        item.setQuantity(item.getQuantity() - 1);
        itemsService.save(item);

        return item;
    }

    private void syncItemsWithDatabase() {
        items = itemsService.findAll();
    }

    public void resetProcessingState() {
        itemCurrentlySelling = null;
    }
}
