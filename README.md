# Planning_Manager - Système de Gestion d'Emplois du Temps

Bienvenue sur le projet du Groupe G9 ! Ce logiciel permet de gérer les emplois du temps, d'ajouter des cours et de vérifier les conflits de salles et d'horaires.

## 📋 Prérequis
Avant de commencer, assurez-vous d'avoir installé :
- **Java JDK 17** ou supérieur.
- **Maven 3.6+**.
- **MySQL / XAMPP**.
- Un IDE comme **VS Code** ou **IntelliJ**.

## 🚀 Installation & Lancement
1.  **Cloner le projet** :
    ```bash
    git clone https://github.com/koulnath/Planning_Manager.git
    cd Planning_Manager
    ```
2.  **Configurer la Base de Données** :
    - Lancez XAMPP (Apache & MySQL).
    - Créez une base de données nommée `tp207`.
    - Importez le fichier `src/main/resources/schema.sql` (ou utilisez le script fourni dans le `guide_equipe.md`).
3.  **Compiler et Lancer** :
    ```bash
    mvn clean compile
    mvn exec:java -Dexec.mainClass="com.g9.Main"
    ```

## 🛠️ Structure du Projet (MVC)
- `com.g9.model` : Contient les objets de données (`Planning.java`).
- `com.g9.view` : Interfaces graphiques Swing (Prisca).
- `com.g9.service` : Logique métier comme la détection de conflits (skjuve & Freddy).
- `com.g9.dao` : Accès à la base de données (Sokeng).

## 🌿 Travail en Équipe (Règles Git)
- **Branche** : Ne travaillez jamais sur `main`. Créez votre branche : `git checkout -b votre-nom-tache`.
- **Commit** : Faites des messages clairs.
- **Push** : Envoyez votre travail avec `git push origin votre-nom-tache`.

## 📖 Documentation Détaillée
- [✨ Guide Fondamental du Groupe G9 (HTML)](documentation/guide.html) : **À lire absolument !** (Architecture, Rôles, Git).
- [🛠️ Guide d'Intégration Backend](INTEGRATION_GUIDE.md) : Pour Prisca (UI), Sokeng (DB) et Ryan (QA).
- [Guide Technique du Développeur](DEVELOPER_GUIDE.md) : Détails techniques profonds.
- [Guide de Contribution](CONTRIBUTING.md) : Pour le travail en binôme.

## ✅ Tests
Pour vérifier que la logique de conflit fonctionne :
```bash
mvn test
```

---
*Projet réalisé dans le cadre du TP207 par le Groupe G9.*
