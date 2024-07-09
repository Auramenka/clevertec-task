package org.clevertec.checkcrud.repository;

import org.clevertec.checkcrud.db.ConnectionManager;
import org.clevertec.checkcrud.db.SQLRequests;
import org.clevertec.checkcrud.model.Product;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private ConnectionManager connectionManager;

    public ProductRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connectionManager.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLRequests.GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                Product product = new Product();
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity_in_stock"));
                product.setWholesale(resultSet.getBoolean("wholesale_product"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(Integer integer) {
        Product product = new Product();
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setDescription(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getInt(4));
                product.setWholesale(resultSet.getBoolean(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product save(Product product) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.SAVE_PRODUCT)) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesale());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SQLRequests.UPDATE_PRODUCT)){
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesale());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean deleteById(Integer integer) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SQLRequests.DELETE_PRODUCT_BY_ID)){
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
