package org.example.daos;

import org.example.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao {

  Account create(Account account);

  Optional<Account> findByIban(String iban);

  void addAccountHolders(String iban, List<Long> accountHoldersId);

  /*
  ToDo Borrar un titular, Modificar cuenta ... CRUD
   */


}
