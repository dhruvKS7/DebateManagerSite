<%--
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page displays sent messages to regular users
--%>

<%--Linking page to servlet and accessing other database languages below:--%>
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--Linking to other classes below:--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inbox Page</title>
        <link rel="stylesheet" type="text/css" href="StylingSheet.css"/>
    </head>
    <%--Creating body below:--%>
    <body id="noScrollBackground">
        <%--Creating navbar for user to navigate between pages below:--%>
        <div class="header">
            <nav>
                <ul>
                    <%--Linking the nav bar to other HTML pages below:--%>
                    <li>||</li>
                    <li>
                        <form action="teamMembers" method="POST">
                            <input id="navBarButton" type="submit" value="TEAM"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li><form action="leadership" method="POST">
                            <input id="navBarButton" type="submit" value="LEADERSHIP"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li>
                        <form action="conferences" method="POST">
                            <input id="navBarButton" type="submit" value="CONFERENCES"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li><form action="inbox" method="POST">
                            <input id="navBarButton" type="submit" value="INBOX"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li><form action="awards" method="POST">
                            <input id="navBarButton" type="submit" value="AWARDS"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li><form action="logOut" method="POST">
                            <input id="navBarButton" type="submit" value="LOG OUT"/>
                        </form></li>
                    <li>||</li>
                </ul>
            </nav>
        </div>
        <br>
        <br>
        <br>
        <br>
        <%--Adding icon below:--%>
        <img id="inboxRegImage" src="message.png" />
        <%--Creating header below:--%>
        <p id="announcementText">INBOX</p>
        <%--Creating table to display data from database below:--%>
        <div id="inboxTable">
            <table border="3" width="50%" id="tableTest">
                <tr>
                    <th>Sender</th>
                    <th id="messageColumn">Message</th>
                </tr>
                <%--Receiving data from servlet below:--%>
                <c:forEach var="row" items="${dbData}">
                    <tr>
                        <td><c:out value="${row.get(3)}"/></td>
                        <td><c:out value="${row.get(2)}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
