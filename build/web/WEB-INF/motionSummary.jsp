<%--
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page displays the awards to regular users
--%>

<%--Linking page to servlet and accessing other database languages below:--%>
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--Declaring head below:-->
    <head>
        <%--Linking to other classes below:--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Awards Page</title>
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
                        <form action="teamMembersAdmin" method="POST">
                            <input id="navBarButton" type="submit" value="TEAM"/>
                        </form>
                    </li>
                    <li>||</li>
                    <li><form action="leadershipAdmin" method="POST">
                            <input id="navBarButton" type="submit" value="LEADERSHIP"/>
                        </form></li>
                    <li>||</li>
                    <li><form action="conferencesAdmin" method="POST">
                            <input id="navBarButton" type="submit" value="CONFERENCES"/>
                        </form></li>
                    <li>||</li>
                    <li><form action="mockRunnerHome" method="POST">
                            <input id="navBarButton" type="submit" value="MOCK RUNNER"/>
                        </form></li>
                    <li>||</li>
                    <li>
                        <%--Creating dropdown for navbar below:--%>
                        <div id="navBarButton" class="dropdown">
                            <button id="navBarButton">MESSAGING</button>
                            <div class="dropdown-content" id="navBarColor">
                                <form action="inboxAdmin" method="POST">
                                    <input id="navBarButton" type="submit" value="   VIEW INBOX   "/>
                                </form>
                                <a href="#popup6" id="navBarButton">SEND MESSAGE</a>
                            </div>
                        </div>
                    </li>
                    <li>||</li>
                    <li><form action="awardsAdmin" method="POST">
                            <input id="navBarButton" type="submit" value="AWARDS"/>
                        </form></li>
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
        <img id="passedMotionsRegImage" src="scroll.png" />
        <%--Creating header below:--%>
        <p id="announcementText">PASSED MOTIONS</p>
        <%--Creating table to display data from database below:--%>
        <div id="passedMotionsTable">
            <table border="3" width="50%" id="tableTest">
                <tr>
                    <th>Motion</th>
                    <th>Total Time</th>
                    <th>Speaking Time</th>
                    <th>Country</th>
                </tr>
                <%--Receiving data from servlet below:--%>
                <c:forEach var="row" items="${dbData}">
                    <tr>
                        <td><c:out value="${row.get(1)}"/></td>
                        <td><c:out value="${row.get(2)}"/></td>
                        <td><c:out value="${row.get(3)}"/></td>
                        <td><c:out value="${row.get(4)}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <br>
        <br>
        <%--Creating button to exit below:--%>
        <form id="passedMotionsButton" action="mockRunnerHome" method="POST">
            <input id="mockButtonTest" type="submit" value="END MOCK"/>
        </form>
    </body>
</html>
