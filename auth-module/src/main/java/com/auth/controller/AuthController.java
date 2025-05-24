package com.auth.controller;

import com.auth.repository.ClientRepository;
import com.auth.service.AuthService;
import com.auth.service.ClientService;
import com.auth.util.SessionManager;
import com.common.model.Account;
import com.common.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.auth.repository.ClientRepository.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Сервис аутентификации.", description = "API для аутентификации пользователей и установки времени отклика.")
public class AuthController {

    private final ClientService clientService;
    private final SessionManager sessionManager;
    private final AuthService authService;


    public AuthController(ClientService clientService, SessionManager sessionManager, AuthService authService) {
        this.clientService = clientService;
        this.sessionManager = sessionManager;
        this.authService = authService;
    }


    @Operation(summary = "Установка времени отклика.",
            description = "Устанавливает постоянную минимальную задержку для времени отклика всех запросов сервиса аутентификации.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Минимальное время отклика установлено.")
    }
    )
    @PostMapping("/settimeout")
    public ResponseEntity<String> setTimeout(
            @Parameter(
                    required = true,
                    name = "timeout",
                    description = "время минимального отклика в миллисекундах",
                    example = "10"
            )
            @RequestParam Integer timeout) {
        authService.setGlobalTimeout(timeout);
        return ResponseEntity.ok("");
    }



    @Operation(summary = "Регистрация в системе.",
            description = "Регистрирует учетные данные пользователя в системе.")
    @PostMapping("/register")
    public Client register(
            @Parameter(
                    required = true,
                    name = "fullName",
                    description = "ФИО",
                    example = "newName newName newName"
            )
            @RequestParam String fullName,

            @Parameter(
                    required = true,
                    name = "phone",
                    description = "номер телефона, без маски",
                    example = "+79876543210"
            )
            @RequestParam String phone,

            @Parameter(
                    required = true,
                    name = "username",
                    description = "логин для входа в систему",
                    example = "newUsername"
            )
            @RequestParam String username,

            @Parameter(
                    required = true,
                    name = "password",
                    description = "пароль для входа в систему",
                    example = "newPassword"
            )
            @RequestParam String password) {
        authService.useGlobalTimeout();
        return clientService.register(fullName, phone, username, password);
    }



    @Operation(summary = "Вход пользователя в систему.",
            description = "Аутентифицирует пользователя в системе используя логи и пароль.")
    @PostMapping("/login")
    public String login(
            @Parameter(
                    required = true,
                    name = "username",
                    description = "логин для входа в систему",
                    example = "user1"
            )
            @RequestParam String username,

            @Parameter(
                    required = true,
                    name = "password",
                    description = "пароль для входа в систему",
                    example = "pass1"
            )
            @RequestParam String password) {
        authService.useGlobalTimeout();
        Optional<Client> clientOpt = clientService.login(username, password);
        if (clientOpt.isPresent()) {
            sessionManager.login(clientOpt.get());
            return "✅ Успешный вход: " + username;
        }
        return "❌ Ошибка: Неверный логин или пароль";
    }



    @Operation(summary = "Выход пользователя из системы.",
            description = "Снимает признак аутентификации пользователя в системе.")
    @PostMapping("/logout")
    public String logout() {
        authService.useGlobalTimeout();
        sessionManager.logout();
        return "✅ Успешный выход";
    }



    @Operation(summary = "Получение всех пользователей.",
            description = "Получение списка всех пользователей и их аккаунтов.")
    @GetMapping("/getallclients")
    public Collection<Client> getallclients() {
        return ClientRepository.getAllClients();
    }



    @Operation(summary = "Проверка аутентификации пользователя.",
            description = "Проверяет аутентифицирован ли пользователь.")
    @GetMapping("/isloggedin")
    public boolean isloggedin() {
        //sessionManager.isLoggedIn();
        return sessionManager.isLoggedIn();
    }



    @Operation(summary = "Получение пользователя по логину.",
            description = "Получает пользователя используя логин (username).")
    @PostMapping("/findbyusername")
    public Optional<Client> findbyusername(
            @Parameter(
                    required = true,
                    name = "username",
                    description = "логин для входа в систему",
                    example = "user1"
            )
            @RequestParam String username
    ) {
        return findByUsername(username);
    }




    @Operation(summary = "Получение пользователя по идентификатору.",
            description = "Получает пользователя используя идентификатор (id).")
    @PostMapping("/findclientbyid")
    public Optional<Client> findclientbyid(
            @Parameter(
                    required = true,
                    name = "id",
                    description = "идентификатор пользователя",
                    example = "?"
            )
            @RequestParam String id
    ) {
        return findById(id);
    }




//    @Operation(summary = "Получение пользователя по идентификатору.",
//            description = "Получает пользователя используя идентификатор (id).")
//    @PostMapping("/renewclient")
//    public void renewclient(
//            @Parameter(
//                    required = true,
//                    name = "client",
//                    description = "новый клиент",
//                    common = "?"
//            )
//            @RequestParam Client client
//    ) {
//        System.out.println(client);
//        ClientRepository.replace(client);
//    }

    @Operation(summary = "Получение пользователя по идентификатору.",
            description = "Получает пользователя используя идентификатор (id).")
    @PostMapping("/renewclient")
    public ResponseEntity<String> renewclient(
            @RequestBody Client client) {
        System.out.println(client);
        ClientRepository.replace(client);
        return ResponseEntity.ok("Client renewed successfully");
    }



    @Operation(summary = "Получение текущего пользователя аутентифицированного в системе.",
            description = "Получает текущего пользователя аутентифицированного в системе.")
    @GetMapping("/getloggedinclient")
    public Client getloggedinclient() {
        return sessionManager.getLoggedInClient();
    }



    @Operation(summary = "Установка баланса на аккаунте клиента.",
            description = "Устанавливает баланс на аккаунте (счете) клиента.")
    @PostMapping("/clientaccountsetbalance")
    public void clientaccountsetbalance(
            @Parameter(
                    required = true,
                    name = "id",
                    description = "идентификатор аккаунта клиента, баланс которого будет изменен",
                    example = "968b9b72-0ecf-477f-927d-82306c701aba"
            )
            @RequestParam String id,

            @Parameter(
                    required = true,
                    name = "amount",
                    description = "новая величина баланса аккаунта клиента",
                    example = "20"
            )
            @RequestParam String amount
    ) {
        double doubleamount = Double.parseDouble(amount);

        List<Account> accounts = new ArrayList<Account>();

        getAllClients().forEach(
                (client -> client
                        .getAccounts()
                        .forEach(
                                account ->
                                        accounts.add(account)
                        )
                )
        );

        accounts.stream()
                .filter(obj -> obj.getId().equals(id))
                .findFirst()
                .ifPresent(matchingObj -> {
                    // Do your logic with matchingObj
                    matchingObj.setBalance(doubleamount);
                });

    }

}