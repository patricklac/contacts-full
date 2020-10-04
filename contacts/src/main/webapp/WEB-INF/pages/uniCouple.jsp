<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Union couple</title>
  </head>
  <body>
    <h2>Union Couple</h2>

    <form action="uniCouple" method="post">
      <div id="nom1">
      <label for="nom1">nom1</label>
      <input type="text" name="nom1" value="${nom1}">
      </div>
      <div id="nom2">
        <label for="nom2">nom2</label>
        <input type="text" name="nom2" value="${nom2}">
      </div>
      <button type="submit" name="Submit">uniCouple</button>
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
