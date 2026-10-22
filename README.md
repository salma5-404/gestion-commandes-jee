# Gestion des commandes (MVC1 / JEE)

Application web : Servlet, JSP/JSTL, JavaBean, DAO, MySQL.
Université Abdelmalek Essaadi, FST Tanger, LSI, module JEE.

## Outils
Eclipse, Maven, Tomcat 9, MySQL (XAMPP), JDK 11

## Lancer le projet
1. Démarrer MySQL (XAMPP) et exécuter `database.sql`
2. Importer le projet dans Eclipse (Existing Maven Project)
3. Vérifier l'URL/identifiants dans `ma.fstt.dao.DBConnection`
4. Run As → Run on Server (Tomcat 9)
5. Ouvrir http://localhost:8080/gestion-commandes/

## Packages
- `ma.fstt.entities` : Client, Produit, Commande, LigneCommande
- `ma.fstt.service` : IDao
- `ma.fstt.dao` : DBConnection, ClientDao, ProduitDao, CommandeDao
- `ma.fstt.servlets` : contrôleurs