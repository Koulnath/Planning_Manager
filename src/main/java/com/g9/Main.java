package com.g9;

import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import com.g9.service.CourseService;
import java.util.Scanner;

public class Main {
    private static final CourseService service = new CourseService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   PLANNING MANAGER G9 - DÉMO UNIFIÉE   ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        initialiserDonnees();

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    voirListe();
                    break;
                case "2":
                    ajouterCours();
                    break;
                case "3":
                    supprimerCours();
                    break;
                case "4":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        System.out.println("Au revoir et bonne présentation !");
    }

    private static void initialiserDonnees() {
        try {
            service.addCourse(new Planning("Java Core", "Favor", "Amphi A", JourSemaine.LUNDI, "08:00", "10:00"));
            service.addCourse(new Planning("SQL DB", "Sokeng", "Salle 102", JourSemaine.LUNDI, "10:00", "12:00"));
            service.addCourse(new Planning("Maths", "Nathanael", "Amphi B", JourSemaine.MARDI, "08:00", "10:30"));
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation des données de test : " + e.getMessage());
        }
    }

    private static void afficherMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Voir la liste des cours");
        System.out.println("2. Ajouter un cours (Vérifie les conflits)");
        System.out.println("3. Supprimer un cours par ID");
        System.out.println("4. Quitter");
        System.out.print("Choix : ");
    }

    private static void voirListe() {
        System.out.println("\n--- LISTE DES COURS ---");
        if (service.getAllCourses().isEmpty()) {
            System.out.println("Aucun cours n'est actuellement programmé.");
        } else {
            for (Planning p : service.getAllCourses()) {
                System.out.println(p.toString());
            }
        }
    }

    private static void ajouterCours() {
        System.out.println("\n--- AJOUT D'UN COURS ---");
        try {
            System.out.print("Nom du cours : ");
            String nom = scanner.nextLine().trim();
            
            System.out.print("Enseignant : ");
            String prof = scanner.nextLine().trim();
            
            System.out.print("Salle : ");
            String salle = scanner.nextLine().trim();
            
            System.out.print("Jour (ex: LUNDI) : ");
            String jourStr = scanner.nextLine().trim().toUpperCase();
            
            JourSemaine jour;
            try {
                jour = JourSemaine.valueOf(jourStr);
            } catch (IllegalArgumentException e) {
                System.err.println("Jour invalide. Veuillez utiliser Lundi, Mardi, etc.");
                return; 
            }
            
            System.out.print("Début (HH:mm) : ");
            String debut = scanner.nextLine().trim();
            
            System.out.print("Fin (HH:mm) : ");
            String fin = scanner.nextLine().trim();

            Planning p = new Planning(nom, prof, salle, jour, debut, fin);
            service.addCourse(p);
            System.out.println("Cours ajouté avec succès !");
            
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }

    private static void supprimerCours() {
        System.out.print("\nID du cours à supprimer : ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            if (service.deleteCourse(id)) {
                System.out.println("Cours supprimé.");
            } else {
                System.out.println("ID non trouvé. Aucun cours n'a été supprimé.");
            }
        } catch (NumberFormatException e) {
            System.err.println("ID invalide. Veuillez entrer un nombre entier.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression.");
        }
    }
}