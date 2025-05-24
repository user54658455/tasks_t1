package com.transaction.controller;

import com.transaction.repository.AccountRepository;
import com.transaction.service.AccountService;
import com.common.model.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Сервис аккаунтов.", description = "API для создания аккаунтов для зарегистрированных пользователей.")
public class AccountController {
    public AccountService accountService;
    public AccountRepository accountRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }



    @Operation(summary = "Создание аккаунта.",
            description = "Создает аккаунт для зарегистрированного в системе пользователя.")
    @PostMapping("/create")
    public Account create(
            @Parameter(
                    required = true,
                    name = "clientId",
                    description = "идентификатор пользователя, можно узнать из ответа на запрос получения списка всех пользователей",
                    example = "4bfb8703-e572-4a8c-b333-bbd888877ad0"
            )
            @RequestParam String clientId) {
        return accountService.createAccount(clientId);
    }



    @Operation(summary = "Сохранение аккаунта.",
            description = "Сохраняет созданный аккаунт, используется при автоматической генерации данных.")
    @PostMapping("/saveaccount")
    public ResponseEntity<String> saveaccount(
            @RequestBody Account account) {
        accountRepository.save(account);
        return ResponseEntity.ok("Item received successfully");
    }

}