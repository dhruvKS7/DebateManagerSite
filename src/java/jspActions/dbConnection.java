/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with database connection
*/
//Declaring package below:
package jspActions;
//Declaring imports below:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//Creating class below"
public class dbConnection {
    //Declarations below:
    private String dbName;
    private ArrayList<ArrayList<String>> data;
    private Connection dbConn;
    //Setting values below:
    public dbConnection(String dbName) {
        this.dbName = dbName;
        this.data = null;
        setDbConn();
    }
    //Setting all values to null beow:
    public dbConnection() {
        this.dbName = "";
        this.data = null;
        this.dbConn = null;
    }
    //Using get method for database name below:
    public String getDbName() {
        return dbName;
    }
    //Using set method for database name below:
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    //Executing select statement below:
    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders) {
        //Creating variables below:
        int columnCount = tableHeaders.length;
        ResultSet rs = null;
        Statement s = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();
        try {
            //Adding values to 2D array from array list below:
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery);
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.data.add(row);
            }
        } //Catching SQL exceptions below:
        catch (SQLException e) {
            //Giving user error message below:
            System.out.println("Error getting data from database!");
            System.exit(0);
        }
        return this.data;
    }
    //Setting data below:
    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }
    //Setting connection below:
    public Connection getDbConn() {
        return dbConn;
    }
    //Setting database connection below:
    public void setDbConn() {
        String connectionURL = "jdbc:mysql://localhost:3306/" + this.dbName;
        this.dbConn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
        } catch (SQLException a) {
            System.out.println("SQL Connection error");
            System.exit(0);
        } catch (ClassNotFoundException i) {
            System.out.println("Class name not found");
            System.exit(0);
        }
    }
    //Creating database below:
    public void createDb(String newDbName) {
        this.dbName = newDbName;
        String connectionURL = "jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
            Statement s = this.dbConn.createStatement();
            int Result = s.executeUpdate("CREATE DATABASE  " + newDbName);
            System.out.println("New database created.");
            this.dbConn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creating database: " + newDbName);
            System.exit(0);
        }
    }
    //Creating database table below:
    public void createTable(String newTable, String dbName) {
        Statement s;
        setDbName(dbName);
        setDbConn();
        try {
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New table created.");
            this.dbConn.close();
        } catch (SQLException e) {
            System.out.println("Error creating table:" + newTable);
            System.exit(0);
        }
    }
    //Creating main method below:
//    public static void main(String[] args) {
//        dbConnection testObj = new dbConnection("debateDatabase");
//        String[] headers = {"adminName", "password"};
//        ArrayList<ArrayList<String>> data = testObj.getData("adminLogin", headers);
//        System.out.println(data);
//        testObj.createDb("tester");
//        testObj.createTable("table", "tester");
//    }
}