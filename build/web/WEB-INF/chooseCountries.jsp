<%--
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page allows a mock facilitator to select countries participating in the mock
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
        <title>Choose Countries</title>
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
        <img id="countryImage" src="globe.png" />
        <%--Creating header below:--%>
        <p id="announcementText">COUNTRY SELECTION</p>
        <br>
        <br>
        <!--Adding country information below:-->
        <div id="countrySelectionBox">
            <form action="chosenCountries" id="chooseCountryButton" method="POST">
                <div id="countries">
                    <label class="container">Afghanistan -
                        <input type="checkbox" name="country1" value="Afghanistan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country1UserName"/>
                    <label class="container">Algeria -
                        <input type="checkbox" name="country2" value="Algeria"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country2UserName"/><br>
                    <label class="container">Angola -
                        <input type="checkbox" name="country3" value="Angola"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country3UserName"/>
                    <label class="container">Argentina -
                        <input type="checkbox" name="country4" value="Argentina"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country4UserName"/>
                    <label class="container">Armenia -
                        <input type="checkbox" name="country5" value="Armenia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country5UserName"/>
                    <label class="container">Australia -
                        <input type="checkbox" name="country6" value="Australia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country6UserName"/>
                    <br>
                    <label class="container">Austria -
                        <input type="checkbox" name="country7" value="Austria"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country7UserName"/>
                    <label class="container">Azerbaijan -
                        <input type="checkbox" name="country8" value="Azerbaijan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country8UserName"/>
                    <label class="container">Bahamas -
                        <input type="checkbox" name="country9" value="Bahamas"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country9UserName"/>
                    <label class="container">Bangladesh -
                        <input type="checkbox" name="country10" value="Bangladesh"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country10UserName"/>
                    <label class="container">Belgium -
                        <input type="checkbox" name="country11" value="Belgium"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country11UserName"/><br>
                    <label class="container">Bolivia -
                        <input type="checkbox" name="country12" value="Bolivia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country12UserName"/><br>
                    <label class="container">Brazil -
                        <input type="checkbox" name="country13" value="Brazil"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country13UserName"/>
                    <label class="container">Burkina Faso -
                        <input type="checkbox" name="country14" value="Burkina Faso"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country14UserName"/>
                    <label class="container">Burundi -
                        <input type="checkbox" name="country15" value="Burundi"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country15UserName"/>
                    <label class="container">Cabo Verde -
                        <input type="checkbox" name="country16" value="Cabo Verde"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country16UserName"/>
                    <label class="container">Cameroon -
                        <input type="checkbox" name="country17" value="Cameroon"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country17UserName"/>
                    <label class="container">Canada -
                        <input type="checkbox" name="country18" value="Canada"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country18UserName"/><br>
                    <label class="container">Chad -
                        <input type="checkbox" name="country19" value="Chad"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country19UserName"/><br>
                    <label class="container">Chile -
                        <input type="checkbox" name="country20" value="Chile"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country20UserName"/><br>
                    <label class="container">China -
                        <input type="checkbox" name="country21" value="China"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country21UserName"/><br>
                    <label class="container">Colombia -
                        <input type="checkbox" name="country22" value="Colombia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country22UserName"/>
                    <br>
                    <label class="container">Congo -
                        <input type="checkbox" name="country23" value="Congo"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country23UserName"/>
                    <label class="container">Costa Rica -
                        <input type="checkbox" name="country24" value="Costa Rica"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country24UserName"/>
                    <label class="container">Croatia -
                        <input type="checkbox" name="country25" value="Croatia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country25UserName"/><br>
                    <label class="container">Cuba -
                        <input type="checkbox" name="country26" value="Cuba"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country26UserName"/><br>
                    <label class="container">Cyprus -
                        <input type="checkbox" name="country27" value="Cyprus"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country27UserName"/><br>
                    <label class="container">DPRK -
                        <input type="checkbox" name="country28" value="DPRK"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country28UserName"/><br>
                    <label class="container">DRC -
                        <input type="checkbox" name="country29" value="DRC"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country29UserName"/><br>
                    <label class="container">Denmark -
                        <input type="checkbox" name="country30" value="Denmark"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country30UserName"/>
                    <label class="container">Dominican Republic -
                        <input type="checkbox" name="country31" value="Dominican Republic"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country31UserName"/><br>
                    <label class="container">Ecuador -
                        <input type="checkbox" name="country32" value="Ecuador"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country32UserName"/><br>
                    <label class="container">Egypt -
                        <input type="checkbox" name="country33" value="Egypt"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country33UserName"/>
                    <label class="container">El Salvador -
                        <input type="checkbox" name="country34" value="El Salvador"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country34UserName"/>
                    <label class="container">Estonia -
                        <input type="checkbox" name="country35" value="Estonia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country35UserName"/><br>
                    <label class="container">Ethiopia -
                        <input type="checkbox" name="country36" value="Ethiopia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country36UserName"/><br>
                    <label class="container">Fiji -
                        <input type="checkbox" name="country37" value="Fiji"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country37UserName"/><br>
                    <label class="container">Finland -
                        <input type="checkbox" name="country38" value="Finland"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country38UserName"/><br>
                    <label class="container">France -
                        <input type="checkbox" name="country39" value="France"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country39UserName"/><br>
                    <label class="container">Gambia -
                        <input type="checkbox" name="country40" value="Gambia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country40UserName"/>
                    <label class="container">Germany -
                        <input type="checkbox" name="country41" value="Germany"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country41UserName"/><br>
                    <label class="container">Ghana -
                        <input type="checkbox" name="country42" value="Ghana"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country42UserName"/><br>
                    <label class="container">Greece -
                        <input type="checkbox" name="country43" value="Greece"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country43UserName"/>
                    <label class="container">Guatemala -
                        <input type="checkbox" name="country44" value="Guatemala"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country44UserName"/><br>
                    <label class="container">Haiti -
                        <input type="checkbox" name="country45" value="Haiti"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country45UserName"/><br>
                    <label class="container">Honduras -
                        <input type="checkbox" name="country46" value="Honduras"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country46UserName"/>
                    <label class="container">Hungary -
                        <input type="checkbox" name="country47" value="Hungary"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country47UserName"/><br>
                    <label class="container">Iceland -
                        <input type="checkbox" name="country48" value="Iceland"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country48UserName"/><br>
                    <label class="container">India -
                        <input type="checkbox" name="country49" value="India"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country49UserName"/><br>
                    <label class="container">Indonesia -
                        <input type="checkbox" name="country50" value="Indoonesia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country50UserName"/><br>
                    <label class="container">Iran -
                        <input type="checkbox" name="country51" value="Iran"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country51UserName"/><br>
                    <label class="container">Iraq -
                        <input type="checkbox" name="country52" value="Iraq"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country52UserName"/><br>
                    <label class="container">Ireland -
                        <input type="checkbox" name="country53" value="Ireland"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country53UserName"/><br>
                    <label class="container">Israel -
                        <input type="checkbox" name="country54" value="Israel"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country54UserName"/><br>
                    <label class="container">Italy -
                        <input type="checkbox" name="country55" value="Italy"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country55UserName"/><br>
                    <label class="container">Japan -
                        <input type="checkbox" name="country56" value="Japan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country56UserName"/><br>
                    <label class="container">Jordan -
                        <input type="checkbox" name="country57" value="Jordan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country57UserName"/>
                    <label class="container">Kazakhstan -
                        <input type="checkbox" name="country58" value="Kazakhstan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country58UserName"/>
                    <label class="container">Kenya -
                        <input type="checkbox" name="country59" value="Kenya"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country59UserName"/><br>
                    <label class="container">Kuwait -
                        <input type="checkbox" name="country60" value="Kuwait"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country60UserName"/><br>
                    <label class="container">Lebanon -
                        <input type="checkbox" name="country61" value="Lebanon"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country61UserName"/><br>
                    <label class="container">Libya -
                        <input type="checkbox" name="country62" value="Libya"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country62UserName"/><br>
                    <label class="container">Lithuania -
                        <input type="checkbox" name="country63" value="Lithuania"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country63UserName"/>
                    <label class="container">Malawi -
                        <input type="checkbox" name="country64" value="Malawi"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country64UserName"/><br>
                    <label class="container">Mali -
                        <input type="checkbox" name="country65" value="Mali"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country65UserName"/><br>
                    <label class="container">Mexico -
                        <input type="checkbox" name="country66" value="Mexico"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country66UserName"/><br>
                    <label class="container">Monaco -
                        <input type="checkbox" name="country67" value="Monaco"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country67UserName"/>
                    <label class="container">Myanmar -
                        <input type="checkbox" name="country68" value="Myanmar"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country68UserName"/><br>
                    <label class="container">Nepal -
                        <input type="checkbox" name="country69" value="Nepal"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country69UserName"/>
                    <label class="container">Netherlands -
                        <input type="checkbox" name="country70" value="Netherlands"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country70UserName"/>
                    <label class="container">New Zealand -
                        <input type="checkbox" name="country71" value="New Zealand"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country71UserName"/>
                    <label class="container">Nigeria -
                        <input type="checkbox" name="country72" value="Nigeria"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country72UserName"/><br>
                    <label class="container">Norway -
                        <input type="checkbox" name="country73" value="Norway"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country73UserName"/><br>
                    <label class="container">Oman -
                        <input type="checkbox" name="country74" value="Oman"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country74UserName"/><br>
                    <label class="container">Pakistan -
                        <input type="checkbox" name="country75" value="Pakistan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country75UserName"/>
                    <br>
                    <label class="container">Panama -
                        <input type="checkbox" name="country76" value="Panama"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country76UserName"/>
                    <label class="container">Paraguay -
                        <input type="checkbox" name="country77" value="Paraguay"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country77UserName"/>
                    <br>
                    <label class="container">Poland -
                        <input type="checkbox" name="country78" value="Poland"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country78UserName"/><br>
                    <label class="container">Portugal -
                        <input type="checkbox" name="country79" value="Portugal"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country79UserName"/><br>
                    <label class="container">Qatar -
                        <input type="checkbox" name="country80" value="Qatar"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country80UserName"/><br>
                    <label class="container">ROK -
                        <input type="checkbox" name="country81" value="ROK"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country81UserName"/><br>
                    <label class="container">Russia -
                        <input type="checkbox" name="country82" value="Russia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country82UserName"/>
                    <label class="container">San Marino -
                        <input type="checkbox" name="country83" value="San Marino"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country83UserName"/>
                    <label class="container">Saudi Arabia -
                        <input type="checkbox" name="country84" value="Saudi Arabia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country84UserName"/>
                    <label class="container">Senegal -
                        <input type="checkbox" name="country85" value="Senegal"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country85UserName"/><br>
                    <label class="container">Serbia -
                        <input type="checkbox" name="country86" value="Serbia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country86UserName"/><br>
                    <label class="container">Sierra Leone -
                        <input type="checkbox" name="country87" value="Sierra Leone"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country87UserName"/><br>
                    <label class="container">Singapore -
                        <input type="checkbox" name="country88" value="Singapore"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country88UserName"/><br>
                    <label class="container">Slovenia -
                        <input type="checkbox" name="country89" value="Slovenia"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country89UserName"/><br>
                    <label class="container">South Africa -
                        <input type="checkbox" name="country90" value="South Africa"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country90UserName"/><br>
                    <label class="container">Spain -
                        <input type="checkbox" name="country91" value="Spain"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country91UserName"/><br>
                    <label class="container">Sri Lanka -
                        <input type="checkbox" name="country92" value="Sri Lanka"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country92UserName"/><br>
                    <label class="container">Sudan -
                        <input type="checkbox" name="country93" value="Sudan"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country93UserName"/><br>
                    <label class="container">Sweden -
                        <input type="checkbox" name="country94" value="Sweden"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country94UserName"/><br>
                    <label class="container">Switzerland -
                        <input type="checkbox" name="country95" value="Switzerland"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country95UserName"/><br>
                    <label class="container">Syria -
                        <input type="checkbox" name="country96" value="Syria"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country96UserName"/><br>
                    <label class="container">Thailand -
                        <input type="checkbox" name="country97" value="Thailand"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country97UserName"/><br>
                    <label class="container">Togo -
                        <input type="checkbox" name="country98" value="Togo"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country98UserName"/><br>
                    <label class="container">Turkey -
                        <input type="checkbox" name="country99" value="Turkey"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country99UserName"/><br>
                    <label class="container">Uganda -
                        <input type="checkbox" name="country100" value="Uganda"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country100UserName"/><br>
                    <label class="container">Ukraine -
                        <input type="checkbox" name="country101" value="Ukraine"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country101UserName"/><br>
                    <label class="container">UAE -
                        <input type="checkbox" name="country102" value="UAE"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country102UserName"/><br>
                    <label class="container">UK -
                        <input type="checkbox" name="country103" value="UK"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country103UserName"/><br>
                    <label class="container">USA -
                        <input type="checkbox" name="country104" value="USA"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country104UserName"/><br>
                    <label class="container">Uruguay -
                        <input type="checkbox" name="country105" value="Uruguay"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country105UserName"/><br>
                    <label class="container">Venezuela -
                        <input type="checkbox" name="country106" value="Venezuela"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country106UserName"/><br>
                    <label class="container">Viet Nam -
                        <input type="checkbox" name="country107" value="Viet Nam"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country107UserName"/><br>
                    <label class="container">Yemen -
                        <input type="checkbox" name="country108" value="Yemen"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country108UserName"/><br>
                    <label class="container">Zimbabwe -
                        <input type="checkbox" name="country109" value="Zimbabwe"/>
                        <span class="checkmark"></span>
                    </label> Username:
                    <input type="text" name="country109UserName"/>
                </div>
                <br>
                <br>
                <div id="countryButton">
                    <input type="submit" value="CHOOSE COUNTRIES"/>
                </div>
                <br>
                <br>
            </form>
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
                        MESSAGE: <br><textarea rows="5" cols="10" id="messageText"></textarea>
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