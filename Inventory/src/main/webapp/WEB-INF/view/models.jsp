<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body>
	<div class="container">
		<div class="col-md-12" style="margin-top: 10px; margin-bottom: 10px;">
			<a href="/Inventory/brand/" class="btn btn-primary">Brand </a> 
			<a href="/Inventory/models/" class="btn btn-primary">Models </a> 
			<a href="/Inventory/items/" class="btn btn-primary">Items </a>
		</div>
		
		<br>
	<div class="col-md-12" style="float: left;">
		<div class="col-md-12" style=" float:left; border-bottom:2px solid #000; margin-top: 10px; margin-bottom: 10px;">
			<h2>Models</h2>
		</div>
		
		<div class="col-md-12" style="float: left;">

		<table class="table table-bordered ">
			<tr>
				<td>SL</td>
				<td>Model Name</td>
				<td>Brand Name</td>
				<td>Entry Date</td>
				<td>Action</td>
			</tr>

					<c:forEach var="b" items="${modelList}">
					    <tr>
					      <td>${b.id}</td>
					      <td>${b.name}</td>
					      <td>${b.brand.name}</td>
					      <td>${b.entryDate}</td>
					      <td>
					      <a href="/Inventory/models/edit/${b.id}" class="btn btn-warning">EDIT</a>
					      <a href="/Inventory/models/delete/${b.id}" onclick="return confirm('Are You Sure To Delete The Selected Model?')" class="btn btn-danger">DELETE</a>
					      
					      </td>
					    </tr>
				 </c:forEach>

</table>
		</div>
		
		<div class="col-md-12" style=" float:left; margin-top: 10px; margin-bottom: 10px;">
			<a href="/Inventory/models/addModels" class="btn btn-primary">Add New Model </a>
		</div>
		</div>
		

		
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>
</html>