<%-- 
    Document   : register
    Created on : Jan 25, 2021, 8:53:31 AM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <a href="singIn">Turn back to login page</a></br>
        <form action="register" method="POST">
            Email:<input type="text" name="txtEmail" value="${param.txtEmail}"/>
            <font color="red">
            ${error['email']}</br>
            </font>

            Name:<input type="text" name="txtName" value="${param.txtName}"/>
            <font color="red">
            ${error['name']}</br>
            </font>

            Password:<input type="password" name="txtPassword" />
            <font color="red">
            ${error['password']}</br>
            </font>

            Confirm Password:<input type="password" name="txtConfirmPassword" />
            <font color="red">
            ${error['confirmPassword']}</br>
            </font>

            <input type="submit" name="btnAction" value="Register"/>
        </form>
    </body>
</html>
