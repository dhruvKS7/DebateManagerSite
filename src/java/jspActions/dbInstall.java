/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page installs the database and tables
*/
//Package below:
package jspActions;
//Imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Creating class below:
public class dbInstall {
    //Creating main method below:
    public static void main(String[] args) {
        dbInstall installation = new dbInstall();
        dbConnection database = new dbConnection();
        //Creating database below:
        database.createDb("debateDatabase");
        //Creating database table below:
        String userLogin = "CREATE TABLE userLogin ( "
                + "userName varchar(30), "
                + "password varchar(30), "
                + "PRIMARY KEY(userName) "
                + ")";
        database.createTable(userLogin, "debateDatabase");
        //Creating database table below:
        String adminLogin = "CREATE TABLE adminLogin ( "
                + "adminName varchar(30), "
                + "password varchar(30), "
                + "PRIMARY KEY(adminName) "
                + ")";
        database.createTable(adminLogin, "debateDatabase");
        //Creating database table below:
        String countryMatrix = "CREATE TABLE countryMatrix ( "
                + "countryName varchar(20), "
                + "userName varchar(30), "
                + "status varchar(10), "
                + "PRIMARY KEY(countryName) "
                + ")";
        database.createTable(countryMatrix, "debateDatabase");
        //Creating database table below:
        String messagingSystem = "CREATE TABLE messagingSystem ( "
                + "messageNumber int AUTO_INCREMENT, "
                + "recipient varchar(30) NOT NULL, "
                + "message varchar(150) NOT NULL, "
                + "sender varchar(30) NOT NULL, "
                + "PRIMARY KEY (messageNumber, recipient, message, sender), "
                + "UNIQUE(recipient, message, sender) "
                + ")";
        database.createTable(messagingSystem, "debateDatabase");
        //Creating database table below:
        String userRanking = "CREATE TABLE userRanking ( "
                + "userName varchar(30), "
                + "firstName varchar(30), "
                + "lastName varchar(30), "
                + "ranking double, "
                + "PRIMARY KEY(userName) "
                + ")";
        database.createTable(userRanking, "debateDatabase");
        //Creating database table below:
        String teamMembers = "CREATE TABLE teamMembers ( "
                + "userName varchar(30), "
                + "firstName varchar(30), "
                + "lastName varchar(30), "
                + "PRIMARY KEY(userName) "
                + ")";
        database.createTable(teamMembers, "debateDatabase");
        //Creating database table below:
        String leadershipTeam = "CREATE TABLE leadershipTeam ( "
                + "userName varchar(30), "
                + "firstName varchar(30), "
                + "lastName varchar(30), "
                + "leaderPosition varchar(30), "
                + "PRIMARY KEY(userName) "
                + ")";
        database.createTable(leadershipTeam, "debateDatabase");
        //Creating database table below:
        String awards = "CREATE TABLE awards ( "
                + "userName varchar(30), "
                + "firstName varchar(30), "
                + "lastName varchar(30), "
                + "positionPaper int, "
                + "honorable int, "
                + "outstanding int, "
                + "best int, "
                + "PRIMARY KEY(userName) "
                + ")";
        database.createTable(awards, "debateDatabase");
        //Creating database table below:
        String conferences = "CREATE TABLE conferences ( "
                + "conferenceName varchar(30), "
                + "conferenceYear int, "
                + "location varchar(40), "
                + "numOfStudents int, "
                + "PRIMARY KEY(conferenceName, conferenceYear) "
                + ")";
        database.createTable(conferences, "debateDatabase");
        //Creating database table below:
        String passedMotions = "CREATE TABLE passedMotions ( "
                + "motionNumber int AUTO_INCREMENT, "
                + "motion varchar(50), "
                + "totalTime int, "
                + "speakingTime int, "
                + "country varchar(30), "
                + "PRIMARY KEY(motionNumber) "
                + ")";
        database.createTable(passedMotions, "debateDatabase");
        installation.initializer();
    }
    //Creating method below:
    public void initializer() {
        String initialize = "INSERT INTO adminLogin VALUES (?, ?)";
        //Establishing connection to database below:
        Connection dbConnection = null;
        String DbName = "debateDatabase";
        String connectionURL = "jdbc:mysql://localhost:3306/" + DbName;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement preparedInitialize = dbConnection.prepareStatement(initialize);
            preparedInitialize.setString(1, "masterLogin");
            preparedInitialize.setString(2, "masterPassword");
            preparedInitialize.executeUpdate();
            dbConnection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error inserting");
        }
    }
}
