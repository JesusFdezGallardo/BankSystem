package org.example.services;

import org.example.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

  Customer create(Customer customer);

  Optional<Customer> findById(Long id);

  List<Customer> findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);
}
