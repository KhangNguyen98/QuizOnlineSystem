<%-- 
    Document   : error403
    Created on : Jan 30, 2021, 10:32:53 AM
    Author     : khang nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied Page</title>
    </head>
    <body>
        <jsp:include page="welcome.jsp" flush="true"></jsp:include>
        </br> <font color="red">You don't have permission to access this page.Sorry!</font></br>
    </body>
</html>
