package org.example.daos;

import org.example.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

  Customer create(Customer customer);

  Optional<Customer> findById(Long id);

  List<Customer> findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);
}
