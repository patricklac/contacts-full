<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Liste contacts</title>
  </head>
  <body>
    <h2>Liste contacts</h2>
      <c:forEach items="${contacts}" var="contact" >
        <li>${contact.nom} : ${contact.telephone}</li>
      </c:forEach>

  </body>
</html>
