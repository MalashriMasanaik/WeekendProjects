<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<!-- <h2>Login to Digital PSW wallet</h2> -->
	<h1>you have successfully changed your psw! Please login</h1>
	<form action="signIn" method="post">
		<pre>
UserName:<input type="text" name="Uname" id="Uname" />
Password:<input type="text" name="PSW" id="PSW" maxlength="8" size="8" /> 
<input type="submit" value="submit" name="submit" /> <input
				type="reset" value="clear" name="clear" />
</pre>
	</form>
</body>
</html>
