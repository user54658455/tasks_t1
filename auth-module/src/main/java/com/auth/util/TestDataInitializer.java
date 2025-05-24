package com.auth.util;

import com.auth.repository.ClientRepository;
import com.common.model.Account;
import com.common.model.Client;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component    //why this is here?
public class TestDataInitializer implements CommandLineRunner {

    private final Faker faker = new Faker();
    private final Random random = new Random();


    @Value("${transactionServiceAddress}")
    private String transactionServiceAddress;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("📌 Генерация тестовых данных...");

        for (int i = 0; i < 10; i++) {
            // Генерируем имя, телефон, логин и пароль
            String fullName = faker.name().fullName();
            String phone = "+79" + (random.nextInt(900000000) + 100000000);
            String username = "user" + (i + 1);
            String password = "pass" + (i + 1);

            // Создаем клиента
            Client client = new Client(fullName, phone, username, password);
            ClientRepository.save(client);
            System.out.println("✅ Создан клиент: " + fullName + " (" + phone + ") | Логин: " + username + ", Пароль: " + password);

            // Создаем случайное количество счетов (от 1 до 3)
            int accountCount = random.nextInt(3) + 1;
            for (int j = 0; j < accountCount; j++) {
                Account account = new Account();
                double initialBalance = random.nextInt(9000) + 1000; // Баланс от 1000 до 10000₽
                account.setBalance(initialBalance);
                client.getAccounts().add(account);
                RestTemplate template = new RestTemplate();
                template.postForLocation(transactionServiceAddress + "/accounts/saveaccount", account);
                System.out.println("  ➕ Счет: " + account.getAccountNumber() + " | Карта: " + account.getCardNumber() + " | Баланс: " + initialBalance + "₽");
            }
        }

        System.out.println("🎉 Генерация тестовых данных завершена!");

    }

}