package com.saral.accountService.services;

import com.saral.accountService.dtos.AccountDto;
import com.saral.accountService.dtos.Response;
import com.saral.accountService.dtos.Status;
import com.saral.accountService.repositories.AccountRepo;
import com.saral.accountService.repositories.models.Account;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService extends ResponseService<Response<List<AccountDto>>>{

    @Autowired
    AccountRepo accountRepo;

    @Override
    public Response<List<AccountDto>> handle() throws Exception {

        var user = SecurityContextHolder.getContext().getAuthentication().getName();

        Iterable<Account> accounts = Optional.ofNullable(
                accountRepo.findAllById(List.of(user))).orElseThrow(
                () -> new NotFoundException("user not found"));

        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account account:accounts){
            accountDtos.add(convertToDto(account));
        }

        Response<List<AccountDto>> response = new Response<>(new Status(HttpStatus.OK), accountDtos);
        return response;
    }

    @Override
    public Response<List<AccountDto>> handleException(Exception ex) {
        return new Response<>(new Status(HttpStatus.INTERNAL_SERVER_ERROR), null);
    }

    private AccountDto convertToDto(Account account){
        return new AccountDto(account.getNumber(), account.getName(), account.getBalance());
    }


}
