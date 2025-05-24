package com.auth.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.local-url}")
    private String localUrl;

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Server URL in local environment for debug");

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("my@ya.ru");
        contact.setName("Alex");

        License licens = new License().name("GPL License").url("https://choosealicense.com/licenses/gpl/");

        Info info = new Info()
                .title("Bank authentication API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage authentication service.")
                .license(licens);

        return new OpenAPI().info(info).servers(List.of(localServer, devServer));
    }

}