package org.example.daos.impl;

import org.example.daos.AccountDao;
import org.example.daos.impl.utils.ConnectionDataSource;
import org.example.model.Account;
import org.example.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
    private Connection connection;

    public AccountDaoImpl() {
        try{
            connection = ConnectionDataSource.getConnection();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Account create(Account account) {
        String query = "INSERT INTO account (iban, balance, createdAt) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, account.getIban());
            preparedStatement.setBigDecimal(2, account.getBalance());
            preparedStatement.setString(3, account.getCreatedAt().toString());
            preparedStatement.executeUpdate();

            List<Long> accountHoldersId = account.getAccountHolders()
                    .stream()
                    .map(Customer::getId)
                    .toList();
            addAccountHolders(account.getIban(), accountHoldersId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return account;
    }

    @Override
    public Optional<Account> findByIban(String iban) {
        return Optional.empty();
    }

    @Override
    public void addAccountHolders(String iban, List<Long> accountHoldersId){
        String accountHoldersQuery = "INSERT INTO account_holders VALUES(?,?)";
        PreparedStatement psAccountHolders = null;
        try {
            psAccountHolders = connection.prepareStatement(accountHoldersQuery);
            for (var holder: accountHoldersId) {
                var i = 0;
                psAccountHolders.setLong(++i,holder);
                psAccountHolders.setString(++i,iban);
                var count = psAccountHolders.executeUpdate();
                if(count != 1){
                    System.err.println("Error: unable to register account holder");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
