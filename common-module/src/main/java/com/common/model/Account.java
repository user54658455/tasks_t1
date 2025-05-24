package com.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class Account {

    @Schema(
            description = "идентификатор аккаунта в системе, формируется автоматически при создании аккаунта",
            example = "380ebd0d-fd7a-4615-a490-cf069a8f8d67"
    )
    private String id;

    @Schema(
            description = "номер аккаунта, формируется автоматически при создании аккаунта",
            example = "b1dd68e3f038"
    )
    private String accountNumber;

    @Schema(
            description = "номер карты, привязанной к аккаунту, формируется автоматически при создании аккаунта",
            example = "27654b7a413c4ca0"
    )
    private String cardNumber;

    @Schema(
            description = "баланс средств на аккаунте",
            example = "4571"
    )
    private double balance;



    public Account() {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        this.cardNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    @Override
//    public String toString() {
//        return "Item{id=" + id + ", accountNumber=" + accountNumber + ", cardNumber=" + cardNumber + ", balance=" + balance + "}";
//    }

}
