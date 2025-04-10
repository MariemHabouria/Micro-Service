# Projet de Gestion de Salle de Sport

## Description
Ce projet consiste en le développement d'une application web distribuée pour la gestion d'une salle de sport. L'application comprend deux parties principales :
- **Backend** : Basé sur une architecture microservices, il gère les fonctionnalités essentielles telles que les abonnements, les réservations, les paiements, et la gestion des produits en ligne.
- **Frontend** : Une interface utilisateur qui consomme les APIs exposées par le backend pour offrir une expérience interactive aux utilisateurs.

## Fonctionnalités Principales
1. **Gestion des Abonnements** :
   - Souscription, renouvellement et annulation d'abonnements.
   - Suivi des statuts et des historiques des abonnés.

2. **Réservation des Cours et Équipements** :
   - Réservation en ligne des cours collectifs et des équipements.
   - Gestion des créneaux horaires et des disponibilités.

3. **Paiement** :
   - Intégration d'un système de paiement sécurisé pour les abonnements et les services.
   - Suivi des transactions et génération de factures.

4. **Gestion des Produits en Ligne** :
   - Catalogue des produits disponibles à la vente (équipements, compléments alimentaires, etc.).
   - Panier d'achat et commandes en ligne.

5. **Cours et Événements** :
   - Publication des cours et événements sportifs.
   - Inscription et gestion des participants.

## Architecture Technique
- **Backend** :
  - **Microservices** : Développés avec Spring Boot, chaque microservice est responsable d'une fonctionnalité spécifique.
  - **Bases de Données** : Utilisation de H2, MySQL, MongoDB/PostgreSQL selon les besoins des microservices.
  - **API Gateway** : Centralise les requêtes et les redirige vers les microservices appropriés.
  - **Server Config** : Configuration centralisée pour les microservices.

- **Frontend** :
  - Framework moderne (React, Angular, ou Vue.js) pour une interface réactive et intuitive.

## Technologies Utilisées
- **Backend** : Spring Boot, API Gateway, H2, MySQL, MongoDB/PostgreSQL.
- **Frontend** : React/Angular/Vue.js.
- **Outils** : Git, Docker, Swagger/OpenAPI, outils de gestion de projet.

