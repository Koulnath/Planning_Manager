package com.planning;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SYSTÈME DE GESTION DE PLANNING      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        try {
            executerTests();
            
            System.out.println("\n" + "═".repeat(50));
            System.out.println("🔍 TEST PERSONNALISÉ");
            System.out.println("Voulez-vous tester vos propres horaires? (oui/non)");
            String reponse = scanner.nextLine();
            
            if (reponse.equalsIgnoreCase("oui")) {
                testPersonnalise(scanner);
            }
        } catch (Exception e) {
            System.err.println("❌ Erreur inattendue: " + e.getMessage());
        }
        
        System.out.println("\n✨ Fin des tests !");
        scanner.close();
    }
    
    private static void executerTests() {
        System.out.println("Test avec Cours A (8h-10h) et Cours B (9h-11h)\n");
        
        // Création des cours pour les tests
        Planning coursA = new Planning(JourSemaine.LUNDI, "08:00", "10:00", "Cours A - Mathématiques");
        Planning coursB = new Planning(JourSemaine.LUNDI, "09:00", "11:00", "Cours B - Physique");
        Planning coursC = new Planning(JourSemaine.LUNDI, "10:00", "12:00", "Cours C - Chimie");
        Planning coursD = new Planning(JourSemaine.MARDI, "08:00", "10:00", "Cours D - Histoire");
        
        // TEST 1: Cours A vs Cours B (conflit)
        System.out.println("🔴 TEST 1: Cours A vs Cours B (même jour, chevauchement)");
        afficherTest(coursA, coursB);
        
        // TEST 2: Cours A vs Cours C (pas conflit - heures qui se touchent)
        System.out.println("\n🟡 TEST 2: Cours A vs Cours C (même jour, heures qui se touchent)");
        afficherTest(coursA, coursC);
        
        // TEST 3: Cours A vs Cours D (pas conflit - jours différents)
        System.out.println("\n🟢 TEST 3: Cours A vs Cours D (jours différents)");
        afficherTest(coursA, coursD);
        
        // TEST 4: Tous les cours ensemble
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📋 TEST AVEC TOUS LES COURS");
        List<Planning> tousLesCours = Arrays.asList(coursA, coursB, coursC, coursD);
        
        System.out.println("\nListe des cours:");
        for (int i = 0; i < tousLesCours.size(); i++) {
            System.out.println((i+1) + ". " + tousLesCours.get(i));
        }
        
        List<String> conflits = ConflictChecker.findAllConflicts(tousLesCours);
        afficherTousConflits(conflits);
        
        afficherRegles();
    }
    
    private static void afficherTest(Planning p1, Planning p2) {
        boolean conflit = ConflictChecker.checkConflict(p1, p2);
        
        System.out.printf("  %-30s vs %-30s%n", p1.toString(), p2.toString());
        System.out.println("  Résultat: " + (conflit ? "❌ CONFLIT" : "✅ Pas de conflit"));
        
        if (p1.getJour() == p2.getJour()) {
            if (conflit) {
                System.out.println("  Explication: Chevauchement de " + 
                    p1.getHeureDebut() + " à " + p2.getHeureFin());
            } else {
                System.out.println("  Explication: Les horaires ne se chevauchent pas");
            }
        } else {
            System.out.println("  Explication: Jours différents → pas de conflit");
        }
    }
    
    private static void afficherTousConflits(List<String> conflits) {
        System.out.println("\n🔍 Recherche de tous les conflits...");
        
        if (conflits.isEmpty()) {
            System.out.println("  ✅ Aucun conflit trouvé dans la liste !");
        } else {
            System.out.println("  ❌ Conflits trouvés (" + conflits.size() + "):");
            for (String conflit : conflits) {
                System.out.println("    • " + conflit);
            }
        }
    }
    
    private static void afficherRegles() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📝 RÈGLES DE DÉTECTION:");
        System.out.println("• Deux cours sont en conflit s'ils sont le MÊME JOUR");
        System.out.println("• ET que leurs horaires se CHEVAUCHENT (strictement)");
        System.out.println("• Si un cours finit à 10h et l'autre commence à 10h → PAS CONFLIT");
        System.out.println("• Les heures sont validées (fin > début)");
    }
    
    private static void testPersonnalise(Scanner scanner) {
        System.out.println("\n--- Création du premier cours ---");
        Planning p1 = creerCours(scanner, "premier");
        if (p1 == null) return;
        
        System.out.println("\n--- Création du deuxième cours ---");
        Planning p2 = creerCours(scanner, "deuxième");
        if (p2 == null) return;
        
        try {
            boolean conflit = ConflictChecker.checkConflict(p1, p2);
            
            System.out.println("\n" + "📊".repeat(10));
            System.out.println("RÉSULTAT DU TEST:");
            System.out.println("  Cours 1: " + p1);
            System.out.println("  Cours 2: " + p2);
            System.out.println("  " + "─".repeat(30));
            System.out.println("  Conflit? " + (conflit ? "❌ OUI" : "✅ NON"));
            
            if (conflit) {
                System.out.println("  ⚠️  Ces deux cours ne peuvent pas avoir lieu en même temps !");
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    private static Planning creerCours(Scanner scanner, String numero) {
        try {
            System.out.print("Jour (LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE): ");
            String jour = scanner.nextLine().toUpperCase();
            
            System.out.print("Heure de début (format HH:MM, ex: 08:00): ");
            String debut = scanner.nextLine();
            
            System.out.print("Heure de fin (format HH:MM, ex: 10:00): ");
            String fin = scanner.nextLine();
            
            System.out.print("Description: ");
            String description = scanner.nextLine();
            
            return new Planning(JourSemaine.valueOf(jour), debut, fin, description);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur de saisie: " + e.getMessage());
            return null;
        }
    }
}
