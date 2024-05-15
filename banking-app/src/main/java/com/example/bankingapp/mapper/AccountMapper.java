package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;


public class AccountMapper {

    //database nae koi ma tt chin lox input object ko Dto htl ayin htae pee Dto htl ka nay mah entity htl pyan htae tar

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
