<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Détail commande</title>
</head>
<body>
  <p><a href="commandes">← Retour aux commandes</a></p>

  <h2>Commande n° ${commande.id}</h2>
  <p>
    Date : ${commande.dateCommande}<br/>
    Client : <c:out value="${commande.client.nom} ${commande.client.prenom}"/>
  </p>

  <h3>Lignes de commande</h3>
  <table border="1">
    <tr>
      <th>Produit</th>
      <th>Prix unitaire</th>
      <th>Quantité</th>
      <th>Sous-total</th>
      <th>Actions</th>
    </tr>
    <c:forEach var="l" items="${commande.lignes}">
      <tr>
        <td><c:out value="${l.produit.designation}"/></td>
        <td>${l.produit.prix}</td>
        <td>
          <input type="number" name="quantite" value="${l.quantite}" min="1"
                 style="width:60px" form="f${l.id}"/>
        </td>
        <td>${l.sousTotal}</td>
        <td>
          <form id="f${l.id}" action="commandes" method="post" style="display:inline">
            <input type="hidden" name="action" value="updateLigne"/>
            <input type="hidden" name="commandeId" value="${commande.id}"/>
            <input type="hidden" name="ligneId" value="${l.id}"/>
            <input type="hidden" name="produitId" value="${l.produit.id}"/>
            <button type="submit">Modifier</button>
          </form>
          <a href="commandes?action=deleteLigne&commandeId=${commande.id}&ligneId=${l.id}"
             onclick="return confirm('Supprimer cette ligne ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <p><strong>Total : ${commande.total}</strong></p>

  <h3>Ajouter une ligne</h3>
  <form action="commandes" method="post">
    <input type="hidden" name="action" value="addLigne"/>
    <input type="hidden" name="commandeId" value="${commande.id}"/>
    Produit :
    <select name="produitId">
      <c:forEach var="p" items="${produits}">
        <option value="${p.id}"><c:out value="${p.designation}"/> (${p.prix})</option>
      </c:forEach>
    </select>
    Quantité :
    <input type="number" name="quantite" value="1" min="1" style="width:60px"/>
    <button type="submit">Ajouter</button>
  </form>
</body>
</html>