package org.example;

import org.example.daos.AccountDao;
import org.example.daos.CustomerDao;
import org.example.daos.impl.AccountDaoImpl;
import org.example.daos.impl.CustomerDaoImpl;
import org.example.model.Account;
import org.example.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Main {

  private static CustomerDao customerDao  = new CustomerDaoImpl();
  private static AccountDao accountDao = new AccountDaoImpl();

  public static void main(String[] args) {
   // customerMain();
    accountMain();
  }

  private static void customerMain(){

    Customer customer = new Customer("Alfredo", "Rueda", LocalDate.of(1978, 07, 19));
    Optional<Customer> salidaById = customerDao.findById(1L);
    //var salida = customerDao.create(customer);
    System.out.println("Filtro por id 1: "+salidaById.toString());

    System.out.println("Filtro por firstname David: ");
    var customers = customerDao.findByFirstName("David");
    for (var c : customers) {
      System.out.println(c.toString());
    }

    System.out.println("Filtro por lastname Iruela: ");
    var customers2 = customerDao.findByLastName("Iruela");
    for (var c : customers2) {
      System.out.println(c.toString());
    }
  }

  private static void accountMain(){

    Optional<Customer> c1 = customerDao.findById(1L);
    Optional<Customer> c2 = customerDao.findById(2L);

    var accountHolders = new ArrayList<Customer>();

    accountHolders.add(c1.get());
    accountHolders.add(c2.get());

    Account account = new Account();
    account.setIban("ES210045123456789");
    account.setAccountHolders(accountHolders);
    account.setBalance(BigDecimal.valueOf(2000000));
    account.setCreatedAt(LocalDateTime.now());

    accountDao.create(account);

  }
}