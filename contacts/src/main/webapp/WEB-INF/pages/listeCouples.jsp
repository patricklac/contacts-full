<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Liste couples</title>
</head>
<body>
<h2>Liste couples</h2>
  <c:forEach items="${contactsInCouple}" var="contact" >
    <li>${contact.nom} &nbsp; ${contact.conjoint.nom}</li>
  </c:forEach>

</body>
</html>
