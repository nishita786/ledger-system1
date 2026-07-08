package com.ledger.ledger_system.service;

import com.ledger.ledger_system.entity.Account;
import com.ledger.ledger_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Integer accountCode) {

        return accountRepository
                .findByAccountCode(accountCode)
                .orElseThrow(() ->
                        new RuntimeException("Account not found : " + accountCode));
    }

    public boolean exists(Integer accountCode) {
        return accountRepository.findByAccountCode(accountCode).isPresent();
    }
}