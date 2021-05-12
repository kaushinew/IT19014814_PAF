<%@page import="Model.Buyer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buyer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/buyer.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Buyer Management V10.1</h1>
<form id="formItem" name="formItem">
 User ID: 
 <input id="userID" name="userID" type="text" 
 class="form-control form-control-sm">
 <br> Product ID: 
 <input id="productID" name="productID" type="text" 
 class="form-control form-control-sm">
 <br> Quantity: 
 <input id="quantity" name="quantity" type="text" 
 class="form-control form-control-sm">

 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Buyer buyerObj = new Buyer(); 
 out.print(buyerObj.readbuyer()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>