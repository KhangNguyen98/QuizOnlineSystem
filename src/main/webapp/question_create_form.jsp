<%-- 
    Document   : question_create
    Created on : Jan 25, 2021, 5:04:35 PM
    Author     : khang nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question Page</title>
    </head>
    <body>
        <jsp:include page="welcome.jsp" flush="true"/>
        <a href="defaultUserPage">Back to search page</a></br>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="questionCreate" method="POST">
            Subject:
            <select name="selectedSubject">
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <c:choose>
                        <c:when test="${subject.id == param.selectedSubject}">
                            <option value="${subject.id}" selected>
                                ${subject.name}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${subject.id}" >
                                ${subject.name}
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            Question's content:
            <input type ="text" name="txtContent" value="${param.txtContent}"/>
            <font color="red">
            ${error['content']}
            </font>
            </br>

            Option 1:
            <input type="text" name="txtOption1" value="${param.txtOption1}"/>
            <font color="red">
            ${error['option1']}
            </font>
            </br>

            Option 2:
            <input type="text" name="txtOption2" value="${param.txtOption2}"/>
            <font color="red">
            ${error['option2']}
            </font>
            </br>

            Option 3:
            <input type="text" name="txtOption3" value="${param.txtOption3}"/>
            <font color="red">
            ${error['option3']}
            </font>
            </br>

            Correct Answer:
            <input type="text" name="txtCorrectAnswer" value="${param.txtCorrectAnswer}"/>
            <font color="red">
            ${error['correctAnswer']}
            </font>
            </br>

            <input type="submit" value="Create Food"/>
        </form>
    </body>
</html>
