package com.saral.accountService.controllers;

import com.saral.accountService.dtos.AccountDto;
import com.saral.accountService.dtos.Response;
import com.saral.accountService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    private ResponseEntity<Response<List<AccountDto>>> accounts(){
        var response = accountService.handleResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
