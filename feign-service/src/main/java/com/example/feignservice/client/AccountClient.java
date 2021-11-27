package com.example.feignservice.client;

import com.example.feignservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("account-service")
public interface AccountClient {
    @GetMapping("api/account")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts();

    @GetMapping("api/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccount(@PathVariable Long id);

    @PostMapping("api/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody AccountDTO accountDTO);
}