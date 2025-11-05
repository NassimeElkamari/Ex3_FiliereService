package com.example.filiereservice;

import com.example.filiereservice.configuration.RsaKeys;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
        info = @Info(
                title = "Filiere Service API",
                version = "2.0.0",
                description = "CRUD sur les filières (idFiliere, code, libelle)"
        ),
        servers = @Server(url = "http://localhost:8081/")
)

@EnableConfigurationProperties({RsaKeys.class})
@SpringBootApplication
public class FiliereserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FiliereserviceApplication.class, args);
    }
}