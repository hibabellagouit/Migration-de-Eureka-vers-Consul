# Migration de Eureka vers Consul

Ce projet démontre la migration d'une architecture microservices utilisant Eureka comme serveur de découverte vers Consul comme alternative.

## Architecture du Projet

Le projet est composé de plusieurs modules :

- **car-service** : Service gérant les voitures
- **client-service** : Service client pour démontrer la communication entre services
- **gateway** : API Gateway pour le routage des requêtes
- **server-eureka** : Ancien serveur de découverte (à remplacer par Consul)

## Prérequis

- Java 17
- Maven 3.6+
- MySQL 8.0+
- Consul (pour la nouvelle version)

## Configuration

### Configuration de la base de données

1. Créer une base de données MySQL nommée `car_db`
2. Configurer les identifiants dans `application.yml`

### Configuration de Consul

1. Télécharger et installer Consul depuis [consul.io](https://www.consul.io/)
2. Démarrer le serveur Consul en mode développement :
   ```
   consul agent -dev
   ```
3. L'interface web de Consul sera disponible à l'adresse : http://localhost:8500

## Démarrer les services

1. Démarrer le serveur Consul
2. Démarrer chaque service dans l'ordre :
   ```
   cd car
   mvn spring-boot:run
   
   cd ../client
   mvn spring-boot:run
   
   cd ../gateway
   mvn spring-boot:run
   ```

## Points d'API

### Car Service
- `GET /api/cars` - Récupérer toutes les voitures
- `GET /api/cars/{id}` - Récupérer une voiture par ID
- `POST /api/cars` - Ajouter une nouvelle voiture

### Client Service
- `GET /api/clients` - Récupérer tous les clients avec leurs voitures

## Migration de Eureka vers Consul

1. Supprimer la dépendance `spring-cloud-starter-netflix-eureka-client`
2. Ajouter la dépendance `spring-cloud-starter-consul-discovery`
3. Mettre à jour le fichier `application.yml` pour utiliser la configuration Consul
4. Ajouter l'annotation `@EnableDiscoveryClient` à la classe principale

## Licence

Ce projet est sous licence MIT.
