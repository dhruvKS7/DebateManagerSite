/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with awards
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
public class awardsDisplay {
    //Defining instance variables below:
    private String connectionURL;
    private String dbName;
    private String userName;
    private String addAward;
    private String tableName;
    private int newAwardCount;
    //Creating constructor below:
    public awardsDisplay() {
        this.connectionURL = "";
        this.dbName = "";
        this.userName = "";
        this.addAward = "";
        this.tableName = "";
        this.newAwardCount = 0;
    }
    //Creating constructor below:
    public awardsDisplay(String connectionURL, String dbName, String userName, String addAward, String tableName, int newAwardCount) {
        this.connectionURL = connectionURL;
        this.dbName = dbName;
        this.userName = userName;
        this.addAward = addAward;
        this.tableName = tableName;
        this.newAwardCount = newAwardCount;
    }
    //Creating get and set methods below:
    public int getNewAwardCount() {
        return newAwardCount;
    }
    public void setNewAwardCount(int newAwardCount) {
        this.newAwardCount = newAwardCount;
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //Creating get and set methods below:
    public String getAddAward() {
        return addAward;
    }
    public void setAddAward(String addAward) {
        this.addAward = addAward;
    }
    //Creating get and set methods below:
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    //Creating method below:
    public boolean awardUpdate() {
        //Establishing connection to database below:
        boolean status = true;
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String selectAwardsStatement = "SELECT * FROM awards WHERE userName = ?";
        String updateAwardsStatement = "UPDATE awards SET positionPaper = ?, honorable = ?, outstanding = ?, best = ? WHERE userName = ?";
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectAwards = con.prepareStatement(selectAwardsStatement);
            //Replacing ? with values below:
            selectAwards.setString(1, this.userName);
            //Receiving information from select statement below:
            ResultSet table = selectAwards.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData tableInfo = table.getMetaData();
            //Creating array list below:
            ArrayList<String> listTest = new ArrayList<>();
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
            String[] awardsInfo = new String[listTest.size()];
            for (int i = 0; i < awardsInfo.length; i++) {
                awardsInfo[i] = listTest.get(i);
            }
            //Executing update statement to insert info into the database below:
            PreparedStatement updateAwards = con.prepareStatement(updateAwardsStatement);
            //Testing various conditions to update specific information in the database below:
            if (this.addAward.equals("positionPaper")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, this.newAwardCount);
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("honorable")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, this.newAwardCount);
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("outstanding")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, this.newAwardCount);
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("best")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, this.newAwardCount);
            }
            //Replacing ? with values below:
            updateAwards.setString(5, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean awardRegister() {
        //Establishing connection to database below:
        boolean status = true;
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectAwardsStatement = "SELECT * FROM awards WHERE userName = ?";
        String updateAwardsStatement = "UPDATE awards SET positionPaper = ?, honorable = ?, outstanding = ?, best = ? WHERE userName = ?";
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectAwards = con.prepareStatement(selectAwardsStatement);
            //Replacing ? with values below:
            selectAwards.setString(1, this.userName);
            //Receiving information from select statement below:
            ResultSet table = selectAwards.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData tableInfo = table.getMetaData();
            //Creating array list below:
            ArrayList<String> listTest = new ArrayList<>();
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
            String[] awardsInfo = new String[listTest.size()];
            for (int i = 0; i < awardsInfo.length; i++) {
                awardsInfo[i] = listTest.get(i);
            }
            //Executing update statement to insert info into the database below:
            PreparedStatement updateAwards = con.prepareStatement(updateAwardsStatement);
            //Testing various conditions to update specific information in the database below:
            if (this.addAward.equals("positionPaper")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]) + 1);
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("honorable")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]) + 1);
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("outstanding")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]) + 1);
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]));
            } else if (this.addAward.equals("best")) {
                //Replacing ? with values below:
                updateAwards.setInt(1, Integer.parseInt(awardsInfo[3]));
                updateAwards.setInt(2, Integer.parseInt(awardsInfo[4]));
                updateAwards.setInt(3, Integer.parseInt(awardsInfo[5]));
                updateAwards.setInt(4, Integer.parseInt(awardsInfo[6]) + 1);
            }
            //Replacing ? with values below:
            updateAwards.setString(5, this.userName);
            //Executing statement below:
            updateAwards.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> allAwardsDisplay() {
        String[] tableHeaders = {"userName", "firstName", "lastName", "positionPaper", "honorable", "outstanding", "best"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating main method below:
//    public static void main(String[] args) {
//        awardsDisplay testObj = new awardsDisplay("jdbc:mysql://localhost:3306/debateDatabase", "debateDatabase", "dhruv", "best", "awards", 2);
//        boolean status = testObj.awardUpdate();
//        System.out.println(status);
//        status = testObj.awardRegister();
//        System.out.println(status);
//        ArrayList<ArrayList<String>> data = testObj.allAwardsDisplay();
//        System.out.println(data);
//    }
}