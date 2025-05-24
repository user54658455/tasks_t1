package com.transaction.controller;

import com.common.model.Account;
import com.common.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Сервис транзакций.", description = "API для перевода средств между счетами клиентов.")
public class TransactionController {
    private Client recipientClient;
    private Account recipientAccount;

    @Value("${authServiceAddress}")
    private String authServiceAddress;

    // 1️⃣ Получить список всех клиентов перед переводом
    @Operation(summary = "Получение списка всех пользователей системы.",
            description = "Выводит информацию о всех пользователях системы вместе с информацией о привязанных аккаунтах.")
    @GetMapping("/clients")
    public List<Client> clients() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Client>> response = template.exchange(
                authServiceAddress + "/auth/getallclients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {}
        );
        return response.getBody();
    }



    // 2️⃣ Выбрать получателя перевода по телефону и номеру счета
    @Operation(summary = "Выбор получателя перевода средств.",
            description = "Устанавливаем логин получателя и номер его счета для перевода средств. Перед выполнением нужно войти в систему.")
    @PostMapping("/select-recipient")
    public String selectRecipient(
            @Parameter(
                    required = true,
                    name = "username",
                    description = "логин получателя перевода",
                    example = "user2"
            )
            @RequestParam String username,

            @Parameter(
                    required = true,
                    name = "accountNumber",
                    description = "номер счета получателя перевода",
                    example = "93a97218648c"
            )
            @RequestParam String accountNumber) {

        RestTemplate template= new RestTemplate();
        boolean res = template.getForObject(authServiceAddress + "/auth/isloggedin", boolean.class);

        if (!res/*.equals("не аутентифицирован")*/) {
            return "❌ Ошибка: Сначала войдите в систему!";
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(authServiceAddress + "/auth/findbyusername")
                .queryParam("username", username);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Client> response = template.exchange(
                uriBuilder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                Client.class
        );

        if (response.getBody().toString().isEmpty()) {
            return "❌ Ошибка: Получатель не найден!";
        }

        Optional<Account> recipientAccountOpt = response.getBody().getAccounts()
                .stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();

        if (recipientAccountOpt.isEmpty()) {
            return "❌ Ошибка: У получателя нет такого счета!";
        }

        this.recipientClient = response.getBody();
        this.recipientAccount = recipientAccountOpt.get();

        return "✅ Получатель выбран: " + recipientClient.getFullName() + " (Счет: " + recipientAccount.getAccountNumber() + ")";
    }



    // 3️⃣ Выполнить перевод (указать сумму и изменить баланс)
    @Operation(summary = "Перевод средств.",
            description = "Устанавливает сумму и переводит средства на аккаунт другого пользователя. Перед выполнением необходимо войти в систему и выбрать получателя.")
    @PostMapping("/transfer")
    public String transfer(
            @Parameter(
                    required = true,
                    name = "amount",
                    description = "сумма для перевода",
                    example = "20"
            )
            @RequestParam double amount) {

        RestTemplate template= new RestTemplate();
        boolean res = template.getForObject(authServiceAddress + "/auth/isloggedin", boolean.class);

        if (!res) {
            return "❌ Ошибка: Сначала войдите в систему!";
        }

        if (recipientClient == null || recipientAccount == null) {
            return "❌ Ошибка: Сначала выберите получателя!";
        }

        ResponseEntity<Client> response = template.exchange(
                authServiceAddress + "/auth/getloggedinclient",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Client>() {}
        );

        Optional<Account> senderAccountOpt = response.getBody().getAccounts().stream().findFirst();
        if (senderAccountOpt.isEmpty()) {
            return "❌ Ошибка: У вас нет счета!";
        }

        Account senderAccount = senderAccountOpt.get();

        if (senderAccount.getBalance() < amount) {
            return "❌ Ошибка: Недостаточно средств на счете!";
        }

        // Обновляем балансы
        double newSenderAccountBalance = senderAccount.getBalance() - amount;
        senderAccount.setBalance(newSenderAccountBalance);
        double newRecipientAccountBalance = recipientAccount.getBalance() + amount;
        recipientAccount.setBalance(newRecipientAccountBalance);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilderSenderAccount = UriComponentsBuilder
                .fromHttpUrl(authServiceAddress + "/auth/clientaccountsetbalance")
                .queryParam("id", senderAccount.getId())
                .queryParam("amount", newSenderAccountBalance);

        ResponseEntity<Client> responseSenderAccount = template.exchange(
                uriBuilderSenderAccount.toUriString(),
                HttpMethod.POST,
                requestEntity,
                Client.class
        );

        UriComponentsBuilder uriBuilderRecipientAccount = UriComponentsBuilder
                .fromHttpUrl(authServiceAddress + "/auth/clientaccountsetbalance")
                .queryParam("id", recipientAccount.getId())
                .queryParam("amount", newRecipientAccountBalance);

        ResponseEntity<Client> responseRecipientAccount = template.exchange(
                uriBuilderRecipientAccount.toUriString(),
                HttpMethod.POST,
                requestEntity,
                Client.class
        );

        return "✅ Перевод завершен! " + amount + "₽ переведено на счет " + recipientAccount.getAccountNumber();
    }

}