<%-- 
    Document   : quiz
    Created on : Jan 24, 2021, 10:45:42 PM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
    </head>
    <body>
        <jsp:include page="welcome.jsp" flush="true"/>  
        <form action="searchHistory" method="POST">
            Search:
            <select name="selectedSubject">
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <option value="${subject.id}">
                        ${subject.name}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="View history"/>
            <c:if test="${not empty param.noResult}">
                <font color="red">
                NO RESULT
                </font>
            </c:if>
        </form>
        <form action="takeAQuiz" method="POST">
            Subject:
            <select name="selectedSubject">
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <option value="${subject.id}">
                        ${subject.name}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="Attempt quiz"/>            
        </form>
        <c:if test="${not empty requestScope.RESULT_IN_EXAM}">
            <c:set var="result" value="${requestScope.RESULT_IN_EXAM}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>State</th>
                        <th>Marks</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            ${result.subjectID}
                        </td>
                        <td>
                            <fmt:formatDate value="${requestScope.SUBMIT_DATE}" pattern="dd/MM/yyyy" var="date"/>
                            ${date}
                        </td>
                        <td>
                            ${result.numberOfCorrectAnswer}/${result.totalQuestion}
                        </td>
                        <td>
                            ${result.mark}/10
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>


        <c:if test="${not empty requestScope.HISTORY}">
            <c:set var="list" value="${requestScope.HISTORY}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>SubjectID</th>
                        <th>State</th>
                        <th>Marks</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="history" items="${list}" varStatus="counter">
                        <tr>
                            <td>
                                ${history.subjectID}
                            </td>
                            <td>
                                ${history.historyOfDate}
                            </td>
                            <td>
                                ${history.numberOfCorrectAnswer}/${history.totalQuestion}
                            </td>
                            <td>
                                ${history.mark}/10
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>
