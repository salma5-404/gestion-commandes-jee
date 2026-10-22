CREATE DATABASE IF NOT EXISTS gestion_commandes CHARACTER SET utf8mb4;
USE gestion_commandes;

CREATE TABLE client (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  email VARCHAR(100),
  telephone VARCHAR(20),
  adresse VARCHAR(200)
);

CREATE TABLE produit (
  id INT AUTO_INCREMENT PRIMARY KEY,
  designation VARCHAR(100) NOT NULL,
  prix DECIMAL(10,2) NOT NULL,
  stock INT DEFAULT 0
);

CREATE TABLE commande (
  id INT AUTO_INCREMENT PRIMARY KEY,
  date_commande DATE NOT NULL,
  client_id INT NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

CREATE TABLE ligne_commande (
  id INT AUTO_INCREMENT PRIMARY KEY,
  commande_id INT NOT NULL,
  produit_id INT NOT NULL,
  quantite INT NOT NULL,
  FOREIGN KEY (commande_id) REFERENCES commande(id) ON DELETE CASCADE,
  FOREIGN KEY (produit_id) REFERENCES produit(id)
);