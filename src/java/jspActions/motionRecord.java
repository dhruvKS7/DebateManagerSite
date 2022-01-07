/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with mock motions
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
public class motionRecord {
    //Declaring initializers below:
    private String connectionURL;
    private String dbName;
    private String tableName;
    private String[] motion;
    private int[] totalTime;
    private int[] speakingTime;
    private String[] country;
    private int[] votes;
    private final int NUMBER_OF_MOTIONS = 3;
    //Creating constructor below:
    public motionRecord() {
        this.connectionURL = "";
        this.dbName = "";
        this.tableName = "";
        this.motion = null;
        this.totalTime = null;
        this.speakingTime = null;
        this.country = null;
        this.votes = null;
    }
    //Creating constructor below:
    public motionRecord(String connectionURL, String dbName, String tableName, String[] motion, int[] totalTime, int[] speakingTime, String[] country, int[] votes) {
        this.connectionURL = connectionURL;
        this.dbName = dbName;
        this.tableName = tableName;
        this.motion = motion;
        this.totalTime = totalTime;
        this.speakingTime = speakingTime;
        this.country = country;
        this.votes = votes;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    //Creating get and set methods below:
    public int[] getVotes() {
        return votes;
    }
    public void setVotes(int[] votes) {
        this.votes = votes;
    }
    //Creating get and set methods below:
    public String[] getMotion() {
        return motion;
    }
    public void setMotion(String[] motion) {
        this.motion = motion;
    }
    //Creating get and set methods below:
    public int[] getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(int[] totalTime) {
        this.totalTime = totalTime;
    }
    //Creating get and set methods below:
    public int[] getSpeakingTime() {
        return speakingTime;
    }
    public void setSpeakingTime(int[] speakingTime) {
        this.speakingTime = speakingTime;
    }
    //Creating get and set methods below:
    public String[] getCountry() {
        return country;
    }
    public void setCountry(String[] country) {
        this.country = country;
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
    public String[] verifyMotion() {
        //Establishing connection to database below:
        Connection con = null;
        String[] motionDetails = new String[4];
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String passMotions = "INSERT INTO passedMotions (motion, totalTime, speakingTime, country) VALUES (?, ?, ?, ?)";
        boolean status = true;
        rollCall quorumDecider = new rollCall();
        int quorum = quorumDecider.calculateQuorum();
        boolean passedMotions = false;
        int highestVotes = this.votes[0];
        int highestIndex = 0;
        //Determining the motion with the most votes below:
        for (int i = 0; i < NUMBER_OF_MOTIONS; i++) {
            if (this.votes[i] > highestVotes) {
                highestVotes = this.votes[i];
                highestIndex = i;
            }
        }
        //Checking if any motion got enough votes below:
        if (highestVotes > quorum) {
            passedMotions = true;
        }
        if (passedMotions == true) {
            //Creating try and catch statement below:
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
                //Executing insert statement to insert info into the database below:
                PreparedStatement passMotionsStatement = con.prepareStatement(passMotions);
                //Replacing ? with values below:
                passMotionsStatement.setString(1, this.motion[highestIndex]);
                passMotionsStatement.setInt(2, this.totalTime[highestIndex]);
                passMotionsStatement.setInt(3, this.speakingTime[highestIndex]);
                passMotionsStatement.setString(4, this.country[highestIndex]);
                //Executing statement below:
                passMotionsStatement.executeUpdate();
                con.close();
                motionDetails[0] = this.motion[highestIndex];
                motionDetails[1] = Integer.toString(this.totalTime[highestIndex]);
                motionDetails[2] = Integer.toString(this.speakingTime[highestIndex]);
                motionDetails[3] = this.country[highestIndex];
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                motionDetails[0] = "error";
            }
        } else {
            //Alerting user to update below:
            motionDetails[0] = "SPEAKER'S LIST";
        }
        return motionDetails;
    }
    //Creating method below:
    public boolean verifyPaper() {
        boolean passing = false;
        rollCall quorumDecider = new rollCall();
        //Calculating quorum below:
        int quorum = quorumDecider.calculateQuorum();
        //Determining if paper passed below:
        if (this.votes[0] >= quorum) {
            passing = true;
        }
        return passing;
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
    
    //Creating method below:
    public ArrayList<ArrayList<String>> allMotionsDisplay() {
        String[] tableHeaders = {"motionNumber", "motion", "totalTime", "speakingTime", "country"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    
    //Creating main method below:
//    public static void main (String[] args) {
//        String[] country = {"hi", "hello", "oops"};
//        String[] motion = {"un", "mo", "de"};
//        int[] time = {5, 6, 7};
//        int[] speak = {1, 2, 3};
//        int[] vote = {10, 0, 0};
//        motionRecord testObj = new motionRecord("jdbc:mysql://localhost:3306/debateDatabase", "debateDatabase", "passedMotions", motion, time, speak, country, vote);
//        String[] test = testObj.verifyMotion();
//        for (int i=0; i<test.length; i++) {
//            System.out.println(test[i]);
//        }
//        ArrayList<ArrayList<String>> data = testObj.allMotionsDisplay();
//        System.out.println(data);
//        boolean status = testObj.verifyPaper();
//        System.out.println(status);
//    }
}