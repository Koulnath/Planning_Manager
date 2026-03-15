# Guide Technique du Développeur - Planning_Manager

Ce document détaille l'architecture technique, les normes de codage et la structure du projet pour assurer une collaboration efficace entre **Juvenal**, **Sandy** et le reste du groupe G9.

## 🏗️ Architecture Globale (MVC)

Le projet suit une architecture **Modèle-Vue-Contrôleur (MVC)** simplifiée pour séparer les responsabilités :

### 1. Le Modèle (`com.g9.model`)
Contient les objets de données simples (POJO).
- **`Planning.java`** : C'est l'entité centrale. Elle représente un cours avec :
    - `id` : Identifiant unique.
    - `nomCours` : Nom de la matière.
    - `enseignant` : Nom du professeur.
    - `salle` : Nom de la salle.
    - `heureDebut` / `heureFin` : Entiers au format `HHMM` (ex: 830 pour 8h30).

### 2. Le Service / Logique métier (`com.g9.service`)
Contient les algorithmes et la validation.
- **`ConflictChecker.java`** : (Rôle de **Sandy**) Contient la logique pour détecter les chevauchements.
- **`CourseService.java`** : (Rôle de **Juvenal**) Gère la liste des cours et appelle le `ConflictChecker` avant toute opération CRUD.

### 3. La Vue (`com.g9.view` / `com.g9.Main`)
Interfaces graphiques Swing.
- **`Main.java`** : Point d'entrée lançant la fenêtre principale.
- Les futurs formulaires de Prisca seront ici.

### 4. Le DAO (`com.g9.dao`)
Accès à la base de données MySQL.
- **`DatabaseConnection.java`** : Gère la connexion JDBC (Rôle de Sokeng).

---

## 🛠️ Normes de Développement

### 1. Codage en Java
- **Version** : Java 17.
- **Nomenclature** : `CamelCase` pour les classes, `lowerCamelCase` pour les variables et méthodes.
- **Javadoc** : Documenter chaque méthode publique (paramètres, retour, exceptions).

### 2. Gestion des Erreurs
- Ne jamais laisser de bloc `catch` vide.
- Utiliser des exceptions explicites (ex: `ConflictException` si un cours chevauche un autre).

### 3. Tests Unitaires (Critique)
Tout code métier (surtout l'algorithme de Sandy) doit être testé.
- Emplacement : `src/test/java/com/g9/service/`
- Outil : **JUnit 4**.
- Lancer les tests : `mvn test`.

---

## 🌿 Workflow Git & Branches

### Règle d'or : Jamais de push direct sur `main`.

1.  **Branche Commune** : `skjuv-conflits`.
2.  **Synchronisation** : Avant de coder, toujours faire :
    ```bash
    git checkout skjuv-conflits
    git pull origin skjuv-conflits
    ```
3.  **Enregistrement** :
    ```bash
    git add .
    git commit -m "Description claire de ton changement"
    git push origin skjuv-conflits
    ```

---

## 🔍 Focus pour Sandy (Algorithme de Conflit)

Ton rôle est de rendre l'application "intelligente". Actuellement, `ConflictChecker` ne gère que les heures. 
**Ton prochain défi** : Ajouter la gestion des jours de la semaine (`Lundi`, `Mardi`, etc.).

**Logique de collision temporelle actuelle :**
```java
return (c1.getHeureDebut() < c2.getHeureFin()) && (c2.getHeureDebut() < c1.getHeureFin());
```
*Note : Si les heures se touchent exactement (ex: 10h00 fin et 10h00 début), il n'y a PAS de conflit.*

---

## 🚀 Comment lancer le projet ?
1.  **Build** : `mvn clean install`
2.  **Exécuter** : `mvn exec:java -Dexec.mainClass="com.g9.Main"`
