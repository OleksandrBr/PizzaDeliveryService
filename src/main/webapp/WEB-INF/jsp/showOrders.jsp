<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzas List</title>
</head>
<body>
	Your balance : ${accumulCard.accumulativeSum} </br>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Customer</th>
			</tr>
		</thead>

		<c:forEach var="order" items="${orders}">
			<tr>
				<td>${order.id}</td>
				<td>${order.name}</td>
				<td>${order.customer.name}</td>
				<td>
					<form method="get" action="showOrder">
						<input type="hidden" name="id" value="${order.id}" /> <input
							type="submit" value="Show details" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>