package com.g9;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Lancement de l'interface de manière sécurisée (bonnes pratiques Java Swing)
        SwingUtilities.invokeLater(() -> {
            // Création de la fenêtre principale
            JFrame frame = new JFrame("G9 - Gestion des Emplois de Temps");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null); // Pour centrer la fenêtre à l'écran

            // Ajout d'un titre
            JLabel titre = new JLabel("Module de Planning - Groupe G9", SwingConstants.CENTER);
            titre.setFont(new Font("Arial", Font.BOLD, 24));
            titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            frame.add(titre, BorderLayout.NORTH);

            // Tableau factice pour montrer à Eyebe l'objectif
            String[] colonnes = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
            String[][] donnees = {
                {"08h - 10h", "Génie Logiciel (Amphi 1)", "", "Base de données (Salle 4)", "", "Java (Salle 2)"},
                {"10h - 12h", "Maths (Salle 2)", "Réseaux (Amphi 2)", "", "Anglais (Salle 1)", "Projet TP"}
            };
            
            JTable tablePlanning = new JTable(donnees, colonnes);
            tablePlanning.setRowHeight(30);
            
            // On ajoute le tableau dans un ScrollPane pour avoir les entêtes
            JScrollPane scrollPane = new JScrollPane(tablePlanning);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Bouton de test (qui sera géré plus tard par la logique de Sineng/Choudja)
            JButton btnAjouter = new JButton("Ajouter un cours");
            frame.add(btnAjouter, BorderLayout.SOUTH);

            // Afficher la fenêtre
            frame.setVisible(true);
        });
    }
}