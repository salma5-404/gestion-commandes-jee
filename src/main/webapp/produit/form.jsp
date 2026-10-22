<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Produit</title></head>
<body>
  <h2><c:out value="${empty produit ? 'Ajouter' : 'Modifier'}"/> un produit</h2>
  <form action="produits" method="post">
    <input type="hidden" name="id" value="${produit.id}"/>
    Désignation : <input name="designation" value="<c:out value='${produit.designation}'/>" required/><br/><br/>
    Prix : <input name="prix" type="number" step="0.01" value="${produit.prix}" required/><br/><br/>
    Stock : <input name="stock" type="number" value="${produit.stock}" required/><br/><br/>
    <button type="submit">Enregistrer</button>
    <a href="produits">Annuler</a>
  </form>
</body>
</html>