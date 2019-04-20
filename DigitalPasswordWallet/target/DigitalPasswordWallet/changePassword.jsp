<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Change Password</h2>

<h4><font color="red">${wrongPSW}</font></h4>
<h4><font color="red">${alreadychanged}</font></h4>
	<form action="setPSW" method="GET">
		<pre>
Old Password:<input type="text" name="oldPSW" id="oldPSW" />
New Password:<input type="text" name="newPSW" id="newPSW" maxlength="8" size="8" /> 
<h4><font color="red">${PSWMismatch}</font></h4>
Confirm Password:<input type="text" name="confirmPSW" id="confirmPSW"
				maxlength="8" size="8" />
Secure Phase:<input type="text" name="securePhase" id="securePhase"
				maxlength="40" size="40" />

<input type="submit" value="submit" name="submit" /> <input
				type="reset" value="clear" name="clear" />
</pre>
	</form>
</body>
</html>
