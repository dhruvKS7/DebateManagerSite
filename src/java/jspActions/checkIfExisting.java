/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page checks if members exist in the databse when the user tries to change information
*/
//Declaring package below:
package jspActions;
//Imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
//Creating class below:
public class checkIfExisting {
    //Defining instance variables below:
    private String connectionURL;
    private String dbName;
    private String userName;
    private String leaderName;
    private String conferenceName;
    private int conferenceYear;
    private String countryName;
    //Creating constructor below:
    public checkIfExisting() {
        this.connectionURL = "";
        this.dbName = "";
        this.userName = "";
        this.leaderName = "";
        this.conferenceName = "";
        this.conferenceYear = 0;
        this.countryName = "";
    }
    //Creating constructor below:
    public checkIfExisting(String connectionURL, String dbName, String userName, String leaderName, String conferenceName, int conferenceYear, String countryName) {
        this.connectionURL = connectionURL;
        this.dbName = dbName;
        this.userName = userName;
        this.leaderName = leaderName;
        this.conferenceName = conferenceName;
        this.conferenceYear = conferenceYear;
        this.countryName = countryName;
    }
    //Creating get and set methods below:
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    //Creating get and set methods below:
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //Creating get and set methods below:
    public String getLeaderName() {
        return leaderName;
    }
    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
    //Creating get and set methods below:
    public String getConferenceName() {
        return conferenceName;
    }
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
    //Creating get and set methods below:
    public int getConferenceYear() {
        return conferenceYear;
    }
    public void setConferenceYear(int conferenceYear) {
        this.conferenceYear = conferenceYear;
    }
    //Creating get and set methods below:
    public String getConnectionURL() {
        return connectionURL;
    }
    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    //Creating get and set methods below:
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    //Creating method below:
    public boolean existingUsername() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String usernameChecker = "SELECT * FROM userLogin WHERE userName = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement usernameCheck = con.prepareStatement(usernameChecker);
            //Replacing ? with values below:
            usernameCheck.setString(1, this.userName);
            //Receiving information from select statement below:
            ResultSet rs = usernameCheck.executeQuery();
            status = rs.next();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean existingLeader() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String leaderChecker = "SELECT * FROM adminLogin WHERE adminName = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement leaderCheck = con.prepareStatement(leaderChecker);
            //Replacing ? with values below:
            leaderCheck.setString(1, this.leaderName);
            //Receiving information from select statement below:
            ResultSet rs = leaderCheck.executeQuery();
            status = rs.next();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean existingConference() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String conferenceChecker = "SELECT * FROM conferences WHERE conferenceName = ? AND conferenceYear = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement conferenceCheck = con.prepareStatement(conferenceChecker);
            //Replacing ? with values below:
            conferenceCheck.setString(1, this.conferenceName);
            conferenceCheck.setInt(2, this.conferenceYear);
            //Receiving information from select statement below:
            ResultSet rs = conferenceCheck.executeQuery();
            status = rs.next();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean leaderAbsent() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String conferenceChecker = "SELECT userName FROM countryMatrix WHERE countryName = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement conferenceCheck = con.prepareStatement(conferenceChecker);
            //Replacing ? with values below:
            conferenceCheck.setString(1, this.countryName);
            //Receiving information from select statement below:
            ResultSet userConference = conferenceCheck.executeQuery();
            ResultSetMetaData conferenceTableInfo = conferenceCheck.getMetaData();
            //Getting the column count of the database below:
            int element = conferenceTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            ArrayList<String> newConferenceList = new ArrayList<>();
            while (userConference.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    newConferenceList.add(userConference.getString(z));
                }
            }
            //Converting arraylist to array below:
            String[] userNameSelect = new String[newConferenceList.size()];
            for (int i = 0; i < userNameSelect.length; i++) {
                userNameSelect[i] = newConferenceList.get(i);
            }
            this.setLeaderName(userNameSelect[0]);
            status = this.existingLeader();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating main method below:
//    public static void main(String[] args) {
//        checkIfExisting testObj = new checkIfExisting("jdbc:mysql://localhost:3306/debateDatabase", "debateDatabase", "rika", "dhruv", "CTMUN Fall", 2020, "Albania");
//        boolean status = testObj.existingUsername();
//        System.out.println(status);
//        status = testObj.existingLeader();
//        System.out.println(status);
//        status = testObj.existingConference();
//        System.out.println(status);
//        status = testObj.leaderAbsent();
//        System.out.println(status);
//    }
}