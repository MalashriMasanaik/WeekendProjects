<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<form action="secure" method="post">
		<pre>
<h4><font color="red">${securePhase}</font></h4>
Secure Phase:<input type="password" name="securePhase" id="securePhase" />
<input type="submit" value="Submit" name="Submit" /> <input
				type="reset" value="Clear" name="Clear" />
</pre>
	</form>
</body>
</html>
