<%--
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page displays an error message to the user
--%>
<!--Imports below;-->
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <!--Declaring header below:-->
    <head>
        <%--Linking other classes below:--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="StylingSheet.css"/>
        <title>Error Page</title>
    </head>
    <%--Creating body below:--%>
    <body id="noScrollBackground">
        <%--Displaying an error message for the user below:--%>
        <h1 id="errorText">ERROR!</h1>
        <h2 id="errorText">${errorReason}</h2>
        <%--Allowing the user to return to home page below:--%>
        <form id="errorButton" action="returnError" method="POST">
            <input id="entryButtonTest" type="submit" value="RETURN">
        </form>
    </body>
</html>
