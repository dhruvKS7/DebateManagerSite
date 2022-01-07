/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with login
*/
//Declaring package below:
package jspActions;
//Imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Creating class below:
public class loginPage {
    //Declaring initializers below:
    private String dbName;
    private String connectionURL;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int yearsOfExperience;
    //Creating constructor below:
    public loginPage() {
        this.dbName = "";
        this.connectionURL = "";
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.yearsOfExperience = 0;
    }
    //Creating constructor below:
    public loginPage(String dbName, String connectionURL, String userName, String password, String firstName, String lastName, int yearsOfExperience) {
        this.dbName = dbName;
        this.connectionURL = connectionURL;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearsOfExperience = yearsOfExperience;
    }
    //Creating get and set methods below:
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    //Creating get and set methods below:
    public String getConnectionURL() {
        return connectionURL;
    }
    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    //Creating get and set methods below:
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //Creating get and set methods below:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Creating get and set methods below:
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //Creating get and set methods below:
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Creating get and set methods below:
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    //Creating method below:
    public boolean checkLogin() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String loginChecker = "SELECT * FROM userLogin WHERE userName = ? and password = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement loginCheck = con.prepareStatement(loginChecker);
            //Replacing ? with values below:
            loginCheck.setString(1, this.userName);
            loginCheck.setString(2, this.password);
            //Receiving information from select statement below:
            ResultSet rs = loginCheck.executeQuery();
            status = rs.next();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean checkSignUp() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String signUpChecker = "INSERT INTO userLogin VALUES (?, ?)";
        String membersNameEntry = "INSERT INTO teamMembers VALUES (?, ?, ?)";
        String rankingEntry = "INSERT INTO userRanking VALUES (?, ?, ?, ?)";
        String awardEntryStatement = "INSERT INTO awards VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean status = true;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement signUpCheck = con.prepareStatement(signUpChecker);
            //Replacing ? with values below:
            signUpCheck.setString(1, this.userName);
            signUpCheck.setString(2, this.password);
            //Executing statement below:
            signUpCheck.executeUpdate();
            //Executing insert statement to insert info into the database below:
            PreparedStatement nameEntry = con.prepareStatement(membersNameEntry);
            //Replacing ? with values below:
            nameEntry.setString(1, this.userName);
            nameEntry.setString(2, this.firstName);
            nameEntry.setString(3, this.lastName);
            //Executing statement below:
            nameEntry.executeUpdate();
            //Executing insert statement to insert info into the database below:
            PreparedStatement rankEntry = con.prepareStatement(rankingEntry);
            //Replacing ? with values below:
            rankEntry.setString(1, this.userName);
            rankEntry.setString(2, this.firstName);
            rankEntry.setString(3, this.lastName);
            //Determining initial rank based on years of experience below:
            if (this.yearsOfExperience == 0) {
                rankEntry.setDouble(4, 6.0);
            } else if (this.yearsOfExperience == 1 || this.yearsOfExperience == 2) {
                rankEntry.setDouble(4, 6.5);
            } else if (this.yearsOfExperience == 3 || this.yearsOfExperience == 4) {
                rankEntry.setDouble(4, 7.0);
            } else if (this.yearsOfExperience > 4) {
                rankEntry.setDouble(4, 7.5);
            } else {
                status = false;
            }
            //Executing statement below:
            rankEntry.executeUpdate();
            //Executing insert statement to insert info into the database below:
            PreparedStatement awardEntry = con.prepareStatement(awardEntryStatement);
            //Replacing ? with values below:
            awardEntry.setString(1, this.userName);
            awardEntry.setString(2, this.firstName);
            awardEntry.setString(3, this.lastName);
            awardEntry.setInt(4, 0);
            awardEntry.setInt(5, 0);
            awardEntry.setInt(6, 0);
            awardEntry.setInt(7, 0);
            //Executing statement below:
            awardEntry.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean checkAdminLogin() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String loginChecker = "SELECT * FROM adminLogin WHERE adminName = ? and password = ?";
        boolean status;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement loginCheck = con.prepareStatement(loginChecker);
            //Replacing ? with values below:
            loginCheck.setString(1, this.userName);
            loginCheck.setString(2, this.password);
            //Receiving information from select statement below:
            ResultSet rs = loginCheck.executeQuery();
            status = rs.next();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    
    public int characterTester(String message) {
        int status = 0;
        int entryLength = message.length();
        if (entryLength == 0) {
            status = 1;
        } else {
            for (int i=0; i<entryLength; i++) {
                if (!isAccepted(message.charAt(i))) {
                    status = 2;
                    break;
                }
            }
        }
        return status;
    }
    
    public boolean isAccepted(char character) {
        return (character>= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || (character >= '0' && character <= '9') ||
               (character == '@') || (character == '.') || (character == '_') || (character == '-') || (character == '$') ||
               (character == '!') || (character == '#') || (character == '%') || (character == '^') || (character == '&') ||
               (character == '*') || (character == '(') || (character == (')') || (character == '/') || (character == '?') ||
               (character == ',') || (character == '+') || (character == '=') || (character == ';') || (character == ':') ||
               (character == '"') || (character == '<') || (character == '>') || (character == '{') || (character == '}') ||
               (character == '[') || (character == ']') || (character == ' ') || (character == 39));
    }
    
    //Creating main method below:
//    public static void main(String[] args) {
//        loginPage testObj = new loginPage("debateDatabase", "jdbc:mysql://localhost:3306/debateDatabase", "rika", "test", "Ritika", "Saligram", 5);
//        boolean status = testObj.checkLogin();
//        System.out.println(status);
//        status = testObj.checkAdminLogin();
//        System.out.println(status);
//        status = testObj.checkSignUp();
//        System.out.println(status);
//    }
}