package org.clevertec.checkcrud.service;


import org.clevertec.checkcrud.db.ConnectionManager;
import org.clevertec.checkcrud.model.Product;
import org.clevertec.checkcrud.repository.ProductRepository;
import org.clevertec.checkcrud.repository.ProductRepositoryImpl;


public class ProductServiceImpl implements ProductService {

    private final ConnectionManager connection = new ConnectionManager();
    private final ProductRepository productRepository = new ProductRepositoryImpl(connection);

    @Override
    public Product getById(Integer integer) {
        return productRepository.getById(integer);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return productRepository.deleteById(integer);
    }
}
