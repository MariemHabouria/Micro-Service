# Projet Microservices Salle de Sport



## 📋 Description
Ce projet implémente une architecture microservices pour une application de gestion de salle de sport, comprenant :
- **Microservices** : Produits, Cours, Événements, Abonnements
- **Technologies** : Spring Boot, Spring Cloud, Docker, Keycloak
- **Frontend** : Angular

## 🛠 Technologies Utilisées

### Backend
- **Java 17** avec Spring Boot 3.x
- **Spring Cloud** (Gateway, Eureka, Config)
- **Base de données** : H2 (développement) / MySQL (production)
- **Authentification** : Keycloak
- **Communication** : Feign Client, REST APIs
- **Docker** avec Docker Hub pour le déploiement
- **Docker Compose** pour l'orchestration

### Frontend
- **Angular 15+**
- **Keycloak JS** pour l'authentification
- **Bootstrap** pour le styling

## 🚀 Installation et Démarrage

### Prérequis
- JDK 17
- Docker et Docker Compose
- Node.js 16+ et npm
- Keycloak (configuré avec les realms/clients nécessaires)

### 1. Lancer l'infrastructure
```bash
docker-compose -f docker-compose-infra.yml up -d
