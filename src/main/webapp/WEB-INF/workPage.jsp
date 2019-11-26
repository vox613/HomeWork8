<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Application</title>
</head>
<body>

<h2>Directories:</h2>

<c:forEach var="num" items="${foldersList}">
    <div>${num}</div>
</c:forEach>

</body>
</html>



