<%-- 
    Document   : login
    Created on : Jan 24, 2021, 1:09:10 PM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            <c:if test="${not empty param.request}">
                <font color="red">
                You must login firstly</br>
                </font>
            </c:if>
            <c:if test="${not empty param.success}">
                <font color="red">
                You have registered successfully</br>
                </font>
            </c:if>
            Email:<input type="text" name="txtEmail" value="${param.txtEmail}"/>
            <font color="red">
            ${param.inexistence}
            </font>
            </br>
            Password:<input type="password" name="txtPassword" /></br>
            <input type="submit" name="btnAction" value="Login"/>
            <c:if test="${not empty param.error}">
                <font color="red">
                Invalid email and password.Please try again</br>
                </font>
            </c:if>
            <a href="signUp">Registry</a>
        </form>
    </body>
</html>
