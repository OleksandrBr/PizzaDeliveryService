<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzas List</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Type</th>
				<th>Price</th>
			</tr>
		</thead>

		<c:forEach var="pizza" items="${pizzas}">
			<tr>
				<td>${pizza.id}</td>
				<td>${pizza.name}</td>
				<td>${pizza.type}</td>
				<td>${pizza.price}</td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="createTestOrder">
		<input type="submit" value="Create new order" />
	</form>
</body>
</html>