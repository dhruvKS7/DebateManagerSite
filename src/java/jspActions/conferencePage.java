/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with conferences
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
public class conferencePage {
    //Defining instance variables below:
    private String dbName;
    private String connectionURL;
    private String tableName;
    private String conferenceName;
    private int conferenceYear;
    private String conferenceLocation;
    private int numOfStudents;
    private int databaseLength;
    //Creating constructor below:
    public conferencePage() {
        this.dbName = "";
        this.connectionURL = "";
        this.tableName = "";
        this.conferenceName = "";
        this.conferenceYear = 0;
        this.conferenceLocation = "";
        this.numOfStudents = 0;
        this.databaseLength = 0;
    }
    //Creating constructor below:
    public conferencePage(String dbName, String connectionURL, String tableName, String conferenceName, int conferenceYear, String conferenceLocation, int numOfStudents, int databaseLength) {
        this.dbName = dbName;
        this.connectionURL = connectionURL;
        this.tableName = tableName;
        this.conferenceName = conferenceName;
        this.conferenceYear = conferenceYear;
        this.conferenceLocation = conferenceLocation;
        this.numOfStudents = numOfStudents;
        this.databaseLength = databaseLength;
    }
    //Creating get and set methods below:
    public int getDatabaseLength() {
        return databaseLength;
    }
    public void setDatabaseLength(int databaseLength) {
        this.databaseLength = databaseLength;
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
    public String getConferenceLocation() {
        return conferenceLocation;
    }
    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }
    //Creating get and set methods below:
    public int getNumOfStudents() {
        return numOfStudents;
    }
    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
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
    public ArrayList<ArrayList<String>> allConferencesDisplay() {
        String[] tableHeaders = {"conferenceName", "conferenceYear", "location", "numOfStudents"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        return data;
    }
    //Creating method below:
    public boolean addConferences() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String conferenceEntry = "INSERT INTO conferences VALUES (?, ?, ?, ?)";
        boolean status = true;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement conferenceRegister = con.prepareStatement(conferenceEntry);
            //Replacing ? with values below:
            conferenceRegister.setString(1, this.conferenceName);
            conferenceRegister.setInt(2, this.conferenceYear);
            conferenceRegister.setString(3, this.conferenceLocation);
            conferenceRegister.setInt(4, this.numOfStudents);
            //Executing statement below:
            conferenceRegister.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean updateConferences() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String conferenceEntry = "UPDATE conferences SET location = ?, numOfStudents = ? WHERE conferenceName = ? AND conferenceYear = ?";
        boolean status = true;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement conferenceRegister = con.prepareStatement(conferenceEntry);
            //Replacing ? with values below:
            conferenceRegister.setString(1, this.conferenceLocation);
            conferenceRegister.setInt(2, this.numOfStudents);
            conferenceRegister.setString(3, this.conferenceName);
            conferenceRegister.setInt(4, this.conferenceYear);
            //Executing statement below:
            conferenceRegister.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public boolean deleteConferences() {
        //Establishing connection to database below:
        Connection con = null;
        this.setConnectionURL(this.connectionURL + this.dbName);
        String conferenceDeleteStatement = "DELETE FROM conferences WHERE conferenceName = ? AND conferenceYear = ?";
        boolean status = true;
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.connectionURL, "root", "mysql1");
            //Executing insert statement to insert info into the database below:
            PreparedStatement conferenceDelete = con.prepareStatement(conferenceDeleteStatement);
            //Replacing ? with values below:
            conferenceDelete.setString(1, this.conferenceName);
            conferenceDelete.setInt(2, this.conferenceYear);
            //Executing statement below:
            conferenceDelete.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            status = false;
        }
        return status;
    }
    //Creating method below:
    public ArrayList<ArrayList<String>> rankConferences() {
        //Creating arraylists below:
        ArrayList<ArrayList<String>> rankedList = new ArrayList<>();
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> firstNameList = new ArrayList<>();
        ArrayList<String> lastNameList = new ArrayList<>();
        ArrayList<String> rankingStringList = new ArrayList<>();
        ArrayList<String> numStudentsStringList = new ArrayList<>();
        studentRanking rankDb = new studentRanking();
        rankDb.setDbName("debateDatabase");
        rankDb.setTableName("userRanking");
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectNumberStatement = "SELECT numOfStudents FROM conferences WHERE conferenceName = ? AND conferenceYear = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectNumber = con.prepareStatement(selectNumberStatement);
            selectNumber.setString(1, this.conferenceName);
            selectNumber.setInt(2, this.conferenceYear);
            //Receiving information from select statement below:
            ResultSet numberTable = selectNumber.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData numberTableInfo = numberTable.getMetaData();
            //Getting the column count of the database below:
            int element = numberTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (numberTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    numStudentsStringList.add(numberTable.getString(z));
                }
            }
            //Converting arraylist of string to arraylist of integer below:
            ArrayList<Integer> numStudentsList = new ArrayList<>();
            for (int i = 0; i < numStudentsStringList.size(); i++) {
                numStudentsList.add(Integer.parseInt(numStudentsStringList.get(i)));
            }
            //Converting arraylist to array below:
            Integer[] numStudents = new Integer[numStudentsList.size()];
            for (int i = 0; i < numStudents.length; i++) {
                numStudents[i] = numStudentsList.get(i);
            }
            this.setNumOfStudents(numStudents[0]);
            //Separating table into separate arraylists below:
            rankedList = rankDb.allRankingsDisplay();
            for (int i = 0; i < rankedList.size(); i++) {
                userNameList.add(rankedList.get(i).get(0));
                firstNameList.add(rankedList.get(i).get(1));
                lastNameList.add(rankedList.get(i).get(2));
                rankingStringList.add(rankedList.get(i).get(3));
            }
            //Converting arraylist of string into arraylist of double below:
            ArrayList<Double> rankingList = new ArrayList<>();
            for (int i = 0; i < rankingStringList.size(); i++) {
                rankingList.add(Double.parseDouble(rankingStringList.get(i)));
            }
            //Converting arraylist to array below:
            String[] userNames = new String[userNameList.size()];
            this.setDatabaseLength(userNames.length);
            for (int i = 0; i < this.databaseLength; i++) {
                userNames[i] = userNameList.get(i);
            }
            //Converting arraylist to array below:
            String[] firstNames = new String[firstNameList.size()];
            for (int i = 0; i < this.databaseLength; i++) {
                firstNames[i] = firstNameList.get(i);
            }
            //Converting arraylist to array below:
            String[] lastNames = new String[lastNameList.size()];
            for (int i = 0; i < this.databaseLength; i++) {
                lastNames[i] = lastNameList.get(i);
            }
            //Converting arraylist to array below:
            Double[] rankings = new Double[rankingList.size()];
            for (int i = 0; i < this.databaseLength; i++) {
                rankings[i] = rankingList.get(i);
            }
            //Running a bubble sort algorithm below:
            boolean completed = false;
            while (completed != true) {
                completed = true;
                for (int k = 0; k < this.databaseLength - 1; k++) {
                    if (rankings[k + 1] > rankings[k]) {
                        completed = false;
                        //Changing values in all arraylists below:
                        double replaceRank = rankings[k];
                        rankings[k] = rankings[k + 1];
                        rankings[k + 1] = replaceRank;
                        String replaceUser = userNames[k];
                        userNames[k] = userNames[k + 1];
                        userNames[k + 1] = replaceUser;
                        String replaceFirst = firstNames[k];
                        firstNames[k] = firstNames[k + 1];
                        firstNames[k + 1] = replaceFirst;
                        String replaceLast = lastNames[k];
                        lastNames[k] = lastNames[k + 1];
                        lastNames[k + 1] = replaceLast;
                    }
                }
            }
            //Checking if members need to be subtracted below:
            if (this.getNumOfStudents() < this.getDatabaseLength()) {
                rankedList.clear();
                //Populating arraylist below:
                for (int i = 0; i < this.getNumOfStudents(); i++) {
                    ArrayList<String> rowEntry = new ArrayList<>();
                    rowEntry.add(userNames[i]);
                    rowEntry.add(firstNames[i]);
                    rowEntry.add(lastNames[i]);
                    rowEntry.add(Double.toString(rankings[i]));
                    rankedList.add(rowEntry);
                }
            } else {
                rankedList.clear();
                //Populating arraylist below:
                for (int i = 0; i < userNames.length; i++) {
                    ArrayList<String> rowEntry = new ArrayList<>();
                    rowEntry.add(userNames[i]);
                    rowEntry.add(firstNames[i]);
                    rowEntry.add(lastNames[i]);
                    rowEntry.add(Double.toString(rankings[i]));
                    rankedList.add(rowEntry);
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            rankedList.clear();
            ArrayList<String> error = new ArrayList<>();
            error.add("0");
            rankedList.add(error);
        }
        return rankedList;
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
//        conferencePage testObj = new conferencePage("debateDatabase", "jdbc:mysql://localhost:3306/debateDatabase", "conferences", "CTMUN Fall", 2020, "Austin, TX", 5, 1);
//        ArrayList<ArrayList<String>> members = testObj.rankConferences();
//        System.out.println(members);
//        members = testObj.allConferencesDisplay();
//        System.out.println(members);
//        boolean status = testObj.addConferences();
//        System.out.println(status);
//        status = testObj.updateConferences();
//        System.out.println(status);
//        status = testObj.deleteConferences();
//        System.out.println(status);
//    }
}
