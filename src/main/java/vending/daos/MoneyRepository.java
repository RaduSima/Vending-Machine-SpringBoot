package vending.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import vending.entities.Money;

public interface MoneyRepository extends JpaRepository<Money, Integer> {
}

