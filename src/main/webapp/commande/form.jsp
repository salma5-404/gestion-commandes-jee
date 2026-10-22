<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Commande</title></head>
<body>
  <h2><c:out value="${empty commande ? 'Nouvelle' : 'Modifier la'}"/> commande</h2>
  <form action="commandes" method="post">
    <input type="hidden" name="id" value="${commande.id}"/>
    Date : <input type="date" name="dateCommande" value="${commande.dateCommande}" required/><br/><br/>
    Client :
    <select name="clientId" required>
      <c:forEach var="cl" items="${clients}">
        <option value="${cl.id}" ${commande.client.id == cl.id ? 'selected' : ''}>
          <c:out value="${cl.nom} ${cl.prenom}"/>
        </option>
      </c:forEach>
    </select><br/><br/>
    <button type="submit">Enregistrer</button>
    <a href="commandes">Annuler</a>
  </form>
</body>
</html>