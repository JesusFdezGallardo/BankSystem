package org.example.services.impl;

import org.example.daos.AccountDao;
import org.example.daos.CustomerDao;
import org.example.daos.TransferDao;
import org.example.model.Account;
import org.example.model.Customer;
import org.example.model.Transfer;
import org.example.services.TransferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TransferServiceImpl implements TransferService {

  private CustomerDao customerDao;

  private AccountDao accountDao;

  private TransferDao transferDao;

  public TransferServiceImpl() {
    //TODO: instanciar las implementaciones de los daos
  }

  @Override
  public Transfer issueTransfer(Long issuerId, String ibanAccountSource, String ibanAccountDestination, BigDecimal amount) {
    Optional<Customer> issuerCustomerOptional = customerDao.findById(issuerId);
    Optional<Account> accountSourceOptional = accountDao.findByIban(ibanAccountSource);
    Optional<Account> accountDestinationOptional = accountDao.findByIban(ibanAccountDestination);

    Customer issuer = issuerCustomerOptional.get();

    Account accountSource = accountSourceOptional.get();
    Account accountDestination = accountDestinationOptional.get();

    if (!accountSource.getAccountHolders().contains(issuer)) {
      throw new IllegalArgumentException("Issuer is not account holder of the source bank account");
    }

    if (accountSource.getBalance().compareTo(amount) < 0) {
       throw new IllegalArgumentException("Source bank account does not have enough balance");
    }

    accountSource.setBalance(accountSource.getBalance().subtract(amount));
    accountDestination.setBalance(accountDestination.getBalance().add(amount));

    Transfer transfer = new Transfer(issuer,accountSource,accountDestination,amount);

    return transferDao.create(transfer);
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
