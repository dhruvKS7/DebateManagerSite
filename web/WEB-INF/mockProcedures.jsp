<%--
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page allows the user to carry out tasks related to mock conferences
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
        <title>JSP Page</title>
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
        <img id="procedureImage" src="procedure.png" />
        <%--Creating header below:--%>
        <p id="mockAnnouncementText">MOCK RUNNER</p>
        <div id="buttonDiv">
            <%--Creating button to insert data into database below:--%>
            <form name="paperVote" id="updateLeaderButton" href="#popup1" method="POST">
                <a href="#popup1" id="mockProcedureButton"><p id="buttonText">PAPER VOTING</p></a>
            </form>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <%--Creating button to insert data from database below:--%>
            <form name="motionVote" id="updateLeaderButton" href="#popup2" method="POST">
                <a href="#popup2" id="mockProcedureButton"><p id="buttonText">SUBMIT MOTION</p></a>
            </form>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <%--Creating button to insert data from database below:--%>
            <form id="resumeMockButton" action="timerPage" method="POST">
                <input id="mockButtonTest" type="submit" value="TIME SPEECHES"/>
            </form>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <%--Creating button to insert data from database below:--%>
            <form name="motionVote" id="updateLeaderButton" href="#popup3" method="POST">
                <a href="#popup3" id="mockProcedureButton"><p id="buttonText">END MOCK</p></a>
            </form>
            <br>
            <br>
            <br>
            <br>
        </div>
        <%--Creating popup to receive data to insert into database below:--%>
        <div id="popup1" class="overlay">
            <div class="popup">
                <h2>WORKING PAPER:</h2>
                <a class="close" href="#">&times;</a>
                <div class="content">
                    <%--Creating area for user to type in data below:--%>
                    <form action="paperAnnounce" method="POST">
                        PAPER NAME: <br> <input type="text" name="paperName"/>
                        <br>
                        <br>
                        VOTES:<br> <input type="text" name="paperVotes"/>
                        <br>
                        <br>
                        <%--Creating button to send data to servlet below:--%>
                        <input class="Sbutton" type="submit" value="SUBMIT"/>
                    </form>
                </div>
            </div>
        </div>
        <%--Creating popup to receive data to insert into database below:--%>
        <div id="popup2" class="overlay">
            <div class="popup">
                <h2>MOTION SUBMISSION:</h2>
                <a class="close" href="#">&times;</a>
                <div class="content">
                    <%--Creating area for user to type in data below:--%>
                    <form action="motionAnnounce" method="POST">
                        <hr class="motionLeft"> <p class="updatingText"> MOTION #1 </p> <hr class="motionRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="countryPropose1"/>
                        <br>
                        <br>
                        MOTION TYPE: <br> <input type="text" name="motionPropose1"/>
                        <br>
                        <br>
                        SPEAKING TIME: <br> <input type="text" name="speakingPropose1"/>
                        <br>
                        <br>
                        TOTAL TIME: <br> <input type="text" name="timePropose1"/>
                        <br>
                        <br>
                        VOTES: <br> <input type="text" name="votePropose1"/>
                        <br>
                        <br>
                        <hr class="motionLeft"> <p class="updatingText"> MOTION #2 </p> <hr class="motionRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="countryPropose2"/>
                        <br>
                        <br>
                        MOTION TYPE: <br> <input type="text" name="motionPropose2"/>
                        <br>
                        <br>
                        SPEAKING TIME: <br> <input type="text" name="speakingPropose2"/>
                        <br>
                        <br>
                        TOTAL TIME: <br> <input type="text" name="timePropose2"/>
                        <br>
                        <br>
                        VOTES: <br> <input type="text" name="votePropose2"/>
                        <br>
                        <br>
                        <hr class="motionLeft"> <p class="updatingText"> MOTION #3 </p> <hr class="motionRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="countryPropose3"/>
                        <br>
                        <br>
                        MOTION TYPE: <br> <input type="text" name="motionPropose3"/>
                        <br>
                        <br>
                        SPEAKING TIME: <br> <input type="text" name="speakingPropose3"/>
                        <br>
                        <br>
                        TOTAL TIME: <br> <input type="text" name="timePropose3"/>
                        <br>
                        <br>
                        VOTES: <br> <input type="text" name="votePropose3"/>
                        <br>
                        <br>
                        <%--Creating button to send data to servlet below:--%>
                        <input class="Sbutton" type="submit" value="SUBMIT"/>
                    </form>
                </div>
            </div>
        </div>
        <%--Creating popup to receive data to insert into database below:--%>
        <div id="popup3" class="overlay">
            <div class="popup">
                <h2>AWARD WINNERS:</h2>
                <a class="close" href="#">&times;</a>
                <div class="content">
                    <%--Creating area for user to type in data below:--%>
                    <form action="inputAwards" method="POST">
                        <hr class="honorLeft"> <p class="updatingText"> HONORABLE </p> <hr class="honorRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="honor"/>
                        <br>
                        <br>
                        <hr class="outstandingLeft"> <p class="updatingText"> OUTSTANDING </p> <hr class="outstandingRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="outstanding"/>
                        <br>
                        <br>
                        <hr class="bestLeft"> <p class="updatingText"> BEST </p> <hr class="bestRight">
                        <br>
                        COUNTRY: <br> <input type="text" name="best"/>
                        <br>
                        <br>
                        <hr>
                        <br>
                        <input class="Sbutton" type="submit" value="SUBMIT"/>
                    </form>
                </div>
            </div>
        </div>
        <%--Creating popup to receive data to insert into database below:--%>
        <div id="popup6" class="overlay">
            <div class="popup">
                <h2>SEND MESSAGE:</h2>
                <a class="close" href="#">&times;</a>
                <div class="content">
                    <%--Creating area for user to type in data below:--%>
                    <form action="sendMessage" method="POST">
                        RECIPIENT USERNAME: <br><input type="text" name="userNameSend"/>
                        <br>
                        <br>
                        MESSAGE: <br><textarea rows="5" cols="10" id="messageText" name="message"></textarea>
                        <br>
                        <br>
                        <%--Creating button to send data to servlet below:--%>
                        <input class="Sbutton" type="submit" value="SEND"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>