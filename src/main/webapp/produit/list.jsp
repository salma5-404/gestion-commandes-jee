<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Produits</title>
  <style>td, th { padding: 5px; }</style>
</head>
<body>
  <p><a href="${pageContext.request.contextPath}/">Accueil</a></p>
  <h2>Liste des produits</h2>
  <a href="produits?action=new">+ Nouveau produit</a>
  <table border="1">
    <tr><th>ID</th><th>Désignation</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
    <c:forEach var="p" items="${produits}">
      <tr>
        <td>${p.id}</td>
        <td><c:out value="${p.designation}"/></td>
        <td>${p.prix}</td>
        <td>${p.stock}</td>
        <td>
          <a href="produits?action=edit&id=${p.id}">Modifier</a> |
          <a href="produits?action=delete&id=${p.id}" onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>