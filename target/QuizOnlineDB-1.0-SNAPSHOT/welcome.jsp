<%-- 
    Document   : welcome
    Created on : Jan 25, 2021, 4:42:04 PM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>

<c:set var="user" value="${sessionScope.USER}"/>
<c:choose>
    <c:when test="${not empty user}">
        <font color="red">
        Welcome ${user.name}</br>
        </font>
        <a href="logout">Log out</a></br>
        <c:choose>
            <c:when test="${user.roleID == 1}">
                <a href="defaultUserPage">Back to search page</a>
            </c:when>
            <c:otherwise>
                <a href="defaultUserPage">Back to get quiz page</a></br>
                <c:if test="${not empty sessionScope.QUIZ_EXAM}">
                    <a href="takeAQuiz">Back to take quiz</a>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <a href="singIn">Go to login page</a></br>
        <a href="signUp">Create new account</a>
    </c:otherwise>
</c:choose>
