<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<form action="signIn" method="post">
		<pre>
<h4><font color="red">${useraname}</font></h4>

UserName:<input type="text" name="Uname" id="Uname"/>
<h4><font color="red">${password}</font></h4>
Password:<input type="password" name="PSW" id="PSW" maxlength="8" size="8" /> 
<input type="submit" value="Submit" name="Submit" /> <input
				type="reset" value="Clear" name="Clear" />
</pre>
	</form>
</body>
</html>
