package org.example.daos;

import org.example.model.Account;

import java.util.Optional;

public interface AccountDao {

  Account create(Account account);

  Optional<Account> findByIban(String iban);


}
