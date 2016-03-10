<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<form id="form" name="form" method="post" action="edit">
		<table>
			<tr>
				<th>name</th>
				<th><input type="text" name="name" id="name"
					value="${pizza.name}" /></th>
			</tr>
			<tr hidden="true">
				<th>id</th>
				<th><input type="text" name="id" id="id"
					value="${pizza.id}" /></th>
			</tr>
			<tr>
				<th>price</th>
				<th><input type="text" name="price" id="price"
					value="${pizza.price}" /></th>
			</tr>
			<tr>
				<th>type</th>
				<th><input type="text" name="type" id="type"
					value="${pizza.type}" /></th>
			</tr>
			<tr>
				<th></th>
				<th><input type="submit" value="save"/></th>
			</tr>
		</table>
	</form>

</body>
</html>