<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Clients</title></head>
<body>
  <p><a href="${pageContext.request.contextPath}/">Accueil</a></p>
  <h2>Liste des clients</h2>
  <a href="clients?action=new">+ Nouveau client</a>
  <table border="1" cellpadding="5">
    <tr><th>ID</th><th>Nom</th><th>Prénom</th><th>Email</th><th>Téléphone</th><th>Adresse</th><th>Actions</th></tr>
    <c:forEach var="cl" items="${clients}">
      <tr>
        <td>${cl.id}</td>
        <td><c:out value="${cl.nom}"/></td>
        <td><c:out value="${cl.prenom}"/></td>
        <td><c:out value="${cl.email}"/></td>
        <td><c:out value="${cl.telephone}"/></td>
        <td><c:out value="${cl.adresse}"/></td>
        <td>
          <a href="clients?action=edit&id=${cl.id}">Modifier</a> |
          <a href="clients?action=delete&id=${cl.id}" onclick="return confirm('Supprimer ce client ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>