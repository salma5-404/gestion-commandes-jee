<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Commandes</title>
  <style>td, th { padding: 5px; }</style>
</head>
<body>
  <p><a href="${pageContext.request.contextPath}/">Accueil</a></p>
  <h2>Liste des commandes</h2>
  <a href="commandes?action=new">+ Nouvelle commande</a>
  <table border="1">
    <tr><th>ID</th><th>Date</th><th>Client</th><th>Total</th><th>Actions</th></tr>
    <c:forEach var="cmd" items="${commandes}">
      <tr>
        <td>${cmd.id}</td>
        <td>${cmd.dateCommande}</td>
        <td><c:out value="${cmd.client.nom} ${cmd.client.prenom}"/></td>
        <td>${cmd.total}</td>
        <td>
          <a href="commandes?action=detail&id=${cmd.id}">Détail / lignes</a> |
          <a href="commandes?action=edit&id=${cmd.id}">Modifier</a> |
          <a href="commandes?action=delete&id=${cmd.id}" onclick="return confirm('Supprimer cette commande ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>