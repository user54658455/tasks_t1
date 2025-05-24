package com.transaction.service;

import com.transaction.repository.AccountRepository;
import com.common.model.Account;
import com.common.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@RequiredArgsConstructor    //why this is here?
public class AccountService {

    @Value("${authServiceAddress}")
    private String authServiceAddress;

    private  final AccountRepository accountRepository;

    public Account createAccount(String clientId) {

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(authServiceAddress + "/auth/findclientbyid")
                .queryParam("id", clientId);

        ResponseEntity<Client> clientOpt = template.exchange(
                uriBuilder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                Client.class
        );

        Account account = new Account();
        clientOpt.getBody().getAccounts().add(account);

        template.postForLocation(authServiceAddress + "/auth/renewclient", clientOpt.getBody());

        return null;

    }

    public Optional<Account> findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

}