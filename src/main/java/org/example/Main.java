package org.example;

import org.example.daos.CustomerDao;
import org.example.daos.impl.CustomerDaoImpl;
import org.example.model.Customer;

import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    CustomerDao customerDao = new CustomerDaoImpl();
//    Customer customer = new Customer("Alfredo", "Rueda", LocalDate.of(1978, 07, 19));
    Optional<Customer> salida = customerDao.findById(1L);
    System.out.println(salida.toString());
  }
}