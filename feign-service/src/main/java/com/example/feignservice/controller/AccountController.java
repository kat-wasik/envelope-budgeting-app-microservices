package com.example.feignservice.controller;

import com.example.feignservice.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.feignservice.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }
}
