<%-- 
    Document   : take_quiz
    Created on : Feb 2, 2021, 11:33:45 AM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <jsp:include page="welcome.jsp" flush="true"/>
        <font color="red">
        ${requestScope.WARNING}</br>
        </font>
        <b id="timeCounter"></b></br>
        <script>
            var count = 0;
            var timeLength = ${requestScope.TIME_LENGTH};

            function convert(time) {
                var hour = Math.floor(time / 3600);
                var minute = Math.floor(time / 60);
                var second = time % 60;
                if (hour < 10) {
                    hour = "0" + hour;
                }
                if (hour > 0) {
                    if (minute >= 60) {
                        minute -= 60;
                    }
                }
                if (minute < 10) {
                    minute = "0" + minute;
                }
                if (second < 10) {
                    second = "0" + second;
                }
                return hour + ":" + minute + ":" + second;
            }

            function setTime() {
                if (timeLength - count > 0) {
                    document.getElementById("timeCounter").innerHTML = convert(timeLength - count);
                    count++;
                } else if (timeLength - count == 0) {
                    document.getElementById("timeCounter").innerHTML = "TIME OUT";
                    count++;
                } else {
                    document.getElementById("done").click();
                    clearInterval(timeSetting);
                }
            }
            var timeSetting = setInterval(setTime, 1000);
        </script>

        <c:set var="list" value="${requestScope.QUIZ}"/>
        <form  action="submit" method="POST">
            <div style="height: 300px; weigth:150px;overflow-y:scroll;">
                <c:forEach var="question" items="${list}" varStatus="counter">
                    Question ${counter.count}:${question.content}</br>
                    <input type="radio" name="answer${counter.count}" value="${question.option1}"/>${question.option1}</br>
                    <input type="radio" name="answer${counter.count}" value="${question.option2}"/>${question.option2}</br>
                    <input type="radio" name="answer${counter.count}" value="${question.correctAnswer}"/>${question.correctAnswer}</br>
                    <input type="radio" name="answer${counter.count}" value="${question.option3}"/>${question.option3}</br>     

                    <input type="hidden" name="question${counter.count}" value="${question.id}"/>
                    <input type="hidden" name="correctAnswer${counter.count}" value="${question.correctAnswer}"/>
                </c:forEach>
            </div>
            <input type="hidden" name="quizID" value="${requestScope.QUIZID}"/>
            <input type="hidden" name="size" value="${list.size()}"/>
            <input type="hidden" name="subjectID" value="${requestScope.SELECTED_SUBJECT}"/>
            <input id="done" type="submit" value="Submit"/>    
        </form>

    </body>
</html>
