<%-- 
    Document   : search
    Created on : Jan 27, 2021, 2:53:06 PM
    Author     : khang nguyen

//van ko hieu sao cai status no co gia tri null ma no van thoa cai dk = 0


--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Page</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <jsp:include page="welcome.jsp" flush="true"/> 
        <c:if test="${not empty param.create}">
            <font color="red">
            You have created successfully
            </font>
        </c:if>
        <c:if test="${not empty param.delete}">
            <font color="red">
            You have deleted successfully
            </font>
        </c:if>
        <c:if test="${not empty param.update}">
            <font color="red">
            You have update successfully
            </font>
        </c:if>
        <a href="questionCreatePage">Create Question</a>
        <form action="search" method="POST">
            Search By Subject Name: <select name="selectedSubjectName">
                <option value= "" >
                    -----
                </option>
                <c:forEach var="subject" items="${requestScope.SUBJECT}">
                    <c:choose>
                        <c:when test="${subject.id == param.selectedSubjectName}">
                            <option value="${subject.id}" selected>
                                ${subject.name}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${subject.id}">
                                ${subject.name}
                            </option>
                        </c:otherwise>    
                    </c:choose>
                </c:forEach>
            </select>
            Search By Question Name:<input type="text" name="txtSearchName" value="${param.txtSearchName}"/></br>
            <c:choose> 
                <c:when test="${empty param.selectedQuestionStatus }">
                    Active:<input type="radio" name="selectedQuestionStatus" value="1" />
                    InActive:<input type="radio" name="selectedQuestionStatus" value="0"/>
                    All:<input type="radio" name="selectedQuestionStatus" value="" checked="true" />
                </c:when> 
                <c:when test="${param.selectedQuestionStatus == 0}">
                    Active:<input type="radio" name="selectedQuestionStatus" value="1" />
                    InActive:<input type="radio" name="selectedQuestionStatus" value="0" checked="true"/>
                    All:<input type="radio" name="selectedQuestionStatus" value="" />
                </c:when>
                <c:otherwise>
                    Active:<input type="radio" name="selectedQuestionStatus" value="1" checked="true"/>
                    InActive:<input type="radio" name="selectedQuestionStatus" value="0"/>
                    All:<input type="radio" name="selectedQuestionStatus" value="" />
                </c:otherwise>    
            </c:choose>
            <input type="submit" value="Search"/>
        </form>
        <c:choose>
            <c:when test="${empty requestScope.QUESTION}">
                <font color="red">
                No result.Please search another
                </font>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Subject Name</th>
                            <th>Creator</th>
                            <th>Question's Content</th>
                            <th>
                                Option 1
                            </th>
                            <th>
                                Option 2
                            </th>
                            <th>
                                Option 3
                            </th>
                            <th>
                                Correct Answer
                            </th>
                            <th>
                                Status
                            </th>
                            <th>
                                Update 
                            </th>
                            <th>
                                Delete
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="question" items="${requestScope.QUESTION}" varStatus="counter">
                            <c:choose>
                                <c:when test="${question.id == param.invalidQuestion}">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                <form action="questionUpdate" method="POST">
                                    <td>
                                        <select name="selectedSubject">
                                            <c:forEach var="subject" items="${requestScope.SUBJECT}">
                                                <c:choose>
                                                    <c:when test="${question.subject == subject.id}">
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
                                    </td>
                                    <td>
                                        ${question.user}
                                    </td>
                                    <td>
                                        <input type="text" name="selectedContent" value="${question.content}"/>
                                        <font color="red">
                                        ${error['content']}
                                        </font>
                                    </td>
                                    <td>
                                        <input type="text" name="selectedOption1" value="${question.option1}"/>
                                        <font color="red">
                                        ${error['option1']}
                                        </font>
                                    </td>
                                    <td>
                                        <input type="text" name="selectedOption2" value="${question.option2}"/>
                                        <font color="red">
                                        ${error['option2']}
                                        </font>
                                    </td>
                                    <td>
                                        <input type="text" name="selectedOption3" value="${question.option3}"/>
                                        <font color="red">
                                        ${error['option3']}
                                        </font>
                                    </td>
                                    <td>
                                        <input type="text" name="selectedCorrectAnswer" value="${question.correctAnswer}"/>
                                        <font color="red">
                                        ${error['correctAnswer']}
                                        </font>
                                    </td>
                                    <td>
                                        <select name="selectedStatus">
                                            <c:choose>
                                                <c:when test="${question.status == 1}">
                                                    <option value="1" selected>
                                                        Active
                                                    </option>
                                                    <option value="0">
                                                        InActive
                                                    </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="0" selected>
                                                        InActive
                                                    </option>
                                                    <option value="1" >
                                                        Active
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update"/>
                                        <input type="hidden" value="${question.id}" name="updatedQuestion"/>
                                        <input type="hidden" name="currentPage" value="${param.currentPage}"/>
                                        <input type="hidden" name="selectedSubjectName" value="${param.selectedSubjectName}"/>
                                        <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"/>
                                        <input type="hidden" name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
                                    </td>
                                </form>
                                <td>
                                    <c:if test="${question.status == 1}">
                                        <form action="questionDelete" method="POST">
                                            <input type="hidden" name="deletedQuestion" value="${question.id}"/>
                                            <input type="submit" value="Delete"/>
                                            <input type="hidden" name="currentPage" value="${param.currentPage}"/>
                                            <input type="hidden" name="selectedSubjectName" value="${param.selectedSubjectName}"/>
                                            <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"/>
                                            <input type="hidden" name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                            <form action="questionUpdate" method="POST">
                                <td>
                                    <select name="selectedSubject">
                                        <c:forEach var="subject" items="${requestScope.SUBJECT}">
                                            <c:choose>
                                                <c:when test="${question.subject == subject.id}">
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
                                </td>
                                <td>
                                    ${question.user}
                                </td>
                                <td>
                                    <input type="text" name="selectedContent" value="${question.content}"/>
                                </td>
                                <td>
                                    <input type="text" name="selectedOption1" value="${question.option1}"/>
                                </td>
                                <td>
                                    <input type="text" name="selectedOption2" value="${question.option2}"/>
                                </td>
                                <td>
                                    <input type="text" name="selectedOption3" value="${question.option3}"/>
                                </td>
                                <td>
                                    <input type="text" name="selectedCorrectAnswer" value="${question.correctAnswer}"/>
                                </td>
                                <td>
                                    <select name="selectedStatus">
                                        <c:choose>
                                            <c:when test="${question.status == 1}">
                                                <option value="1" selected>
                                                    Active
                                                </option>
                                                <option value="0">
                                                    InActive
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0" selected>
                                                    InActive
                                                </option>
                                                <option value="1" >
                                                    Active
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                                <td>
                                    <input type="submit" value="Update"/>
                                    <input type="hidden" value="${question.id}" name="updatedQuestion"/>
                                    <input type="hidden" name="currentPage" value="${param.currentPage}"/>
                                    <input type="hidden" name="selectedSubjectName" value="${param.selectedSubjectName}"/>
                                    <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"/>
                                    <input type="hidden" name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
                                </td>
                            </form>
                            <td>
                                <c:if test="${question.status == 1}">
                                    <form action="questionDelete" method="POST">
                                        <input type="hidden" name="deletedQuestion" value="${question.id}"/>
                                        <input type="submit" value="Delete"/>
                                        <input type="hidden" name="currentPage" value="${param.currentPage}"/>
                                        <input type="hidden" name="selectedSubjectName" value="${param.selectedSubjectName}"/>
                                        <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"/>
                                        <input type="hidden" name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>
</c:otherwise>    
</c:choose>

<c:if test="${not empty requestScope.PREVIOUS_PAGE}">
    <c:url var="previousPage" value="defaultUserPage">
        <c:param name="currentPage" value="${requestScope.PREVIOUS_PAGE}"/>
        <c:param name="selectedSubjectName" value="${param.selectedSubjectName}"/>
        <c:param name="txtSearchName" value="${param.txtSearchName}"/>
        <c:param name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
    </c:url>
    <a href="${previousPage}"> << </a>
</c:if>                
<c:choose>
    <c:when test="${empty param.currentPage}">
        <font color="red">
            Page:1
        </font> 
    </c:when>
    <c:otherwise>
        <font color="red">
         Page:${param.currentPage}
        </font> 
    </c:otherwise>
</c:choose>             
<c:if test="${not empty requestScope.NEXT_PAGE}">
    <c:url var="nextPage" value="defaultUserPage">
        <c:param name="currentPage" value="${requestScope.NEXT_PAGE}"/>
        <c:param name="selectedSubjectName" value="${param.selectedSubjectName}"/>
        <c:param name="txtSearchName" value="${param.txtSearchName}"/>
        <c:param name="selectedQuestionStatus" value="${param.selectedQuestionStatus}"/>
    </c:url>
    <a href="${nextPage}"> >> </a>
</c:if>        

</body>
</html>


