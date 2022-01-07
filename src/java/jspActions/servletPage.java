/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This class links JSP pages together
 */
//Declaring package below:
package jspActions;
//Declaring imports below:
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//Declaring hidden JSP files URLs below:
@WebServlet(name = "servletPage",
        loadOnStartup = 1,
        urlPatterns = {"/signupSuccessful", "/loginSuccessful", "/ErrorPage",
            "/adminSuccessful", "/awards", "/awardsAdmin", "/chooseCountries",
            "/conferences", "/conferencesAdmin", "/inbox", "/inboxAdmin",
            "/leadership", "/leadershipAdmin", "/mockProcedures", "/mockRunnerHome",
            "/motionAnnounce", "/paperAnnounce", "/rollCall", "/teamMembers",
            "/teamMembersAdmin", "/timerPage", "/chosenCountries", "/motionVote",
            "/teamRanking", "/teamRankingConference", "/countdown", "/sendMessage",
            "/updateMember", "/deleteMember", "/updateLeader", "/registerAdmin",
            "/deleteAdmin", "/registerConference", "/updateConference",
            "/deleteConference", "/registerAward", "/updateAward", "/backToMockProcedures",
            "/goToMockProcedures", "/inputAwards", "/logOut", "/teamRankingIndividual",
            "/returnError", "/motionSummary"})
//Creating class below:
public class servletPage extends HttpServlet {
    //Declaring initializers below:
    private String causeOfError;
    private String returningPage;
    private String currentUserName;
    private final int NUMBER_OF_MOTIONS = 3;
    private final int NUMBER_OF_COUNTRIES = 110;
    //Creating constructor below:
    public servletPage() {
        this.causeOfError = "";
        this.returningPage = "";
        this.currentUserName = "";
    }
    //Creating constructor below:
    public servletPage(String causeOfError, String returningPage, String currentUserName) {
        this.causeOfError = causeOfError;
        this.returningPage = returningPage;
        this.currentUserName = currentUserName;
    }
    //Creating get and set methods below:
    public String getReturningPage() {
        return returningPage;
    }
    public void setReturningPage(String returningPage) {
        this.returningPage = returningPage;
    }
    //Creating get and set methods below:
    public String getCauseOfError() {
        return this.causeOfError;
    }
    public void setCauseOfError(String causeOfError) {
        this.causeOfError = causeOfError;
    }
    //Creating get and set methods below:
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }
    public String getCurrentUserName() {
        return this.currentUserName;
    }
    /**
     Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     methods.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     Handles the HTTP <code>GET</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    //Creating get method below:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }
    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    //Creating post method below:
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Receiving information about the requested webpage below:*/
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        checkIfExisting existing = new checkIfExisting();
        try {
            //Testing to see what JSP file the user wishes to access below:
            if (userPath.equals("/loginSuccessful")) {
                try {
                    loginPage login = new loginPage();
                    //Getting data from JSP file below:
                    String userName = request.getParameter("userName");
                    String password = request.getParameter("password");
                    //Setting information below:
                    login.setConnectionURL("jdbc:mysql://localhost:3306/");
                    login.setDbName("debateDatabase");
                    login.setUserName(userName);
                    login.setPassword(password);
                    boolean checker = login.checkLogin();
                    //Checking success below:
                    if (checker == true) {
                        String myUrl = "teamMembers";
                        this.setCurrentUserName(userName);
                        //Sending user to page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("Username or Password is Incorrect");
                        this.setReturningPage("index.html");
                        String myUrl = "ErrorPage";
                        //Sending user to page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                    //Catching exceptions below:
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Login Failed");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/updateAward")) {
                try {
                    //Getting information from JSP file below:
                    awardsDisplay updateAward = new awardsDisplay();
                    studentRanking rank = new studentRanking();
                    String userName = request.getParameter("fullNameUpdate");
                    String radioSelection = request.getParameter("awardUpdate");
                    String newCountString = request.getParameter("finalAwardUpdate");
                    //Setting information below:
                    int newCount = Integer.parseInt(newCountString);
                    if (radioSelection.equals("positionPaperUpdate")) {
                        updateAward.setAddAward("positionPaper");
                    } else if (radioSelection.equals("honorableUpdate")) {
                        updateAward.setAddAward("honorable");
                    } else if (radioSelection.equals("outstandingUpdate")) {
                        updateAward.setAddAward("outstanding");
                    } else if (radioSelection.equals("bestUpdate")) {
                        updateAward.setAddAward("best");
                    }
                    updateAward.setConnectionURL("jdbc:mysql://localhost:3306/");
                    updateAward.setDbName("debateDatabase");
                    updateAward.setUserName(userName);
                    updateAward.setNewAwardCount(newCount);
                    existing.setUserName(userName);
                    existing.setLeaderName(userName);
                    //Checking for misinformation below:
                    boolean existingUserNameCheck = existing.existingUsername();
                    boolean existingLeaderCheck = existing.existingLeader();
                    if (existingUserNameCheck == true || existingLeaderCheck == true) {
                        boolean checker = updateAward.awardUpdate();
                        checker = rank.regularAwardInput();
                        //Checking for success below:
                        if (checker == true) {
                            //Sending user to new JSP file below:
                            String myUrl = "awardsAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("Error Updating Award");
                            this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("USERNAME DOES NOT EXIST");
                        this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("Award Update Failure");
                    this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/registerAward")) {
                try {
                    //Getting information from JSP file below:
                    awardsDisplay awardUpdate = new awardsDisplay();
                    studentRanking rank = new studentRanking();
                    String userName = request.getParameter("fullName");
                    String radioSelection = request.getParameter("award");
                    //Setting information below:
                    if (radioSelection.equals("positionPaper")) {
                        awardUpdate.setAddAward("positionPaper");
                        rank.setAwardType("positionPaper");
                    } else if (radioSelection.equals("honorable")) {
                        awardUpdate.setAddAward("honorable");
                        rank.setAwardType("honorable");
                    } else if (radioSelection.equals("outstanding")) {
                        awardUpdate.setAddAward("outstanding");
                        rank.setAwardType("oustanding");
                    } else if (radioSelection.equals("best")) {
                        awardUpdate.setAddAward("best");
                        rank.setAwardType("best");
                    }
                    awardUpdate.setConnectionURL("jdbc:mysql://localhost:3306/");
                    awardUpdate.setDbName("debateDatabase");
                    awardUpdate.setUserName(userName);
                    rank.setBestUser(userName);
                    existing.setUserName(userName);
                    existing.setLeaderName(userName);
                    //Checking for misinformation below:
                    boolean existingUsernameCheck = existing.existingUsername();
                    boolean existingLeaderCheck = existing.existingLeader();
                    if (existingUsernameCheck == true) {
                        boolean checker = awardUpdate.awardRegister();
                        checker = rank.regularAwardInput();
                        //Checking for success below:
                        if (checker == true) {
                            //Sending user to new JSP file below:
                            String myUrl = "awardsAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("Error Registering Award");
                            this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (existingLeaderCheck == true) {
                        boolean checker = awardUpdate.awardRegister();
                        //Checking for success below:
                        if (checker == true) {
                            //Sending user to new JSP file below:
                            String myUrl = "awardsAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("Error Registering Leader Award");
                            this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("USERNAME DOES NOT EXIST");
                        this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Award Register Failure");
                    this.setReturningPage("/WEB-INF/awardsAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/deleteConference")) {
                try {
                    //Getting information from JSP file below:
                    conferencePage conferenceDelete = new conferencePage();
                    String conferenceName = request.getParameter("conferenceNameDelete");
                    String conferenceYearString = request.getParameter("conferenceYearDelete");
                    int conferenceYear = Integer.parseInt(conferenceYearString);
                    //Setting information below:
                    conferenceDelete.setConnectionURL("jdbc:mysql://localhost:3306/");
                    conferenceDelete.setDbName("debateDatabase");
                    conferenceDelete.setConferenceName(conferenceName);
                    conferenceDelete.setConferenceYear(conferenceYear);
                    existing.setConferenceName(conferenceName);
                    existing.setConferenceYear(conferenceYear);
                    //Checking for misinformation below:
                    boolean existingConference = existing.existingConference();
                    if (existingConference == true) {
                        boolean checker = conferenceDelete.deleteConferences();
                        //Checking for success below:
                        if (checker == true) {
                            String myUrl = "conferencesAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("Error Deleting Conference");
                            this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("CONFERENCE DOES NOT EXIST");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("Conference Deletion Failure");
                    this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/updateConference")) {
                try {
                    //Getting data from JSP files below:
                    conferencePage conferenceUpdate = new conferencePage();
                    String conferenceName = request.getParameter("conferenceNameUpdate");
                    String conferenceYearString = request.getParameter("conferenceYearUpdate");
                    int conferenceYear = Integer.parseInt(conferenceYearString);
                    String conferenceLocation = request.getParameter("conferenceLocationUpdate");
                    String numOfStudentsString = request.getParameter("numOfStudentsUpdate");
                    int locationValidity = conferenceUpdate.characterTester(conferenceLocation);
                    int studentValidity = conferenceUpdate.characterTester(numOfStudentsString);
                    if (locationValidity == 0 && studentValidity == 0) {
                        int numOfStudents = Integer.parseInt(numOfStudentsString);
                        //Setting information below:
                        conferenceUpdate.setConnectionURL("jdbc:mysql://localhost:3306/");
                        conferenceUpdate.setDbName("debateDatabase");
                        conferenceUpdate.setConferenceName(conferenceName);
                        conferenceUpdate.setConferenceLocation(conferenceLocation);
                        conferenceUpdate.setConferenceYear(conferenceYear);
                        conferenceUpdate.setNumOfStudents(numOfStudents);
                        existing.setConferenceName(conferenceName);
                        existing.setConferenceYear(conferenceYear);
                        //Checking for misinformation below:
                        boolean existingConference = existing.existingConference();
                        if (existingConference == true) {
                            boolean checker = conferenceUpdate.updateConferences();
                            if (checker == true) {
                                //Checking for success below:
                                String myUrl = "conferencesAdmin";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            } else {
                                //Setting error information below:
                                this.setCauseOfError("Error Updating Conference");
                                this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                                String myUrl = "ErrorPage";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("CONFERENCE DOES NOT EXIST");
                            this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (locationValidity == 1 || studentValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE EMPTY");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (locationValidity == 2 || studentValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("Conference Update Failure");
                    this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/registerConference")) {
                try {
                    //Getting information from JSP file below:
                    conferencePage conferenceRegister = new conferencePage();
                    String conferenceName = request.getParameter("conferenceName");
                    String conferenceYearString = request.getParameter("conferenceYear");
                    String conferenceLocation = request.getParameter("conferenceLocation");
                    String numOfStudentsString = request.getParameter("numOfStudents");
                    int nameValidity = conferenceRegister.characterTester(conferenceName);
                    int yearValidity = conferenceRegister.characterTester(conferenceYearString);
                    int locationValidity = conferenceRegister.characterTester(conferenceLocation);
                    int studentValidity = conferenceRegister.characterTester(numOfStudentsString);
                    if (nameValidity == 0 && yearValidity == 0 && locationValidity == 0 && studentValidity == 0) {
                        int conferenceYear = Integer.parseInt(conferenceYearString);
                        int numOfStudents = Integer.parseInt(numOfStudentsString);
                        //Setting information below:
                        conferenceRegister.setConnectionURL("jdbc:mysql://localhost:3306/");
                        conferenceRegister.setDbName("debateDatabase");
                        conferenceRegister.setConferenceName(conferenceName);
                        conferenceRegister.setConferenceLocation(conferenceLocation);
                        conferenceRegister.setConferenceYear(conferenceYear);
                        conferenceRegister.setNumOfStudents(numOfStudents);
                        boolean checker = conferenceRegister.addConferences();
                        //Checking for success below:
                        if (checker == true) {
                            String myUrl = "conferencesAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("CONFERENCE ALREADY EXISTS");
                            this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (nameValidity == 1 || yearValidity == 1 || locationValidity == 1 || studentValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE EMPTY");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (nameValidity == 2 || yearValidity == 2 || locationValidity == 2 || studentValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("Conference Registration Failure");
                    this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/deleteAdmin")) {
                try {
                    //Getting information from JSP file below:
                    teamMembers adminDelete = new teamMembers();
                    String userName = request.getParameter("adminNameDelete");
                    //Setting information below:
                    adminDelete.setConnectionURL("jdbc:mysql://localhost:3306/");
                    adminDelete.setDbName("debateDatabase");
                    adminDelete.setUserName(userName);
                    existing.setLeaderName(userName);
                    //Checking for misinformation below:
                    boolean existingLeader = existing.existingLeader();
                    if (existingLeader == true) {
                        boolean checker = adminDelete.deleteLeaders();
                        if (checker == true) {
                            //Checking for success below:
                            String myUrl = "leadershipAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("Error Deleting Admin");
                            this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("ADMIN DOES NOT EXIST");
                        this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Admin Deletion Failure");
                    this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/registerAdmin")) {
                try {
                    //Getting information from JSP file below:
                    teamMembers adminRegister = new teamMembers();
                    String firstName = request.getParameter("adminFirstRegister");
                    String lastName = request.getParameter("adminLastRegister");
                    String leaderPosition = request.getParameter("adminPositionRegister");
                    String userName = request.getParameter("adminNameRegister");
                    String password = request.getParameter("adminPasswordRegister");
                    int firstValidity = adminRegister.characterTester(firstName);
                    int lastValidity = adminRegister.characterTester(lastName);
                    int leaderValidity = adminRegister.characterTester(leaderPosition);
                    int userValidity = adminRegister.characterTester(userName);
                    int passwordValidity = adminRegister.characterTester(password);
                    if (firstValidity == 0 && lastValidity == 0 && leaderValidity == 0 && userValidity == 0 && passwordValidity == 0) {
                        //Setting information below:
                        adminRegister.setConnectionURL("jdbc:mysql://localhost:3306/");
                        adminRegister.setDbName("debateDatabase");
                        adminRegister.setFirstName(firstName);
                        adminRegister.setLastName(lastName);
                        adminRegister.setLeaderPosition(leaderPosition);
                        adminRegister.setUserName(userName);
                        adminRegister.setPassword(password);
                        boolean checker = adminRegister.addLeaders();
                        if (checker == true) {
                            //Checking for success below:
                            String myUrl = "leadershipAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("ADMIN NAME ALREADY EXISTS");
                            this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (firstValidity == 1 || lastValidity == 1 || leaderValidity == 1 || userValidity == 1 || passwordValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE BLANK");
                        this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (firstValidity == 2 || lastValidity == 2 || leaderValidity == 2 || userValidity == 2 || passwordValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Admin Registration Failure");
                    this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/updateMember")) {
                try {
                    //Getting information from JSP file below:
                    teamMembers updateMember = new teamMembers();
                    String updateUserName = request.getParameter("userNameUpdate");
                    String updateFirstName = request.getParameter("firstNameUpdate");
                    String updateLastName = request.getParameter("lastNameUpdate");
                    int firstValidity = updateMember.characterTester(updateUserName);
                    int lastValidity = updateMember.characterTester(updateLastName);
                    if (firstValidity == 0 && lastValidity == 0) {
                        //Setting information below:
                        updateMember.setConnectionURL("jdbc:mysql://localhost:3306/");
                        updateMember.setDbName("debateDatabase");
                        updateMember.setUserName(updateUserName);
                        updateMember.setFirstName(updateFirstName);
                        updateMember.setLastName(updateLastName);
                        existing.setUserName(updateUserName);
                        //Checking for misinformation below:
                        boolean existingCheck = existing.existingUsername();
                        if (existingCheck == true) {
                            boolean checker = updateMember.updateMembers();
                            if (checker == true) {
                                //Checking for success below:
                                String myUrl = "teamMembersAdmin";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            } else {
                                //Setting error information below:
                                this.setCauseOfError("Error Updating Member");
                                this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                                String myUrl = "ErrorPage";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("USERNAME DOES NOT EXIST");
                            this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (firstValidity == 1 || lastValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE BLANK");
                        this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (firstValidity == 2 || lastValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Member Update Failure");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/updateLeader")) {
                try {
                    //Getting information from JSP file below:
                    teamMembers leaderUpdate = new teamMembers();
                    String userName = request.getParameter("leaderNameUpdate");
                    String firstName = request.getParameter("leaderFirstUpdate");
                    String lastName = request.getParameter("leaderLastUpdate");
                    String leaderPosition = request.getParameter("leaderPositionUpdate");
                    int firstValidity = leaderUpdate.characterTester(firstName);
                    int lastValidity = leaderUpdate.characterTester(lastName);
                    int leaderValidity = leaderUpdate.characterTester(leaderPosition);
                    if (firstValidity == 0 && lastValidity == 0 && leaderValidity == 0) {
                        //Setting information below:
                        leaderUpdate.setConnectionURL("jdbc:mysql://localhost:3306/");
                        leaderUpdate.setDbName("debateDatabase");
                        leaderUpdate.setUserName(userName);
                        leaderUpdate.setFirstName(firstName);
                        leaderUpdate.setLastName(lastName);
                        leaderUpdate.setLeaderPosition(leaderPosition);
                        existing.setLeaderName(userName);
                        //Checking for misinformation below:
                        boolean existingCheck = existing.existingLeader();
                        if (existingCheck == true) {
                            boolean checker = leaderUpdate.updateLeaders();
                            if (checker == true) {
                                //Checking for success below:
                                String myUrl = "leadershipAdmin";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            } else {
                                //Setting error information below:
                                this.setCauseOfError("Error Updating Leader");
                                this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                                String myUrl = "ErrorPage";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                } catch (IOException | ServletException ed) {
                                    ed.printStackTrace();
                                }
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("ADMIN NAME DOES NOT EXIST");
                            this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (firstValidity == 1 || lastValidity == 1 || leaderValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS EMPTY");
                        this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (firstValidity == 2 || lastValidity == 2 || leaderValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Leader Update Failure");
                    this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/deleteMember")) {
                try {
                    try {
                        //Getting information from JSP file below:
                        teamMembers deleteMember = new teamMembers();
                        String userName = request.getParameter("userNameDelete");
                        //Setting information below:
                        deleteMember.setConnectionURL("jdbc:mysql://localhost:3306/");
                        deleteMember.setDbName("debateDatabase");
                        deleteMember.setUserName(userName);
                        existing.setUserName(userName);
                        //Checking for misinformation below:
                        boolean existingCheck = existing.existingUsername();
                        if (existingCheck == true) {
                            boolean checker = deleteMember.deleteMembers();
                            String myUrl = "teamMembersAdmin";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("USERNAME DOES NOT EXIST");
                            this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } catch (Exception ex) {
                        //Setting error information below:
                        this.setCauseOfError("Error Deleting Member");
                        this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("Member Deletion Failure");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/signupSuccessful")) {
                try {
                    //Getting information from JSP file below:
                    loginPage signUp = new loginPage();
                    String newUserName = request.getParameter("createUserName");
                    String newPassword = request.getParameter("createPassword");
                    String newFirstName = request.getParameter("createFirst");
                    String newLastName = request.getParameter("createLast");
                    int yearsOfExperience = Integer.parseInt(request.getParameter("createYears"));
                    int userValidity = signUp.characterTester(newUserName);
                    int passwordValidity = signUp.characterTester(newPassword);
                    int firstValidity = signUp.characterTester(newFirstName);
                    int lastValidity = signUp.characterTester(newLastName);
                    if (userValidity == 0 && passwordValidity == 0 && firstValidity == 0 && lastValidity == 0) {
                        //Setting information below:
                        signUp.setConnectionURL("jdbc:mysql://localhost:3306/");
                        signUp.setDbName("debateDatabase");
                        signUp.setUserName(newUserName);
                        signUp.setPassword(newPassword);
                        signUp.setFirstName(newFirstName);
                        signUp.setLastName(newLastName);
                        signUp.setYearsOfExperience(yearsOfExperience);
                        boolean checker = signUp.checkSignUp();
                        //Checking for success below:
                        if (checker == true) {
                            String myUrl = "teamMembers";
                            this.setCurrentUserName(newUserName);
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("USERNAME ALREADY EXISTS");
                            this.setReturningPage("index.html");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (userValidity == 1 || passwordValidity == 1 || firstValidity == 1 || lastValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS LEFT BLANK");
                        this.setReturningPage("index.html");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (userValidity == 2 || passwordValidity == 2 || firstValidity == 2 || lastValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("index.html");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("Sign Up Failure");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/adminSuccessful")) {
                try {
                    //Getting information from JSP file below:
                    loginPage adminLogin = new loginPage();
                    String adminName = request.getParameter("adminUserName");
                    String adminPassword = request.getParameter("adminPassword");
                    //Setting information below:
                    adminLogin.setConnectionURL("jdbc:mysql://localhost:3306/");
                    adminLogin.setDbName("debateDatabase");
                    adminLogin.setUserName(adminName);
                    adminLogin.setPassword(adminPassword);
                    boolean checker = adminLogin.checkAdminLogin();
                    //Checking for success below:
                    if (checker == true) {
                        String myUrl = "teamMembersAdmin";
                        this.setCurrentUserName(adminName);
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("ERROR LOGGING IN ADMIN");
                        this.setReturningPage("index.html");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ADMIN LOGIN FAILURE");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/awards")) {
                try {
                    //Displaying information from the database below:
                    awardsDisplay awardsDatabase = new awardsDisplay();
                    awardsDatabase.setDbName("debateDatabase");
                    awardsDatabase.setTableName("awards");
                    ArrayList<ArrayList<String>> members = awardsDatabase.allAwardsDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO AWARDS");
                    this.setReturningPage("/WEB-INF/teamMembers.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/awardsAdmin")) {
                try {
                    //Displaying information from the database below:
                    awardsDisplay awardsDatabase = new awardsDisplay();
                    awardsDatabase.setDbName("debateDatabase");
                    awardsDatabase.setTableName("awards");
                    ArrayList<ArrayList<String>> members = awardsDatabase.allAwardsDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO AWARDS ADMIN");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/chooseCountries")) {
                try {
                    rollCall countrySelection = new rollCall();
                    boolean status = countrySelection.clearDatabase();
                    if (status == true) {
                        //Travelling to another JSP page below:
                        String myUrl = "/WEB-INF" + userPath + ".jsp";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("ERROR GOING TO CHOOSE COUNTRIES");
                        this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO CHOOSE COUNTRIES");
                    this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/conferences")) {
                try {
                    //Displaying information from the database below:
                    conferencePage conferencesDatabase = new conferencePage();
                    conferencesDatabase.setDbName("debateDatabase");
                    conferencesDatabase.setTableName("conferences");
                    ArrayList<ArrayList<String>> members = conferencesDatabase.allConferencesDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO CONFERENCES");
                    this.setReturningPage("/WEB-INF/teamMembers.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/conferencesAdmin")) {
                try {
                    //Displaying information from the database below:
                    conferencePage conferencesDatabase = new conferencePage();
                    conferencesDatabase.setDbName("debateDatabase");
                    conferencesDatabase.setTableName("conferences");
                    ArrayList<ArrayList<String>> members = conferencesDatabase.allConferencesDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO CONFERENCES ADMIN");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/inbox")) {
                try {
                    //Displaying information from the database below:
                    messagingSystem messagesDatabase = new messagingSystem();
                    messagesDatabase.setDbName("debateDatabase");
                    messagesDatabase.setTableName("messagingSystem");
                    String currentUser = this.getCurrentUserName();
                    ArrayList<ArrayList<String>> members = messagesDatabase.specificMessagesDisplay(currentUser);
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO INBOX");
                    this.setReturningPage("/WEB-INF/teamMembers.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/inboxAdmin")) {
                try {
                    //Displaying information from the database below:
                    messagingSystem messagesDatabase = new messagingSystem();
                    messagesDatabase.setDbName("debateDatabase");
                    messagesDatabase.setTableName("messagingSystem");
                    ArrayList<ArrayList<String>> members = messagesDatabase.allMessagesDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO INBOX ADMIN");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/leadership")) {
                try {
                    //Displaying information from the database below:
                    teamMembers leadershipDatabase = new teamMembers();
                    leadershipDatabase.setDbName("debateDatabase");
                    leadershipDatabase.setTableName("leadershipTeam");
                    ArrayList<ArrayList<String>> members = leadershipDatabase.allLeadersDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO LEADERSHIP");
                    this.setReturningPage("/WEB-INF/teamMembers.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/leadershipAdmin")) {
                try {
                    //Displaying information from the database below:
                    teamMembers leadershipDatabase = new teamMembers();
                    leadershipDatabase.setDbName("debateDatabase");
                    leadershipDatabase.setTableName("leadershipTeam");
                    ArrayList<ArrayList<String>> members = leadershipDatabase.allLeadersDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO LEADERSHIP ADMIN");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/mockProcedures")) {
                try {
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO MOCK PROCEDURES");
                    this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/mockRunnerHome")) {
                try {
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO MOCK RUNNER");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/motionAnnounce")) {
                try {
                    //Craeting arrays below:
                    String[] motions = new String[3];
                    int[] votes = new int[3];
                    int[] speaking = new int[3];
                    String[] country = new String[3];
                    int[] totalTime = new int[3];
                    for (int i = 0; i < NUMBER_OF_MOTIONS; i++) {
                        int counter = i;
                        counter++;
                        //Defining new statement below:
                        String counterString = Integer.toString(counter);
                        String motionStatement = "motionPropose" + counterString;
                        String voteStatement = "votePropose" + counterString;
                        String speakingStatement = "speakingPropose" + counterString;
                        String countryStatement = "countryPropose" + counterString;
                        String totalTimeStatement = "timePropose" + counterString;
                        //Getting information from JSP file below:
                        motions[i] = request.getParameter(motionStatement);
                        votes[i] = Integer.parseInt(request.getParameter(voteStatement));
                        speaking[i] = Integer.parseInt(request.getParameter(speakingStatement));
                        country[i] = request.getParameter(countryStatement);
                        totalTime[i] = Integer.parseInt(request.getParameter(totalTimeStatement));
                    }
                    //Setting information below:
                    motionRecord motionsRecord = new motionRecord();
                    int motionOneValidity = motionsRecord.characterTester(motions[0]);
                    int motionTwoValidity = motionsRecord.characterTester(motions[1]);
                    int motionThreeValidity = motionsRecord.characterTester(motions[2]);
                    int countryOneValidity = motionsRecord.characterTester(country[0]);
                    int countryTwoValidity = motionsRecord.characterTester(country[1]);
                    int countryThreeValidity = motionsRecord.characterTester(country[2]);
                    if (motionOneValidity == 0 && motionTwoValidity == 0 && motionThreeValidity == 0 && countryOneValidity == 0 && countryTwoValidity == 0 && countryThreeValidity == 0) {
                        motionsRecord.setMotion(motions);
                        motionsRecord.setVotes(votes);
                        motionsRecord.setSpeakingTime(speaking);
                        motionsRecord.setCountry(country);
                        motionsRecord.setTotalTime(totalTime);
                        String[] motionDetails = motionsRecord.verifyMotion();
                        //Checking for error below:
                        if (!motionDetails[0].equals("error")) {
                            if (motionDetails[0].equals("SPEAKER'S LIST")) {
                                session.setAttribute("motionText", motionDetails[0]);
                                session.setAttribute("timeText", "N/A");
                                session.setAttribute("speakText", "N/A");
                                session.setAttribute("countryText", "N/A");
                                String myUrl = "/WEB-INF" + userPath + ".jsp";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                    //Catching exceptions below:
                                } catch (IOException | ServletException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                //Populating JSP page below:
                                session.setAttribute("motionText", motionDetails[0]);
                                session.setAttribute("timeText", motionDetails[1]);
                                session.setAttribute("speakText", motionDetails[2]);
                                session.setAttribute("countryText", motionDetails[3]);
                                String myUrl = "/WEB-INF" + userPath + ".jsp";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                    //Catching exceptions below:
                                } catch (IOException | ServletException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("ERROR ANNOUNCING MOTION");
                            this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (motionOneValidity == 1 || motionTwoValidity == 1 || motionThreeValidity == 1 || countryOneValidity == 1 || countryTwoValidity == 1 || countryThreeValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE EMPTY");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    } else if (motionOneValidity == 2 || motionTwoValidity == 2 || motionThreeValidity == 2 || countryOneValidity == 2 || countryTwoValidity == 2 || countryThreeValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO MOTION ANNOUNCE");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/paperAnnounce")) {
                try {
                    //Getting information from JSP page below:
                    String paperName = request.getParameter("paperName");
                    String paperVotesString = request.getParameter("paperVotes");
                    int paperVotes = Integer.parseInt(paperVotesString);
                    int[] paperVoting = new int[1];
                    paperVoting[0] = paperVotes;
                    motionRecord papers = new motionRecord();
                    int nameValidity = papers.characterTester(paperName);
                    if (nameValidity == 0) {
                        //Setting information below:
                        papers.setVotes(paperVoting);
                        boolean passing = papers.verifyPaper();
                        if (passing == true) {
                            //Populating JSP page below:
                            session.setAttribute("paperName", paperName);
                            session.setAttribute("paperStatus", "PASSED");
                            String myUrl = "/WEB-INF" + userPath + ".jsp";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Populating JSP page below:
                            session.setAttribute("paperName", paperName);
                            session.setAttribute("paperStatus", "FAILED");
                            String myUrl = "/WEB-INF" + userPath + ".jsp";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (nameValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("TEXT FIELDS ARE EMPTY");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (nameValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO PAPER ANNOUNCE");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/rollCall")) {
                try {
                    //Setting information below:
                    rollCall displayCountry = new rollCall();
                    displayCountry.setDbName("debateDatabase");
                    displayCountry.setTableName("countryMatrix");
                    ArrayList<ArrayList<String>> members = displayCountry.allCountryDisplay();
                    if (members.isEmpty()) {
                        //Setting error information below:
                        this.setCauseOfError("NO COUNTRIES HAVE BEEN SELECTED");
                        this.setReturningPage("/WEB-INF/chooseCountries.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else {
                        session.setAttribute("dbData", members);
                        String myUrl = "/WEB-INF" + userPath + ".jsp";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO ROLL CALL");
                    this.setReturningPage("/WEB-INF/chooseCountries.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/teamMembers")) {
                try {
                    //Displaying information from the database below:
                    teamMembers memberDatabase = new teamMembers();
                    memberDatabase.setDbName("debateDatabase");
                    memberDatabase.setTableName("teamMembers");
                    ArrayList<ArrayList<String>> members = memberDatabase.allMembersDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO TEAM MEMBERS");
                    this.setReturningPage("/WEB-INF/leadership.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/teamMembersAdmin")) {
                try {
                    //Displaying information from the database below:
                    teamMembers memberDatabase = new teamMembers();
                    memberDatabase.setDbName("debateDatabase");
                    memberDatabase.setTableName("teamMembers");
                    ArrayList<ArrayList<String>> members = memberDatabase.allMembersDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO TEAM MEMBERS ADMIN");
                    this.setReturningPage("/WEB-INF/leadershipAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/timerPage")) {
                try {
                    //Travelling to another JSP page below:
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO TIMER");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/chosenCountries")) {
                try {
                    //Setting information below:
                    String myUrl = "rollCall";
                    rollCall countrySelection = new rollCall();
                    boolean status = countrySelection.clearDatabase();
                    countrySelection.setConnectionURL("jdbc:mysql://localhost:3306/");
                    countrySelection.setDbName("debateDatabase");
                    boolean countrySelected = false;
                    //Getting information from JSP file below:
                    for (int i = 1; i < NUMBER_OF_COUNTRIES; i++) {
                        String increment = Integer.toString(i);
                        String countryCheck = "country" + increment;
                        if (request.getParameter(countryCheck) != null) {
                            countrySelected = true;
                            //Setting information below:
                            String countryUserName = countryCheck + "UserName";
                            countrySelection.setCountryName(request.getParameter(countryCheck));
                            countrySelection.setUserName(request.getParameter(countryUserName));
                            existing.setUserName(request.getParameter(countryUserName));
                            existing.setLeaderName(request.getParameter(countryUserName));
                            //Checking for misinformation below:
                            boolean existingUserNameCheck = existing.existingUsername();
                            boolean existingLeaderNameCheck = existing.existingLeader();
                            if (existingUserNameCheck == true || existingLeaderNameCheck == true) {
                                status = countrySelection.addNewCountry();
                                //Checking for success below:
                                if (status == false) {
                                    //Setting error information below:
                                    this.setCauseOfError("ERROR GOING TO COUNTRY CHOOSING");
                                    this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                                    myUrl = "ErrorPage";
                                }
                            } else {
                                //Setting error information below:
                                this.setCauseOfError("CERTAIN USERNAMES DO NOT EXIST");
                                this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                                myUrl = "ErrorPage";
                            }
                        }
                    }
                    if (countrySelected == false) {
                        //Setting error information below:
                        this.setCauseOfError("NO COUNTRY SELECTED");
                        this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                        myUrl = "ErrorPage";
                    }
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO CHOSEN COUNTRIES");
                    this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/teamRankingIndividual")) {
                try {
                    //Displaying information from the database below:
                    studentRanking rankingDatabase = new studentRanking();
                    rankingDatabase.setDbName("debateDatabase");
                    rankingDatabase.setTableName("userRanking");
                    ArrayList<ArrayList<String>> members = rankingDatabase.allRankingsDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "teamRanking";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO TEAM RANKING");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/teamRankingConference")) {
                try {
                    //Getting information from JSP file below:
                    conferencePage rankTeam = new conferencePage();
                    //Setting information below:
                    rankTeam.setDbName("debateDatabase");
                    rankTeam.setTableName("userRanking");
                    rankTeam.setConferenceName(request.getParameter("conferenceNameRank"));
                    rankTeam.setConferenceYear(Integer.parseInt(request.getParameter("conferenceYearRank")));
                    existing.setConferenceName(request.getParameter("conferenceNameRank"));
                    existing.setConferenceYear(Integer.parseInt(request.getParameter("conferenceYearRank")));
                    boolean existingConferenceCheck = existing.existingConference();
                    //Checking for misinformation below:
                    if (existingConferenceCheck == true) {
                        ArrayList<ArrayList<String>> members = rankTeam.rankConferences();
                        session.setAttribute("dbData", members);
                        String myUrl = "teamRanking";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("CONFERENCE DOES NOT EXIST");
                        this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (NumberFormatException ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO TEAM RANKING FOR CONFERENCE");
                    this.setReturningPage("/WEB-INF/conferencesAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/countdown")) {
                try {
                    String myUrl = "timerPage";
                    //Travelling to another JSP page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO COUNTDOWN");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/ErrorPage")) {
                try {
                    //Setting error information below:
                    session.setAttribute("errorReason", this.getCauseOfError());
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("something's really messed up...");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/sendMessage")) {
                try {
                    //Getting information from JSP file below:
                    String userNameCurrent = this.getCurrentUserName();
                    messagingSystem sendMessage = new messagingSystem();
                    String recipient = request.getParameter("userNameSend");
                    String message = request.getParameter("message");
                    int messageValidity = sendMessage.characterTester(message);
                    if (messageValidity == 0) {
                        //Setting information below:
                        sendMessage.setConnectionURL("jdbc:mysql://localhost:3306/");
                        sendMessage.setDbName("debateDatabase");
                        sendMessage.setRecipient(recipient);
                        sendMessage.setMessage(message);
                        sendMessage.setSender(userNameCurrent);
                        existing.setUserName(recipient);
                        //Checking for misinformation below:
                        boolean existingCheck = existing.existingUsername();
                        if (existingCheck == true) {
                            boolean checker = sendMessage.sendMessages();
                            //Checking for success below:
                            if (checker == true) {
                                String myUrl = "inboxAdmin";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                    //Catching exceptions below:
                                } catch (IOException | ServletException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                //Goes here on reload after sending message
                                String myUrl = "teamMembersAdmin";
                                try {
                                    request.getRequestDispatcher(myUrl).forward(request, response);
                                    //Catching exceptions below:
                                } catch (IOException | ServletException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("RECIPIENT DOES NOT EXIST");
                            this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                            String myUrl = "ErrorPage";
                            //Sending user to error page below:
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                            } catch (IOException | ServletException ed) {
                                ed.printStackTrace();
                            }
                        }
                    } else if (messageValidity == 1) {
                        //Setting error information below:
                        this.setCauseOfError("MESSAGE FIELD EMPTY");
                        this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    } else if (messageValidity == 2) {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN CHARACTERS NOT ACCEPTED");
                        this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                        String myUrl = "ErrorPage";
                        //Sending user to error page below:
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                        } catch (IOException | ServletException ed) {
                            ed.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR SENDING MESSAGE");
                    this.setReturningPage("/WEB-INF/mockRunnerHome.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/backToMockProcedures")) {
                try {
                    //Travelling to another JSP page below:
                    String myUrl = "/WEB-INF" + "/mockProcedures" + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING BACK TO PROCEDURES");
                    this.setReturningPage("/WEB-INF/timerPage.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/goToMockProcedures")) {
                try {
                    //Setting information below:
                    boolean status = true;
                    rollCall country = new rollCall();
                    studentRanking rank = new studentRanking();
                    country.setConnectionURL("jdbc:mysql://localhost:3306/");
                    country.setDbName("debateDatabase");
                    ArrayList<String> countries = new ArrayList<>();
                    countries = country.returnCountryName();
                    //Converting arraylist below:
                    String[] countryNames = new String[countries.size()];
                    for (int i = 0; i < countryNames.length; i++) {
                        countryNames[i] = countries.get(i);
                    }
                    //Getting information from JSP file below:
                    for (int i = 0; i < countryNames.length; i++) {
                        if (request.getParameter(countryNames[i]) != null) {
                            //Setting information below:
                            country.setCountryName(countryNames[i]);
                            existing.setCountryName(countryNames[i]);
                            //Checking if user is a leader below:
                            boolean isLeader = existing.leaderAbsent();
                            if (request.getParameter(countryNames[i]).equals("present")) {
                                country.setNewStatus("present");
                                status = country.updateStatus();
                            } else if (request.getParameter(countryNames[i]).equals("presentAndVoting")) {
                                country.setNewStatus("voting");
                                status = country.updateStatus();
                            } else {
                                //Preventing leaders from lower ranks below:
                                if (isLeader == false) {
                                    country.setNewStatus("absent");
                                    status = country.updateStatus();
                                }
                            }
                        } else {
                            status = false;
                        }
                    }
                    //Checking for misinformation below:
                    boolean verify = rank.verifyRanking();
                    //Checking for success below:
                    if (status == true && verify == true) {
                        String myUrl = "mockProcedures";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("ERROR GOING TO PROCEDURES");
                        this.setReturningPage("/WEB-INF/chooseCountries.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO MOCK PROCEDURES");
                    this.setReturningPage("/WEB-INF/rollCall.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/inputAwards")) {
                try {
                    //Getting information from JSP file below:
                    boolean status = true;
                    studentRanking rank = new studentRanking();
                    rank.setConnectionURL("jdbc:mysql://localhost:3306/");
                    rank.setDbName("debateDatabase");
                    String bestUser = request.getParameter("best");
                    String outstandingUser = request.getParameter("outstanding");
                    String honorableUser = request.getParameter("honor");
                    //Setting information below:
                    rank.setBestUser(bestUser);
                    rank.setOutstandingUser(outstandingUser);
                    rank.setHonorableUser(honorableUser);
                    status = rank.convertToUserNames();
                    existing.setUserName(rank.getBestUser());
                    existing.setLeaderName(rank.getBestUser());
                    //Checking for misinformation below:
                    boolean bestUserCheck = existing.existingUsername();
                    boolean bestLeaderCheck = existing.existingLeader();
                    existing.setUserName(rank.getOutstandingUser());
                    existing.setLeaderName(rank.getOutstandingUser());
                    //Checking for misinformation below:
                    boolean outstandingUserCheck = existing.existingUsername();
                    boolean outstandingLeaderCheck = existing.existingLeader();
                    existing.setUserName(rank.getHonorableUser());
                    existing.setLeaderName(rank.getHonorableUser());
                    //Checking for misinformation below:
                    boolean honorableUserCheck = existing.existingUsername();
                    boolean honorableLeaderCheck = existing.existingLeader();
                    //Checking if all users are not leaders below:
                    if (bestUserCheck == true && outstandingUserCheck == true && honorableUserCheck == true) {
                        //Inputting awards below:
                        status = rank.mockAwardInput();
                        if (status == true) {
                            String myUrl = "motionSummary";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Setting error information below:
                            this.setCauseOfError("ERROR INPUTTING AWARDS");
                            this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                            String myUrl = "ErrorPage";
                            try {
                                request.getRequestDispatcher(myUrl).forward(request, response);
                                //Catching exceptions below:
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (bestLeaderCheck == true || outstandingLeaderCheck == true || honorableLeaderCheck == true) {
                        //Setting error information below:
                        this.setCauseOfError("ADMINS CANNOT WIN AWARDS");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Setting error information below:
                        this.setCauseOfError("CERTAIN USERNAMES DO NOT EXIST");
                        this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                        String myUrl = "ErrorPage";
                        try {
                            request.getRequestDispatcher(myUrl).forward(request, response);
                            //Catching exceptions below:
                        } catch (IOException | ServletException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR ENDING MOCK");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/logOut")) {
                try {
                    String myUrl = "index.html";
                    //Travelling to another JSP page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR LOGGING OUT");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/teamRanking")) {
                try {
                    //Travelling to another JSP page below:
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO RANKING");
                    this.setReturningPage("/WEB-INF/teamMembersAdmin.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/returnError")) {
                try {
                    //Travelling to another JSP page below:
                    String myUrl = this.returningPage;
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING BACK");
                    this.setReturningPage("index.html");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            } else if (userPath.equals("/motionSummary")) {
                try {
                    //Displaying information from the database below:
                    motionRecord motionDatabase = new motionRecord();
                    motionDatabase.setDbName("debateDatabase");
                    motionDatabase.setTableName("passedMotions");
                    ArrayList<ArrayList<String>> members = motionDatabase.allMotionsDisplay();
                    session.setAttribute("dbData", members);
                    String myUrl = "/WEB-INF" + userPath + ".jsp";
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                        //Catching exceptions below:
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    //Setting error information below:
                    this.setCauseOfError("ERROR GOING TO MOTION SUMMARY");
                    this.setReturningPage("/WEB-INF/mockProcedures.jsp");
                    String myUrl = "ErrorPage";
                    //Sending user to error page below:
                    try {
                        request.getRequestDispatcher(myUrl).forward(request, response);
                    } catch (IOException | ServletException ed) {
                        ed.printStackTrace();
                    }
                }
            }
            //processRequest(request, response);
        } catch (Exception er) {
            String myUrl = "ErrorPage";
            //Travelling to another JSP page below:
            try {
                request.getRequestDispatcher(myUrl).forward(request, response);
                //Catching exceptions below:
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
