<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<body>
<h2>Welcome to DigitalPasswordWallet!</h2>
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
</body>
</html>
