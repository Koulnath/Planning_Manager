-- Création de la base de données
CREATE DATABASE IF NOT EXISTS planning_g9;
USE planning_g9;

-- Table des Salles
CREATE TABLE IF NOT EXISTS salles (
    id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom_salle VARCHAR(50) NOT NULL,
    capacite INT NOT NULL
);

-- Table des Enseignants
CREATE TABLE IF NOT EXISTS enseignants (
    id_enseignant INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    specialite VARCHAR(100)
);

-- Table des Cours
CREATE TABLE IF NOT EXISTS cours (
    id_cours INT AUTO_INCREMENT PRIMARY KEY,
    intitule VARCHAR(100) NOT NULL,
    niveau VARCHAR(50) -- Ex: L1, L2, Master
);

-- Table Principale : Le Planning (Celle qui relie tout)
CREATE TABLE IF NOT EXISTS emplois_du_temps (
    id_planning INT AUTO_INCREMENT PRIMARY KEY,
    id_cours INT NOT NULL,
    id_salle INT NOT NULL,
    id_enseignant INT NOT NULL,
    jour_semaine VARCHAR(15) NOT NULL, -- Ex: 'Lundi'
    heure_debut TIME NOT NULL,         -- Ex: '08:00:00'
    heure_fin TIME NOT NULL,           -- Ex: '10:00:00'
    FOREIGN KEY (id_cours) REFERENCES cours(id_cours) ON DELETE CASCADE,
    FOREIGN KEY (id_salle) REFERENCES salles(id_salle) ON DELETE CASCADE,
    FOREIGN KEY (id_enseignant) REFERENCES enseignants(id_enseignant) ON DELETE CASCADE
);

-- Quelques données de test pour vérifier que ça marche
INSERT INTO salles (nom_salle, capacite) VALUES ('Amphi 1', 500), ('Salle 4', 50);
INSERT INTO enseignants (nom, specialite) VALUES ('Dr. KENFACK', 'Génie Logiciel'), ('Pr. TCHOUA', 'Mathématiques');
INSERT INTO cours (intitule, niveau) VALUES ('Génie Logiciel Appliqué', 'L3'), ('Algèbre Linéaire', 'L1');