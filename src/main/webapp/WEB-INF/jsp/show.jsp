<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzas List</title>
</head>
<body>
	User name: ${name} </br>
    Roles: ${roles} </br>
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
				<sec:authorize access="hasRole('ADMIN')">
					<td>
						<form method="get" action="edit">
							<input type="hidden" name="id" value="${pizza.id}" /> <input
								type="submit" value="Edit" />
						</form>
					</td>
				</sec:authorize>
				<td>
					<form method="post" action="addtoorder">
						<input type="hidden" name="id" value="${pizza.id}" /> 
						<input type="submit" value="Add to order" />
						<input type="hidden" 
				               name="${_csrf.parameterName}" 
				               value="${_csrf.token}"/>
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
	<sec:authorize access="hasRole('ADMIN')">
		<form method="get" action="create">
			<input type="submit" value="Create new pizza" />
			<input type="hidden" 
               name="${_csrf.parameterName}" 
               value="${_csrf.token}"/>
		</form>
	</sec:authorize>
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Amount</th>
			</tr>
		</thead>
		<c:forEach var="pizza" items="${pizzaSet}">
			<tr>
				<td>${pizza.key.name}</td>
				<td>${pizza.value}</td>
			</tr>
		</c:forEach>
	</table>
	Total price : ${totalPrice} </br>
	<form method="post" action="saveOrder">
		<input type="submit" value="Save order" />
		<input type="hidden" 
               name="${_csrf.parameterName}" 
               value="${_csrf.token}"/>
	</form>
	<form method="get" action="../orders/show">
		<input type="submit" value="Show yours orders" />
		<%-- <input type="hidden" 
               name="${_csrf.parameterName}" 
               value="${_csrf.token}"/> --%>
	</form>
	
	<c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out" />
        <input type="hidden" 
               name="${_csrf.parameterName}" 
               value="${_csrf.token}"/>
    </form>
</body>
</html>