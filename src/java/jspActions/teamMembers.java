/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with team members
*/
//Package below:
package jspActions;
//Imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
//Creating class below:
public class teamMembers {
    //Declaring initializers below:
    private String dbName;
    private String tableName;
    private String connectionURL;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String leaderPosition;
    //Creating constructor below:
    public teamMembers() {
        this.dbName = "";
        this.tableName = "";
        this.connectionURL = "";
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.leaderPosition = "";
    }
    //Creating constructor below:
    public teamMembers(String dbName, String tableName, String connectionURL, String userName, String password, String firstName, String lastName, String leaderPosition) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.connectionURL = connectionURL;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaderPosition = leaderPosition;
    }
    //Creating get and set methods below:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Creating get and set methods below:
    public String getLeaderPosition() {
        return leaderPosition;
    }
    public void setLeaderPosition(String leaderPosition) {
        this.leaderPosition = leaderPosition;
    }
    //Creating get and set methods below:
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    //Creating get and set methods below:
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> allMembersDisplay() {
        String[] tableHeaders = {"userName", "firstName", "lastName"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> allLeadersDisplay() {
        String[] tableHeaders = {"userName", "firstName", "lastName", "leaderPosition"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating method below:
    public boolean updateMembers() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String memberUpdate = "UPDATE teamMembers SET firstName = ?, lastName = ? WHERE userName = ?";
        String awardUpdate = "UPDATE awards SET firstName = ?, lastName = ? WHERE userName = ?";
        String rankingUpdate = "UPDATE userRanking SET firstName = ?, lastName = ? WHERE userName = ?";
        boolean status = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement updateMembers = con.prepareStatement(memberUpdate);
            //Replacing ? with values below:
            updateMembers.setString(1, this.firstName);
            updateMembers.setString(2, this.lastName);
            updateMembers.setString(3, this.userName);
            //Executing statement below:
            updateMembers.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateAwards = con.prepareStatement(awardUpdate);
            //Replacing ? with values below:
            updateAwards.setString(1, this.firstName);
            updateAwards.setString(2, this.lastName);
            updateAwards.setString(3, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateRanking = con.prepareStatement(rankingUpdate);
            //Replacing ? with values below:
            updateRanking.setString(1, this.firstName);
            updateRanking.setString(2, this.lastName);
            updateRanking.setString(3, this.userName);
            //Executing statement below:
            updateRanking.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean deleteMembers() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        //Creating statements below:
        String memberUpdate = "DELETE FROM teamMembers WHERE userName = ?";
        String awardUpdate = "DELETE FROM awards WHERE userName = ?";
        String rankingUpdate = "DELETE FROM userRanking WHERE userName = ?";
        String loginUpdate = "DELETE FROM userLogin WHERE userName = ?";
        boolean status = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement updateMembers = con.prepareStatement(memberUpdate);
            //Replacing ? with values below:
            updateMembers.setString(1, this.userName);
            //Executing statement below:
            updateMembers.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateAwards = con.prepareStatement(awardUpdate);
            //Replacing ? with values below:
            updateAwards.setString(1, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateRanking = con.prepareStatement(rankingUpdate);
            //Replacing ? with values below:
            updateRanking.setString(1, this.userName);
            //Executing statement below:
            updateRanking.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateLogin = con.prepareStatement(loginUpdate);
            //Replacing ? with values below:
            updateLogin.setString(1, this.userName);
            //Executing statement below:
            updateLogin.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean updateLeaders() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String memberUpdate = "UPDATE leadershipTeam SET firstName = ?, lastName = ?, leaderPosition = ? WHERE userName = ?";
        String awardUpdate = "UPDATE awards SET firstName = ?, lastName = ? WHERE userName = ?";
        boolean status = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement updateMembers = con.prepareStatement(memberUpdate);
            //Replacing ? with values below:
            updateMembers.setString(1, this.firstName);
            updateMembers.setString(2, this.lastName);
            updateMembers.setString(3, this.leaderPosition);
            updateMembers.setString(4, this.userName);
            //Executing statement below:
            updateMembers.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateAwards = con.prepareStatement(awardUpdate);
            //Replacing ? with values below:
            updateAwards.setString(1, this.firstName);
            updateAwards.setString(2, this.lastName);
            updateAwards.setString(3, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean deleteLeaders() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String memberUpdate = "DELETE FROM leadershipTeam WHERE userName = ?";
        String awardUpdate = "DELETE FROM awards WHERE userName = ?";
        String loginUpdate = "DELETE FROM adminLogin WHERE adminName = ?";
        boolean status = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement updateMembers = con.prepareStatement(memberUpdate);
            //Replacing ? with values below:
            updateMembers.setString(1, this.userName);
            //Executing statement below:
            updateMembers.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateAwards = con.prepareStatement(awardUpdate);
            //Replacing ? with values below:
            updateAwards.setString(1, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement updateLogin = con.prepareStatement(loginUpdate);
            //Replacing ? with values below:
            updateLogin.setString(1, this.userName);
            //Executing statement below:
            updateLogin.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean addLeaders() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String signUpChecker = "INSERT INTO adminLogin VALUES (?, ?)";
        String membersNameEntry = "INSERT INTO leadershipTeam VALUES (?, ?, ?, ?)";
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
            PreparedStatement nameEntry = con.prepareStatement(membersNameEntry);
            nameEntry.setString(1, this.userName);
            nameEntry.setString(2, this.firstName);
            nameEntry.setString(3, this.lastName);
            nameEntry.setString(4, this.leaderPosition);
            nameEntry.executeUpdate();
            //Executing statement below:
            PreparedStatement awardEntry = con.prepareStatement(awardEntryStatement);
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
//        teamMembers testObj = new teamMembers("debateDatabase", "teamMembers", "jdbc:mysql://localhost:3306/debateDatabase", "dhruv", "test", "Dhruv", "Saligram", "Sec-Gen");
//        ArrayList<ArrayList<String>> data = testObj.allLeadersDisplay();
//        System.out.println(data);
//        data = testObj.allMembersDisplay();
//        System.out.println(data);
//        boolean status = testObj.addLeaders();
//        System.out.println(status);
//        status = testObj.deleteLeaders();
//        System.out.println(status);
//        status = testObj.deleteMembers();
//        System.out.println(status);
//        status = testObj.updateLeaders();
//        System.out.println(status);
//        status = testObj.updateMembers();
//        System.out.println(status);
//    }
}
