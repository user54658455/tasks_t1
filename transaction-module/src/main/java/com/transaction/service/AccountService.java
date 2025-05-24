package com.transaction.service;

import com.transaction.repository.AccountRepository;
//import com.bankapp.repository.ClientRepository;
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
        //send client id to auth service and get client entity here
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


        //create to this client entity account here (should i save client entity here after that? seems like i do not have clients here)
        Account account = new Account();
        clientOpt.getBody().getAccounts().add(account);
        //System.out.println(clientOpt.getBody());


        //send renewed client entity (with account) to auth service
        // authServiceAddress + "/auth/renewclient"
        template.postForLocation(authServiceAddress + "/auth/renewclient", clientOpt.getBody());
        //template.postForLocation(authServiceAddress + "/auth/renewclient", clientOpt);
//        ResponseEntity<Client> response = template.exchange(
//                authServiceAddress + "/auth/renewclient",
//                HttpMethod.POST,
//                clientOpt.getBody(),
//                new ParameterizedTypeReference<Client>() {}
//        );
//        return response.getBody();


        //replace client entity with account on auth service




        //get client as entity here (and put it into "clientOpt") from auth service

//        String clientOpt = template.getForEntity(authServiceAddress + "/findclientbyid", String.class).getBody();
//        template.postForLocation(authServiceAddress + "/findclientbyid", clientId);
//        Account request = new Account();
//        HttpEntity<Account> account = new HttpEntity<>(request);

        //System.out.println(clientOpt);

        return null;

        //Optional<Client> clientOpt = ClientRepository.findById(clientId);
//        if (clientOpt.isEmpty()) {
//            throw new RuntimeException("Client not found");
//        }
//
//        Account account = new Account();
//        clientOpt.get().getAccounts().add(account);
//        return accountRepository.save(account);
    }

    public Optional<Account> findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

}