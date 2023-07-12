package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transfer {

  private Long id;

  private Account accountSource;

  private Account accountDestination;

  private Customer issuer;

  private LocalDateTime transferDate;

  private BigDecimal amount;

  public Transfer(Customer issuer, Account accountSource, Account accountDestination, BigDecimal amount) {
    this.issuer = issuer;
    this.accountSource = accountSource;
    this.accountDestination = accountDestination;
    this.amount = amount;
    this.transferDate = LocalDateTime.now();
  }
}
