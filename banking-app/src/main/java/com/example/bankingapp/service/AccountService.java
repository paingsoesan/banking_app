package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);
}
