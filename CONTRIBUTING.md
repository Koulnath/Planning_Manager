# Guide de Contribution - Binôme skjuve & Sandy

Bienvenue Sandy ! Ce document définit comment nous allons travailler ensemble sur la partie **Backend & Logique de Conflit**.

## 🛠️ Notre Stack
- **Modèle** : `com.g9.model.Planning`
- **Logic** : `com.g9.service.ConflictChecker`
- **Tests** : `src/test/java/com/g9/service/ConflictCheckerTest.java`

## 🤝 Workflow de Binômage
Pour éviter les conflits de code entre nous deux :

1.  **Communication** : Avant de commencer une modification, valide avec skjuve pour éviter de travailler sur la même méthode.
2.  **Branche Commune** : Nous travaillons sur `skjuv-conflits`.
3.  **Sync** : Fais toujours un `git pull origin skjuv-conflits` avant de coder.

## 📝 Règles de Code
- **Tests d'abord** : Si tu ajoutes une fonctionnalité dans `ConflictChecker`, ajoute le test correspondant dans `ConflictCheckerTest`.
- **Logic de Nathanaël** : Respecte strictement la formule : `(C1.debut < C2.fin) ET (C2.debut < C1.fin) ET (C1.salle == C2.salle)`.
- **Clean Code** : Commente tes méthodes en Javadoc (comme je l'ai fait pour `hasConflict`).

## 🚀 Prochaines étapes pour nous
- [ ] Intégrer la gestion des jours de la semaine (pour l'instant on ne gère que les heures).
- [ ] Ajouter une validation pour que `heureFin > heureDebut`.
- [ ] Préparer l'intégration avec le DAO de Sokeng.

---
*Travaillons proprement pour que Nathanaël n'ait aucun mal à merger notre code ce soir !*
