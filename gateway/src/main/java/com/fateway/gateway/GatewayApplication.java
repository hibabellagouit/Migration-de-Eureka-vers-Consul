package com.fateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

/**
 * Classe principale de l'API Gateway.
 * Cette application sert de point d'entrée unique pour toutes les requêtes des clients
 * et les route vers les microservices appropriés en utilisant la découverte de service.
 */
@SpringBootApplication
@EnableDiscoveryClient // Active la découverte de service (Consul dans ce cas)
public class GatewayApplication {

    /**
     * Point d'entrée de l'application.
     * 
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * Configuration du routeur dynamique pour la découverte de services.
     * Ce bean permet de créer des routes dynamiques basées sur les services enregistrés
     * dans le service de découverte (Consul).
     *
     * @param reactiveDiscoveryClient Le client de découverte réactif
     * @param discoveryLocatorProperties Les propriétés de configuration de la découverte
     * @return Un routeur dynamique basé sur la découverte de services
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator routesDynamic(
            ReactiveDiscoveryClient reactiveDiscoveryClient,
            DiscoveryLocatorProperties discoveryLocatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}