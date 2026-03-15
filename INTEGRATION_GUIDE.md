# Guide d'Intégration du Backend Unifié

Ce guide explique comment les autres membres de l'équipe G9 peuvent exploiter le backend robuste développé par **Juvenal** et **Sandy**.

---

## 🎨 Partie 1 : Pour la Designer UI/UX (Prisca)
L'interface doit communiquer avec la logique métier via le `CourseService`. 

### Comment ajouter un cours depuis un bouton ?
```java
// Dans votre ActionListener :
CourseService service = new CourseService(); // Idéalement partagé

try {
    Planning nouveauCours = new Planning(
        "Introduction Java", 
        "Juvenal", 
        "Amphi A", 
        JourSemaine.LUNDI, 
        "08:00", 
        "10:00"
    );
    service.addCourse(nouveauCours);
    JOptionPane.showMessageDialog(null, "Cours ajouté !");
} catch (IllegalArgumentException e) {
    // Si l'algo de Sandy détecte un conflit
    JOptionPane.showMessageDialog(null, "Attention : " + e.getMessage(), "Conflit", JOptionPane.WARNING_MESSAGE);
}
```

---

## 🗄️ Partie 2 : Pour l'Administrateur BD (Sokeng)
Le `CourseService` gère actuellement les données en mémoire. Vous devez implémenter le DAO pour persister ces données.

### Structure de la table SQL suggérée :
```sql
CREATE TABLE planning (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom_cours VARCHAR(100),
    enseignant VARCHAR(100),
    salle VARCHAR(50),
    jour VARCHAR(20),       -- Sauvegarder JourSemaine.name()
    heure_debut TIME,       -- Format LocalTime compatible
    heure_fin TIME
);
```

---

## 🧪 Partie 3 : Pour le Testeur / QA (Ryan)
Toute la logique de conflit et de CRUD est couverte par des tests unitaires.

### Comment vérifier le backend ?
Exécutez simplement la commande Maven :
```text
mvn test
```
Les fichiers importants sont dans `src/test/java/com/g9/service/`. Si vous trouvez un bug, créez un nouveau test dans `ConflictCheckerTest.java` qui reproduit l'erreur.

---

## 📊 Partie 4 : Pour l'Analyste / Data (Esther)
Le système supporte désormais :
- Les **conflits de salles** (même salle, même heure).
- Les **conflits d'enseignants** (un prof ne peut pas être dans deux salles en même temps).
- La **validation temporelle** (un cours ne peut pas finir avant de commencer).

Veuillez nous fournir des jeux de données incluant des cours le Samedi et le Dimanche pour tester la robustesse de l'algorithme de Sandy.

---

## 📦 Importants pour tous
- **Package Modèle** : `com.g9.model` (Planning, JourSemaine)
- **Package Service** : `com.g9.service` (ConflictChecker, CourseService)
