package org.example.daos.impl;

import org.example.daos.TransferDao;
import org.example.daos.impl.utils.ConnectionDataSource;
import org.example.model.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class TransferDaoImpl implements TransferDao {
    private Connection connection;
    public TransferDaoImpl() {
        try{
            connection = ConnectionDataSource.getConnection();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Transfer create(Transfer transfer) {
        String query = "INSERT INTO transfers (account_id_src, account_id_dst,issuer_id,date_operation,amount) VALUES (?, ?, ?, ?, ?)";
        try {
            int i = 0;
            Timestamp timestamp = Timestamp.valueOf(transfer.getTransferDate());
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++i, transfer.getAccountSource().getIban());
            preparedStatement.setString(++i, transfer.getAccountDestination().getIban());
            preparedStatement.setLong(++i, transfer.getIssuer().getId());
            preparedStatement.setTimestamp(++i, timestamp);
            preparedStatement.setBigDecimal(++i, transfer.getAmount());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                Long id = generatedKeys.getLong(1);
                transfer.setId(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transfer;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Transfer> findTransfersFrom(String ibanAccountSource) {
        return null;
    }

    @Override
    public List<Transfer> findTransfersTo(String ibanAccountDestination) {
        return null;
    }
}
