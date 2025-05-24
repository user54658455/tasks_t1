package com.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Client {

    @Schema(
            description = "идентификатор пользователя в системе, формируется автоматически при создании пользователя",
            example = "4bfb8703-e572-4a8c-b333-bbd888877ad0"
    )
    private String id;

    @Schema(
            description = "ФИО пользователя",
            example = "newName newName newName"
    )
    private String fullName;

    @Schema(
            description = "номер телефона пользователя, без маски",
            example = "+79876543210"
    )
    private String phone;

    @Schema(
            description = "логин для входа в систему",
            example = "newUsername"
    )
    private String username;

    @Schema(
            description = "пароль для входа в систему",
            example = "newPassword"
    )
    private String password;

    @Schema(
            description = "список аккаунтов, привязанных к пользователю",
            example ="      {\n" +
                    "        \"id\": \"b784545a-aa8b-4352-a75e-03b75f6eaf71\",\n" +
                    "        \"accountNumber\": \"d96df37bbd6c\",\n" +
                    "        \"cardNumber\": \"23e2a7be05fc47a3\",\n" +
                    "        \"balance\": 6165\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"id\": \"a4610003-024f-486a-a63b-bf5349f2782d\",\n" +
                    "        \"accountNumber\": \"e1fcdfec2589\",\n" +
                    "        \"cardNumber\": \"a6929ee467f64089\",\n" +
                    "        \"balance\": 1567\n" +
                    "      }\n"
    )
    private List<Account> accounts = new ArrayList<>();

//RestTemplate template = new RestTemplate();
//String res = template.getForEntity("http://localhost:8030/hello", String.class).getBody();

    public Client(String fullName, String phone, String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
