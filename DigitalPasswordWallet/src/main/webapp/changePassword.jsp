<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Change Password</h2>
	<%-- Parameter myparam: <%= request.getParameter("name") %> --%>

<h4><font color="red">${wrongPSW}</font></h4>
<h4><font color="red">${alreadychanged}</font></h4>
	<form action="setPSW" method="GET">
		<pre>
		
	<%-- <h1>UserName${userName}</h1> --%>
UserName:<input type="text" name="userName" id="name" value="${userName}" readonly>
Old Password:<input type="password" name="oldPSW" id="oldPSW" />
New Password:<input type="password" name="newPSW" id="newPSW" maxlength="8" size="8" /> 
<h4><font color="red">${PSWMismatch}</font></h4>
Confirm Password:<input type="password" name="confirmPSW" id="confirmPSW"
				maxlength="8" size="8" />
Secure Phase:<input type="password" name="securePhase" id="securePhase"
				maxlength="40" size="40" />

<input type="submit" value="Submit" name="Submit" /> <input
				type="reset" value="Clear" name="Clear" />
</pre>
	</form>
</body>
</html>
