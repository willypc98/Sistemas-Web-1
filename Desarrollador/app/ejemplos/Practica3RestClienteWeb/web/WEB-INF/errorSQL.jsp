<%-- 
    Document   : errorSQL
    Created on : Abr, 2020, 11:50:52 AM
    Author     : darth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="5;url=${requestScope.nextPage}">
        <title>SQL ERROR Produced</title>
    </head>
    <body>
        <h1>Something went wrong</h1>
        <p>${requestScope.errorMessage}</p>
        <p>Details:</p>
        <p>${requestScope.error}</p>
    </body>
</html>
