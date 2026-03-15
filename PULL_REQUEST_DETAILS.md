# Détails de la Pull Request - Branche `skjuv-conflits`

Ce document résume le travail effectué sur la branche `skjuv-conflits`. Nathanaël, tu peux l'utiliser comme base pour le merge.

## 📌 Résumé du travail
Mise en place des fondations backend pour la gestion des emplois du temps et la détection des conflits.

## 🛠️ Modifications techniques
1.  **Modèle de données** :
    - [Planning.java](src/main/java/com/g9/model/Planning.java) : Définit les attributs (nom, prof, salle, horaires).
2.  **Logique de conflit** :
    - [ConflictChecker.java](src/main/java/com/g9/service/ConflictChecker.java) : Implémentation de la règle d'intersection temporelle.
3.  **Tests unitaires** :
    - [ConflictCheckerTest.java](src/test/java/com/g9/service/ConflictCheckerTest.java) : 4 tests passant avec succès couvrant les différents cas de chevauchement.
4.  **Dépendances** :
    - Ajout du driver MySQL dans le `pom.xml`.
5.  **Collaboration** :
    - `README.md` : Instructions d'installation.
    - `CONTRIBUTING.md` : Guide pour le binômage avec Freddy.

## ✅ Résultats des Tests
Tous les tests passent via `mvn test`.
```text
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---
*Soumis par SINENG KENGNI Juvenal le 15/03/2026.*
