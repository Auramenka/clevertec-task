package org.clevertec.checkcrud.service;

import org.clevertec.checkcrud.db.ConnectionManager;
import org.clevertec.checkcrud.model.DiscountCard;
import org.clevertec.checkcrud.repository.DiscountCardRepository;
import org.clevertec.checkcrud.repository.DiscountCardRepositoryImpl;

public class DiscountCardServiceImpl implements DiscountCardService {

    private final ConnectionManager connection = new ConnectionManager();
    private final DiscountCardRepository discountCardRepository = new DiscountCardRepositoryImpl(connection);

    @Override
    public DiscountCard getById(Integer integer) {
        return discountCardRepository.getById(integer);
    }

    @Override
    public DiscountCard save(DiscountCard discountCard) {
        return discountCardRepository.save(discountCard);
    }

    @Override
    public DiscountCard update(DiscountCard discountCard) {
        return discountCardRepository.update(discountCard);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return discountCardRepository.deleteById(integer);
    }
}
