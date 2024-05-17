package com.example.bankingapp.impl;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountDao;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountDao.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountDao.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
       Account account =  accountDao.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

       double total = account.getBalance() + amount;
        account.setBalance(total);
       Account savedAccount = accountDao.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountDao.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

            if(account.getBalance() < amount){
                throw new RuntimeException("Insufficient amount");
            }

            double total = account.getBalance() - amount;
            account.setBalance(total);
            Account savedAccount = accountDao.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
      List<Account> accounts =  accountDao.findAll();
        return accounts.stream().map((account -> AccountMapper.mapToAccountDto(account)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountDao.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

        accountDao.deleteById(id);

    }
}
