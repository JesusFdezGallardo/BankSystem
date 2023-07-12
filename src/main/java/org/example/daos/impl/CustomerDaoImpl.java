package org.example.daos.impl;

import org.example.daos.CustomerDao;
import org.example.daos.impl.utils.ConnectionDataSource;
import org.example.model.Customer;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

  private Connection connection;

  public CustomerDaoImpl() {
    try{
      connection = ConnectionDataSource.getConnection();
    }catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public Customer create(Customer customer)  {
    String query = "INSERT INTO customer (first_name, last_name, birth_date) VALUES (?, ?, ?)";
      try {
      PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, customer.getFirstName());
      preparedStatement.setString(2, customer.getLastName());
      preparedStatement.setString(3, customer.getBirthDate().toString());
      preparedStatement.executeUpdate();

      var generatedKeys = preparedStatement.getGeneratedKeys();
      if (generatedKeys.next()) {
        Long id = generatedKeys.getLong(1);
        customer.setId(id);
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }


    return customer;
  }

  @Override
  public Optional<Customer> findById(Long id) {
    String query = "SELECT * FROM customer WHERE id = "+id;
    try {
      Statement statement = connection.createStatement();
      var rs = statement.executeQuery(query);
      Customer customer;
      if (rs.next()){
        customer = new Customer();
        customer.setId(id);
        customer.setFirstName(rs.getString(2));
        customer.setLastName(rs.getString(3));

       Date birthDate = rs.getDate(4);
        if (birthDate != null) {
          customer.setBirthDate(birthDate.toLocalDate());
        }

        return Optional.of(customer);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  @Override
  public List<Customer> findByFirstName(String firstName) {
    return null;
  }

  @Override
  public List<Customer> findByLastName(String lastName) {
    return null;
  }
}
