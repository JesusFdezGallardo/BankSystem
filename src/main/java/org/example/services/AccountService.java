package org.example.services;

import org.example.model.Account;

import java.util.Optional;

public interface AccountService {

  Account create(Account account);

  Optional<Account> findByIban(String iban);
}
