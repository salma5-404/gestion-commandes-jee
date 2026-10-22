<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Client</title></head>
<body>
  <h2><c:out value="${empty client ? 'Ajouter' : 'Modifier'}"/> un client</h2>
  <form action="clients" method="post">
    <input type="hidden" name="id" value="${client.id}"/>
    Nom : <input name="nom" value="<c:out value='${client.nom}'/>" required/><br/><br/>
    Prénom : <input name="prenom" value="<c:out value='${client.prenom}'/>" required/><br/><br/>
    Email : <input name="email" value="<c:out value='${client.email}'/>"/><br/><br/>
    Téléphone : <input name="telephone" value="<c:out value='${client.telephone}'/>"/><br/><br/>
    Adresse : <input name="adresse" value="<c:out value='${client.adresse}'/>"/><br/><br/>
    <button type="submit">Enregistrer</button>
    <a href="clients">Annuler</a>
  </form>
</body>
</html>