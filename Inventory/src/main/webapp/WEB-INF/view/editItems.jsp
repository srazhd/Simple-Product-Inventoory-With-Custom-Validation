<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

.brandid {
	font-size: 18px;
	font-weight: bold;
	width: 255px;
	padding: 3px 0px;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-md-12" style="margin-top: 10px; margin-bottom: 10px;">
			<a href="/Inventory/brand/" class="btn btn-primary">Brand </a> <a
				href="/Inventory/models/" class="btn btn-primary">Models </a> <a
				href="/Inventory/items/" class="btn btn-primary">Items </a>
		</div>

		<br>
		<div class="col-md-10" style="float: left;">
			<div class="col-md-12"
				style="float: left; border-bottom: 2px solid #000; margin-top: 10px; margin-bottom: 10px;">
				<h2>Edit Item</h2>
			</div>
			<div class="col-md-12"
				style="float: left; margin-top: 10px; margin-bottom: 10px;">
				<h3>Field with marked * are mandatory</h3>
			</div>
			<div class="col-md-12" style="float: left;">
				<form:form action="/Inventory/items/editItems" method="post"
					modelAttribute="item">
					<form:hidden path="id" />
					<table>

						<tr style="font-size: 18px; font-weight: bold;">
							<td>Brand Name * &nbsp</td>
							<td>&nbsp:&nbsp</td>
							<td><form:select path="brand.id" class="brandid">
									<form:option value="0" label=" Select Brand ..." />
									<form:options items="${brandMap}" />
								</form:select></td>
							<td><form:errors path="brand.id" cssClass="error" /></td>
						</tr>

						<tr style="font-size: 18px; font-weight: bold;">
							<td>Model Name * &nbsp</td>
							<td>&nbsp:&nbsp</td>
							<td><form:select path="models.id" class="brandid">
									<form:option value="0" label=" Select Model ..." />
									<form:options items="${modelMap}" />
								</form:select></td>
							<td><form:errors path="models.id" cssClass="error" /></td>
						</tr>

						<tr style="font-size: 18px; font-weight: bold;">
							<td>Item Name * &nbsp</td>
							<td>&nbsp:&nbsp</td>
							<td><form:input path="name" class="form-control" type="text"
									name="name" style="font-size:18px;font-weight: bold;" /></td>
							<td><form:errors path="name" cssClass="error" /></td>
						</tr>

						<tr>

							<td></td>
							<td></td>
							<td><input class="btn btn-primary" type="submit"
								value="SUBMIT" style="margin-top: 5px;" /></td>
							<td></td>
						</tr>
					</table>
				</form:form>
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