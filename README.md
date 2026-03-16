

```markdown
# Planning Manager - Groupe G9

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Swing](https://img.shields.io/badge/UI-Swing/FlatLaf-blue?style=for-the-badge)

> **Planning Manager** est une solution robuste de gestion d'emplois du temps universitaire. Elle permet de planifier des cours en temps réel tout en garantissant l'absence de conflits logistiques grâce à un algorithme de détection intelligent.

---

## Fonctionnalités Clés
- **Interface Moderne** : UI épurée avec support du **Dark Mode** et **Light Mode** (FlatLaf).
- **Gestion des Conflits** : Vérification automatique de la disponibilité des salles et des professeurs.
- **Dashboard Intuitif** : Visualisation claire des cours avec un tableau dynamique.
- **Persistance des Données** : Intégration complète avec MySQL via JDBC.

---

## Architecture du Projet (MVC)
Le projet respecte le pattern **Modèle-Vue-Contrôleur** pour une séparation claire des responsabilités :

* **`com.g9.model`** : Représentation des données (Planning, JourSemaine).
* **`com.g9.view`** : Interface utilisateur (MainGUI) avec composants personnalisés.
* **`com.g9.service`** : Logique métier et algorithme de détection de conflits.
* **`com.g9.dao`** : Couche d'accès à la base de données (Data Access Object).

---

## Installation & Lancement

### 1. Prérequis
- **JDK 17** ou supérieur.
- **MySQL / XAMPP**.
- **Maven 3.6+**.

### 2. Configuration de la Base de Données
1. Démarrez votre serveur MySQL (via XAMPP par exemple).
2. Créez une base de données nommée `tp207`.
3. Importez le script SQL de structure :
   ```sql
   -- Localisation : src/main/resources/schema.sql
   CREATE DATABASE tp207;
   USE tp207;

```

### 3. Compilation et Exécution

Ouvrez votre terminal à la racine du projet :

```bash
# Télécharger les dépendances et compiler
mvn clean install

# Lancer l'application
mvn exec:java -Dexec.mainClass="com.g9.MainGUI"

```

---

## Travail en Équipe (Workflow Git)

Pour maintenir la stabilité du code, l'équipe G9 suit ces règles :

* **Branches** : `main` est réservée aux versions stables. Travail sur branches `feature/nom`.
* **Commits** : Messages explicites (ex: `feat: integration flatlaf`).
* **Review** : Validation avant fusion sur la branche principale.

---

## L'Équipe G9

* **Design UI/UX** : Prisca
* **Logique & Algorithme** : skjuve & Freddy
* **Database & DAO** : Sokeng
* **Qualité & Tests** : Ryan

---

## Tests

Exécutez les tests unitaires pour valider la logique de détection de conflits :

```bash
mvn test

```

---

**Projet réalisé dans le cadre du TP207 par le Groupe G9.**

```


```
