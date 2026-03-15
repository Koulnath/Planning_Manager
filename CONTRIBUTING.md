# Guide de Contribution - Binôme Juvenal & Freddy

Ce document définit la collaboration entre **SINENG KENGNI Juvenal** (skjuve) et **CHOUDJA TADZEWA Freddy**.

## 🛠️ Répartition des Rôles
- **Juvenal (skjuve)** : Développement de la logique **CRUD** (Ajouter/Modifier/Supprimer un cours).
- **Freddy** : Implémentation et optimisation de l'algorithme de **détection de conflits** (horaires/salles).

## 🤝 Workflow de Binômage
Pour éviter les conflits de code entre nous deux :

1.  **Communication** : Avant de commencer une modification, valide avec skjuve pour éviter de travailler sur la même méthode.
2.  **Branche Commune** : Nous travaillons sur `skjuv-conflits`.
3.  **Sync** : Fais toujours un `git pull origin skjuv-conflits` avant de coder.

## 📝 Règles de Code
- **Tests d'abord** : Si tu ajoutes une fonctionnalité dans `ConflictChecker`, ajoute le test correspondant dans `ConflictCheckerTest`.
- **Logic de Nathanaël** : Respecte strictement la formule : `(C1.debut < C2.fin) ET (C2.debut < C1.fin) ET (C1.salle == C2.salle)`.
- **Clean Code** : Commente tes méthodes en Javadoc (comme je l'ai fait pour `hasConflict`).

## 🚀 Prochaines étapes détaillées
### 1. Gestion des jours de la semaine
Actuellement, si un cours a lieu le Lundi à 8h et un autre le Mardi à 8h dans la même salle, le système détectera un conflit (car il ne regarde que l'heure).
- **Action** : Ajouter `String jour` dans `Planning.java` et l'inclure dans la comparaison de `ConflictChecker`.

### 2. Validation des données
Empêcher les erreurs de saisie avant même de vérifier les conflits.
- **Action** : Créer une méthode `isValid(Planning p)` qui vérifie que `heureFin >= heureDebut + 100` (minimum 1h de cours) et que les champs ne sont pas vides.

### 3. Gestion des Enseignants
Un enseignant ne peut pas être à deux endroits en même temps !
- **Action** : Ajouter une règle de conflit : il y a conflit si `(prof1 == prof2)` même si les salles sont différentes.

---
*Travaillons proprement pour que Nathanaël n'ait aucun mal à merger notre code ce soir !*
