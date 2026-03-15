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
            String choix = scanner.nextLine();

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
                    System.out.println("Choix invalide.");
            }
        }
        System.out.println("Au revoir !");
    }

    private static void initialiserDonnees() {
        try {
            service.addCourse(new Planning("Java Core", "Favor", "Amphi A", JourSemaine.LUNDI, "08:00", "10:00"));
            service.addCourse(new Planning("SQL DB", "Sokeng", "Salle 102", JourSemaine.LUNDI, "10:00", "12:00"));
            service.addCourse(new Planning("Maths", "Nathanael", "Amphi B", JourSemaine.MARDI, "08:00", "10:30"));
        } catch (Exception e) {
            System.err.println("Erreur initialisation : " + e.getMessage());
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
        System.out.println("\n📋 LISTE DES COURS :");
        for (Planning p : service.getAllCourses()) {
            System.out.println(p);
        }
    }

    private static void ajouterCours() {
        System.out.println("\n--- AJOUT D'UN COURS ---");
        try {
            System.out.print("Nom du cours : ");
            String nom = scanner.nextLine();
            System.out.print("Enseignant : ");
            String prof = scanner.nextLine();
            System.out.print("Salle : ");
            String salle = scanner.nextLine();
            System.out.print("Jour (ex: LUNDI) : ");
            String jourStr = scanner.nextLine().toUpperCase();
            System.out.print("Début (HH:mm) : ");
            String debut = scanner.nextLine();
            System.out.print("Fin (HH:mm) : ");
            String fin = scanner.nextLine();

            Planning p = new Planning(nom, prof, salle, JourSemaine.valueOf(jourStr), debut, fin);
            service.addCourse(p);
            System.out.println("✅ Cours ajouté avec succès !");
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    private static void supprimerCours() {
        System.out.print("\nID du cours à supprimer : ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (service.deleteCourse(id)) {
                System.out.println("✅ Cours supprimé.");
            } else {
                System.out.println("⚠️ ID non trouvé.");
            }
        } catch (Exception e) {
            System.err.println("❌ ID invalide.");
        }
    }
}