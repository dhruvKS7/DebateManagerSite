/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with messaging
*/
//Package below:
package jspActions;
//Imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//Creating class below:
public class messagingSystem {
    //Declaring initializers below:
    private String dbName;
    private String connectionURL;
    private String tableName;
    private String recipient;
    private String message;
    private String sender;
    //Creating constructor below:
    public messagingSystem() {
        this.dbName = "";
        this.tableName = "";
        this.recipient = "";
        this.message = "";
        this.sender = "";
    }
    //Creating constructor below:
    public messagingSystem(String dbName, String connectionURL, String tableName, String recipient, String message, String sender) {
        this.dbName = dbName;
        this.connectionURL = connectionURL;
        this.tableName = tableName;
        this.recipient = recipient;
        this.message = message;
        this.sender = sender;
    }
    //Creating get and set methods below:
    public String getConnectionURL() {
        return connectionURL;
    }
    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    //Creating get and set methods below:
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    //Creating get and set methods below:
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    //Creating get and set methods below:
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
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
    public ArrayList<ArrayList<String>> allMessagesDisplay() {
        String[] tableHeaders = {"messageNumber", "recipient", "message", "sender"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> specificMessagesDisplay(String currentUserName) {
        String[] tableHeaders = {"messageNumber", "recipient", "message", "sender"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = this.getMessages(this.tableName, tableHeaders, currentUserName);
        return data;
    }
    //Creating method below:
    public boolean sendMessages() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String messageSend = "INSERT INTO messagingSystem (recipient, message, sender) VALUES (?, ?, ?)";
        boolean status = true;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement messageSender = con.prepareStatement(messageSend);
            //Replacing ? with values below:
            messageSender.setString(1, this.recipient);
            messageSender.setString(2, this.message);
            messageSender.setString(3, this.sender);
            //Executing statement below:
            messageSender.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Executing select statement below:
    public ArrayList<ArrayList<String>> getMessages(String tableName, String[] tableHeaders, String currentUserName) {
        //Creating variables below:
        int columnCount = tableHeaders.length;
        Connection con = null;
        ResultSet rs = null;
        Statement s = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String dbQuery = "SELECT * FROM " + tableName + " WHERE recipient='" + currentUserName + "'";
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Adding values to 2D array from array list below:
            s = con.createStatement();
            rs = s.executeQuery(dbQuery);
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    row.add(rs.getString(tableHeaders[i]));
                }
                data.add(row);
            }
        } //Catching SQL exceptions below:
        catch (SQLException | ClassNotFoundException e) {
            //Giving user error message below:
            System.out.println("Error getting data from database!");
            System.exit(0);
        }
        return data;
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
//        messagingSystem testObj = new messagingSystem("debateDatabase", "jdbc:mysql://localhost:3306/debateDatabase", "messagingSystem", "rika", "tester", "dhruv");
//        ArrayList<ArrayList<String>> data = testObj.allMessagesDisplay();
//        System.out.println(data);
//        data = testObj.specificMessagesDisplay("rika");
//        System.out.println(data);
//        boolean status = testObj.sendMessages();
//        System.out.println(status);
//        int x = testObj.characterTester("'");
//        System.out.println(x);
//    }
}
