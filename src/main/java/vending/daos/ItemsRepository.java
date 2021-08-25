package vending.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import vending.entities.Item;

public interface ItemsRepository extends JpaRepository<Item, Integer> {
}
