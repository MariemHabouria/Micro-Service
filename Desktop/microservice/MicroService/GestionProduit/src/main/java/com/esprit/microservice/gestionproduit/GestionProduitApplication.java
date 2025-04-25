package com.esprit.microservice.gestionproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GestionProduitApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionProduitApplication.class, args);
    }
}

