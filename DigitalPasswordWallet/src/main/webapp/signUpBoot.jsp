<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title>DigitalPasswordWallet!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
<nav
		class="navbar navbar-expand-sm bg-primary navbar-dark justify-content-end">
		<div class="container-fluid">
			
			<p class="font-weight-bold">
				<label for="DigitalPasswordWallet">DigitalPasswordWallet</label>
			</p>
			<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
					href="http://localhost:8080/DigitalPasswordWallet/signIn.jsp">
						<span class="glyphicon glyphicon-log-in"></span> Sign In
				</a></li>
			</ul>
		
		</div>
	</nav>
	
	
	
	
	<div class="container-fluid" style="height: 900px;">
	<div class="main-w3ls-logo">
			<form action="signup" method="post">
<pre>
<h4><font color="blue">${userExist}</font></h4>
<h4><font color="red">${email}</font></h4>
Email Id:<input type="email" name="email" id="email"/>

<h4><font color="red">${mobileNo}</font></h4>
Mobile No:<input type="number" name="mobileNo" id="mobileNo" maxlength="10" size="10"/> 

<h4><font color="red">${userName}</font></h4>
User Name:<input type="text" name="userName" id="userName"/>

Type: <select name="userType">
<option value=""></option>
<option value="individual">Individual</option>
<option value="professional">professional</option>
</select>
<input type="submit" value="Submit" name="Submit" /> <input type="reset" value="Clear" name="Clear" />
</pre>
</form>

</div>
</div>
			
<nav
	class="navbar fixed-bottom  bg-primary navbar-dark justify-content-center">	
	<div class="footer-copyright text-center py-3">
			© 2019 Copyright: <a
				href="https://www.cmu.edu/iso/governance/guidelines/password-management.html"
				class="text-body"> DigitalPasswordWallet.com</a>
		</div>
		<!-- Copyright -->
	</nav>	

</body>
</html>
