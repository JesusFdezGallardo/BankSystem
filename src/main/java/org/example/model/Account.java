package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Account {

  //TODO: Generar los id articial
  private String iban;

  private BigDecimal balance;

  private LocalDateTime createdAt;

  private List<Customer> accountHolders;

  public void deposit(BigDecimal amount) {
    balance = balance.add(amount);
  }

  public void withdraw(BigDecimal amount) {
    if(balance.compareTo(amount) < 0)
      throw new IllegalArgumentException("Not enough balance");

    balance = balance.subtract(amount);
  }

}
