/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with roll call
*/
//Package below:
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
public class rollCall {
    //Declaring initializers below:
    private String dbName;
    private String connectionURL;
    private String tableName;
    private String countryName;
    private String userName;
    private String countryStatus;
    private String newStatus;
    //Creating constructor below:
    public rollCall() {
        this.dbName = "";
        this.connectionURL = "";
        this.tableName = "";
        this.countryName = "";
        this.userName = "";
        this.countryStatus = "";
        this.newStatus = "";
    }
    //Creating constructor below:
    public rollCall(String dbName, String connectionURL, String tableName, String countryName, String userName, String countryStatus, String newStatus) {
        this.dbName = dbName;
        this.connectionURL = connectionURL;
        this.tableName = tableName;
        this.countryName = countryName;
        this.userName = userName;
        this.countryStatus = countryStatus;
        this.newStatus = newStatus;
    }
    //Creating get and set methods below:
    public String getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    //Creating get and set methods below:
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
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
    public String getCountryStatus() {
        return countryStatus;
    }
    public void setCountryStatus(String countryStatus) {
        this.countryStatus = countryStatus;
    }
    //Creating method below:
    public boolean addNewCountry() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        boolean status = true;
        String countryInsertion = "INSERT INTO countryMatrix VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement countryInsertCheck = con.prepareStatement(countryInsertion);
            //Replacing ? with values below:
            countryInsertCheck.setString(1, this.getCountryName());
            countryInsertCheck.setString(2, this.getUserName());
            countryInsertCheck.setString(3, "none");
            //Executing statement below:
            countryInsertCheck.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> allCountryDisplay() {
        String[] tableHeaders = {"countryName", "userName", "status"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating method below:
    public ArrayList<String> returnCountryName() {
        boolean status = true;
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectCountryStatement = "SELECT countryName FROM countryMatrix";
        //Creating array list below:
        ArrayList<String> listTest = new ArrayList<>();
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectCountry = con.prepareStatement(selectCountryStatement);
            //Receiving information from select statement below:
            ResultSet table = selectCountry.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData tableInfo = table.getMetaData();
            //Getting the column count of the database below:
            int element = tableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (table.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    listTest.add(table.getString(z));
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return listTest;
    }
    //Creating method below:
    public boolean updateStatus() {
        boolean status = true;
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String statusUpdate = "UPDATE countryMatrix SET status = ? WHERE countryName = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement updateStatus = con.prepareStatement(statusUpdate);
            //Replacing ? with values below:
            updateStatus.setString(1, this.newStatus);
            updateStatus.setString(2, this.countryName);
            //Executing statement below:
            updateStatus.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean clearDatabase() {
        boolean status = true;
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String clearCountryStatement = "DELETE FROM countryMatrix";
        String clearMotionsStatement = "DELETE FROM passedMotions";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement deleteCountries = con.prepareStatement(clearCountryStatement);
            //Executing statement below:
            deleteCountries.executeUpdate();
            //Executing select statement to get info from the database below:
            PreparedStatement deleteMotions = con.prepareStatement(clearMotionsStatement);
            //Executing statement below:
            deleteMotions.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public int calculateQuorum() {
        int quorum = 0;
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectStatusStatement = "SELECT status FROM countryMatrix";
        //Creating array list below:
        ArrayList<String> listTest = new ArrayList<>();
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectStatus = con.prepareStatement(selectStatusStatement);
            //Receiving information from select statement below:
            ResultSet table = selectStatus.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData tableInfo = table.getMetaData();
            //Getting the column count of the database below:
            int element = tableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (table.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    listTest.add(table.getString(z));
                }
            }
            //Converting array list to array below:
            String[] statusInfo = new String[listTest.size()];
            for (int i = 0; i < statusInfo.length; i++) {
                statusInfo[i] = listTest.get(i);
            }
            //Calculating number of people present below:
            int totalNumber = statusInfo.length;
            for (int i = 0; i < statusInfo.length; i++) {
                if (statusInfo[i].equals("absent")) {
                    totalNumber--;
                }
            }
            //Calculating quorum below:
            quorum = (totalNumber * 2) / 3;
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            quorum = 0;
        }
        return quorum;
    }
    //Creating main method below:
//    public static void main(String[] args) {
//        rollCall testObj = new rollCall("debateDatabase", "jdbc:mysql://localhost:3306/debateDatabase", "countryMatrix", "Albania", "rika", "present", "absent");
//        boolean check = testObj.clearDatabase();
//        System.out.println(check);
//        check = testObj.addNewCountry();
//        System.out.println(check);
//        check = testObj.updateStatus();
//        System.out.println(check);
//        int checker = testObj.calculateQuorum();
//        System.out.println(checker);
//        ArrayList<ArrayList<String>> data = testObj.allCountryDisplay();
//        System.out.println(data);
//        ArrayList<String> dataTwo = testObj.returnCountryName();
//        System.out.println(dataTwo);
//    }
}