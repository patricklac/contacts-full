<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Ajoute contact</title>
  </head>
  <body>
    <h2>Ajoute contact</h2>

    <form action="ajoute" method="post">
      <div id="nom">
      <label for="nom">nom</label>
      <input type="text" name="nom" value="${nom}">
      </div>
      <div id="telephone">
        <label for="telephone">téléphone</label>
        <input type="text" name="telephone" value="${telephone}"/>
      </div>
      <button type="submit" name="Submit">Ajoute</button>
    </form>
    <c:if test="${errors != null}">
      Erreurs:
      <ul>
        <c:forEach items="${errors}" var="error">
          <li>${error}</li>
        </c:forEach>
      </ul>
    </c:if>
  </body>
</html>
