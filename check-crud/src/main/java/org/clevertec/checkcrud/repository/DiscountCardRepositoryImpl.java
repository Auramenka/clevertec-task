package org.clevertec.checkcrud.repository;

import org.clevertec.checkcrud.db.ConnectionManager;
import org.clevertec.checkcrud.db.SQLRequests;
import org.clevertec.checkcrud.model.DiscountCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {

    private ConnectionManager connectionManager;

    public DiscountCardRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public DiscountCard getById(Integer integer) {
        DiscountCard discountCard = new DiscountCard();
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.GET_DISCOUNT_CARD_BY_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                discountCard.setNumber(resultSet.getInt(2));
                discountCard.setDiscount(resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

    @Override
    public DiscountCard save(DiscountCard discountCard) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.SAVE_DISCOUNT_CARD)) {
            preparedStatement.setInt(1, discountCard.getNumber());
            preparedStatement.setDouble(2, discountCard.getDiscount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

    @Override
    public DiscountCard update(DiscountCard discountCard) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SQLRequests.UPDATE_DISCOUNT_CARD)){
            preparedStatement.setInt(1, discountCard.getNumber());
            preparedStatement.setInt(2, discountCard.getDiscount());
            preparedStatement.setInt(3, discountCard.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

    @Override
    public boolean deleteById(Integer integer) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SQLRequests.DELETE_DISCOUNT_CARD_BY_ID)){
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
