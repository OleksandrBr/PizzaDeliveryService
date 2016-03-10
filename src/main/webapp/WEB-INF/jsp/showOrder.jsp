<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzas List</title>
</head>
<body>
	order id = ${order.id}
	Pizzas:
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Amount</th>
			</tr>
		</thead>

		<c:forEach var="pizza" items="${pizzas}">
			<tr>
				<td>${pizza.key.name}</td>
				<td>${pizza.value}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>