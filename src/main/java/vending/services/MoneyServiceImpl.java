package vending.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vending.daos.MoneyRepository;
import vending.entities.Money;
import vending.exceptions.CurrencyInexistentException;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyServiceImpl implements MoneyService {
    private final MoneyRepository moneyRepository;

    @Autowired
    public MoneyServiceImpl(MoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    @Override
    public List<Money> findAll() {
        return moneyRepository.findAll();
    }

    @Override
    public Money findById(int theId) {
        Optional<Money> result = moneyRepository.findById(theId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CurrencyInexistentException("Money with id " + theId + " " +
                "does not exist.");
    }

    @Override
    public void save(Money theMoney) {
        moneyRepository.save(theMoney);
    }

    @Override
    public void deleteById(int theId) {
        moneyRepository.deleteById(theId);
    }
}
