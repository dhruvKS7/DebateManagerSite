/*
Dhruv K. Saligram
1/11/2020
This program facilitates mock conferences and debate team management
This page carries out calculations and algorithms associated with student ranking
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
import java.text.DecimalFormat;
import java.util.ArrayList;
//Creating class below:
public class studentRanking {
    //Declaring initializers below:
    private String dbName;
    private String tableName;
    private String connectionURL;
    private String bestUser;
    private String outstandingUser;
    private String honorableUser;
    private String awardType;
    private int databaseLength;
    //Creating constructor below:
    public studentRanking() {
        this.dbName = "";
        this.tableName = "";
        this.connectionURL = "";
        this.bestUser = "";
        this.outstandingUser = "";
        this.honorableUser = "";
        this.awardType = "";
        this.databaseLength = 0;
    }
    //Creating constructor below:
    public studentRanking(String dbName, String tableName, String connectionURL, String bestUser, String outstandingUser, String honorableUser, String awardType, int databaseLength) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.connectionURL = connectionURL;
        this.bestUser = bestUser;
        this.outstandingUser = outstandingUser;
        this.honorableUser = honorableUser;
        this.awardType = awardType;
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
    public String getAwardType() {
        return awardType;
    }
    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }
    //Creating get and set methods below:
    public String getBestUser() {
        return bestUser;
    }
    public void setBestUser(String bestUser) {
        this.bestUser = bestUser;
    }
    //Creating get and set methods below:
    public String getOutstandingUser() {
        return outstandingUser;
    }
    public void setOutstandingUser(String outstandingUser) {
        this.outstandingUser = outstandingUser;
    }
    //Creating get and set methods below:
    public String getHonorableUser() {
        return honorableUser;
    }
    public void setHonorableUser(String honorableUser) {
        this.honorableUser = honorableUser;
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
    public ArrayList<ArrayList<String>> allRankingsDisplay() {
        String[] tableHeaders = {"userName", "firstName", "lastName", "ranking"};
        dbConnection databaseObject = new dbConnection(this.dbName);
        //Sending information to receive info from the database below:
        ArrayList<ArrayList<String>> data = databaseObject.getData(this.tableName, tableHeaders);
        //Creating arraylists below:
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> firstNameList = new ArrayList<>();
        ArrayList<String> lastNameList = new ArrayList<>();
        ArrayList<String> rankingStringList = new ArrayList<>();
        //Separating table into separate arraylists below:
        for (int i = 0; i < data.size(); i++) {
            userNameList.add(data.get(i).get(0));
            firstNameList.add(data.get(i).get(1));
            lastNameList.add(data.get(i).get(2));
            rankingStringList.add(data.get(i).get(3));
        }
        //Converting arraylists below:
        ArrayList<Double> rankingList = new ArrayList<>();
        for (int i = 0; i < rankingStringList.size(); i++) {
            rankingList.add(Double.parseDouble(rankingStringList.get(i)));
        }
        //Converting arraylists below:
        String[] userNames = new String[userNameList.size()];
        this.setDatabaseLength(userNames.length);
        for (int i = 0; i < this.databaseLength; i++) {
            userNames[i] = userNameList.get(i);
        }
        //Converting arraylists below:
        String[] firstNames = new String[firstNameList.size()];
        for (int i = 0; i < this.databaseLength; i++) {
            firstNames[i] = firstNameList.get(i);
        }
        //Converting arraylists below:
        String[] lastNames = new String[lastNameList.size()];
        for (int i = 0; i < this.databaseLength; i++) {
            lastNames[i] = lastNameList.get(i);
        }
        //Converting arraylists below:
        Double[] rankings = new Double[rankingList.size()];
        for (int i = 0; i < this.databaseLength; i++) {
            rankings[i] = rankingList.get(i);
        }
        //Utilizing bubble sort algorithm below:
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
        data.clear();
        //Populating arraylist below:
        for (int i = 0; i < userNames.length; i++) {
            ArrayList<String> rowEntry = new ArrayList<>();
            rowEntry.add(userNames[i]);
            rowEntry.add(firstNames[i]);
            rowEntry.add(lastNames[i]);
            rowEntry.add(Double.toString(rankings[i]));
            data.add(rowEntry);
        }
        return data;
    }
    //Creating method below:
    public boolean regularAwardInput() {
        boolean statusCheck = true;
        //Connecting to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectSpecificRankingStatement = "SELECT ranking FROM userRanking WHERE userName = ?";
        String updateRankingStatement = "UPDATE userRanking SET ranking = ? WHERE userName = ?";
        try {
            //Connecting to database below:
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            PreparedStatement selectRanking = con.prepareStatement(selectSpecificRankingStatement);
            selectRanking.setString(1, this.getBestUser());
            ResultSet rankingInfo = selectRanking.executeQuery();
            ResultSetMetaData rankingTableInfo = rankingInfo.getMetaData();
            //Getting the column count of the database below:
            int element = rankingTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            ArrayList<String> newRankingList = new ArrayList<>();
            while (rankingInfo.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    newRankingList.add(rankingInfo.getString(z));
                }
            }
            //Converting arraylist below:
            String[] newRanking = new String[newRankingList.size()];
            for (int i = 0; i < newRanking.length; i++) {
                newRanking[i] = newRankingList.get(i);
            }
            double rank = Double.parseDouble(newRanking[0]);
            String award = this.getAwardType();
            //Changing rank based on award won below:
            if (award.equals("positionPaper") || award.equals("honorable")) {
                rank = rank * 1.08;
            } else if (award.equals("outstanding")) {
                rank = rank * 1.12;
            } else if (award.equals("best")) {
                rank = rank * 1.16;
            }
            //Formatting rank below:
            DecimalFormat formatDecimal = new DecimalFormat("#.###");
            rank = Double.valueOf(formatDecimal.format(rank));
            if (rank > 10.000) {
                rank = 10.000;
            }
            //Executing statement below:
            PreparedStatement updateHonorableRanking = con.prepareStatement(updateRankingStatement);
            updateHonorableRanking.setDouble(1, rank);
            updateHonorableRanking.setString(2, this.getBestUser());
            updateHonorableRanking.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            statusCheck = false;
        }
        return statusCheck;
    }
    //Creating method below:
    public boolean mockAwardInput() {
        boolean statusCheck = true;
        awardsDisplay awardEntry = new awardsDisplay();
        //Connecting to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectSpecificRankingStatement = "SELECT ranking FROM userRanking WHERE userName = ?";
        String updateRankingStatement = "UPDATE userRanking SET ranking = ? WHERE userName = ?";
        try {
            //Connecting to database below:
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing statement below:
            PreparedStatement selectHonorableRanking = con.prepareStatement(selectSpecificRankingStatement);
            selectHonorableRanking.setString(1, this.getHonorableUser());
            //Putting table info into result set below:
            ResultSet honorableRankingInfo = selectHonorableRanking.executeQuery();
            ResultSetMetaData honorableRankingTableInfo = honorableRankingInfo.getMetaData();
            //Getting the column count of the database below:
            int element = honorableRankingTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            ArrayList<String> newHonorableRankingList = new ArrayList<>();
            while (honorableRankingInfo.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    newHonorableRankingList.add(honorableRankingInfo.getString(z));
                }
            }
            //Converting arraylist below:
            String[] newHonorableRanking = new String[newHonorableRankingList.size()];
            for (int i = 0; i < newHonorableRanking.length; i++) {
                newHonorableRanking[i] = newHonorableRankingList.get(i);
            }
            //Formatting rank below:
            double rank = Double.parseDouble(newHonorableRanking[0]);
            rank = rank * 1.08;
            DecimalFormat formatDecimal = new DecimalFormat("#.###");
            rank = Double.valueOf(formatDecimal.format(rank));
            if (rank > 10.000) {
                rank = 10.000;
            }
            //Executing statement below:
            PreparedStatement updateHonorableRanking = con.prepareStatement(updateRankingStatement);
            updateHonorableRanking.setDouble(1, rank);
            updateHonorableRanking.setString(2, this.getHonorableUser());
            updateHonorableRanking.executeUpdate();
            awardEntry.setUserName(this.getHonorableUser());
            awardEntry.setAddAward("honorable");
            statusCheck = awardEntry.awardRegister();
            //Executing statement below:
            PreparedStatement selectOutstandingRanking = con.prepareStatement(selectSpecificRankingStatement);
            selectOutstandingRanking.setString(1, this.getOutstandingUser());
            ResultSet oustandingRankingInfo = selectOutstandingRanking.executeQuery();
            ResultSetMetaData oustandingRankingTableInfo = oustandingRankingInfo.getMetaData();
            //Getting the column count of the database below:
            element = oustandingRankingTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            ArrayList<String> newOustandingRankingList = new ArrayList<>();
            while (oustandingRankingInfo.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    newOustandingRankingList.add(oustandingRankingInfo.getString(z));
                }
            }
            //Converting arraylist below:
            String[] newOustandingRanking = new String[newOustandingRankingList.size()];
            for (int i = 0; i < newOustandingRanking.length; i++) {
                newOustandingRanking[i] = newOustandingRankingList.get(i);
            }
            //Formatting rank below:
            rank = Double.parseDouble(newOustandingRanking[0]);
            rank = rank * 1.12;
            rank = Double.valueOf(formatDecimal.format(rank));
            if (rank > 10.000) {
                rank = 10.000;
            }
            //Executing statement below:
            PreparedStatement updateOustandingRanking = con.prepareStatement(updateRankingStatement);
            updateOustandingRanking.setDouble(1, rank);
            updateOustandingRanking.setString(2, this.getOutstandingUser());
            updateOustandingRanking.executeUpdate();
            awardEntry.setUserName(this.getOutstandingUser());
            awardEntry.setAddAward("outstanding");
            statusCheck = awardEntry.awardRegister();
            //Executing statement below:
            PreparedStatement selectBestRanking = con.prepareStatement(selectSpecificRankingStatement);
            selectBestRanking.setString(1, this.getBestUser());
            ResultSet bestRankingInfo = selectBestRanking.executeQuery();
            ResultSetMetaData bestRankingTableInfo = bestRankingInfo.getMetaData();
            //Getting the column count of the database below:
            element = bestRankingTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            ArrayList<String> newBestRankingList = new ArrayList<>();
            while (bestRankingInfo.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    newBestRankingList.add(bestRankingInfo.getString(z));
                }
            }
            //Converting arraylist below:
            String[] newBestRanking = new String[newBestRankingList.size()];
            for (int i = 0; i < newBestRanking.length; i++) {
                newBestRanking[i] = newBestRankingList.get(i);
            }
            //Formatting rank below:
            rank = Double.parseDouble(newBestRanking[0]);
            rank = rank * 1.16;
            rank = Double.valueOf(formatDecimal.format(rank));
            if (rank > 10.000) {
                rank = 10.000;
            }
            //Executing statement below:
            PreparedStatement updateBestRanking = con.prepareStatement(updateRankingStatement);
            updateBestRanking.setDouble(1, rank);
            updateBestRanking.setString(2, this.getBestUser());
            updateBestRanking.executeUpdate();
            awardEntry.setUserName(this.getBestUser());
            awardEntry.setAddAward("best");
            statusCheck = awardEntry.awardRegister();
            con.close();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            statusCheck = false;
        }
        return statusCheck;
    }
    //Creating method below:
    public boolean verifyRanking() {
        boolean statusCheck = true;
        //Connecting to database below:
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        //Creating statements below:
        String selectCountryUserStatement = "SELECT userName FROM countryMatrix";
        String selectStatusStatement = "SELECT status FROM countryMatrix";
        String selectUserStatement = "SELECT userName FROM userRanking";
        String selectRankingStatement = "SELECT ranking FROM userRanking";
        String selectSpecificRankingStatement = "SELECT ranking FROM userRanking WHERE userName = ?";
        String updateRankingStatement = "UPDATE userRanking SET ranking = ? WHERE userName = ?";
        //Creating array lists below:
        ArrayList<String> countryUserList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();
        ArrayList<String> userList = new ArrayList<>();
        ArrayList<String> rankingList = new ArrayList<>();
        //Creating try and catch statement below:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            //Executing select statement to get info from the database below:
            PreparedStatement selectCountryUsers = con.prepareStatement(selectCountryUserStatement);
            //Receiving information from select statement below:
            ResultSet countryUserTable = selectCountryUsers.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData countryUserTableInfo = countryUserTable.getMetaData();
            //Getting the column count of the database below:
            int element = countryUserTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (countryUserTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    countryUserList.add(countryUserTable.getString(z));
                }
            }
            String[] countryUser = new String[countryUserList.size()];
            for (int i = 0; i < countryUser.length; i++) {
                countryUser[i] = countryUserList.get(i);
            }
            //Executing select statement to get info from the database below:
            PreparedStatement selectStatus = con.prepareStatement(selectStatusStatement);
            //Receiving information from select statement below:
            ResultSet statusTable = selectStatus.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData statusTableInfo = statusTable.getMetaData();
            //Getting the column count of the database below:
            element = statusTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (statusTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    statusList.add(statusTable.getString(z));
                }
            }
            String[] status = new String[statusList.size()];
            for (int i = 0; i < status.length; i++) {
                status[i] = statusList.get(i);
            }
            //Executing select statement to get info from the database below:
            PreparedStatement selectUsers = con.prepareStatement(selectUserStatement);
            //Receiving information from select statement below:
            ResultSet userTable = selectUsers.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData userTableInfo = userTable.getMetaData();
            //Getting the column count of the database below:
            element = userTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (userTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    userList.add(userTable.getString(z));
                }
            }
            String[] user = new String[userList.size()];
            for (int i = 0; i < user.length; i++) {
                user[i] = userList.get(i);
            }
            //Executing select statement to get info from the database below:
            PreparedStatement selectRanking = con.prepareStatement(selectRankingStatement);
            //Receiving information from select statement below:
            ResultSet rankingTable = selectRanking.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData rankingTableInfo = rankingTable.getMetaData();
            //Getting the column count of the database below:
            element = rankingTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (rankingTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    rankingList.add(rankingTable.getString(z));
                }
            }
            //Converting arraylist below:
            ArrayList<String> allAbsentMembers = new ArrayList<>();
            for (int i = 0; i < status.length; i++) {
                if (status[i].equals("absent")) {
                    allAbsentMembers.add(countryUser[i]);
                }
            }
            //Converting arraylist below:
            String[] absentMembers = new String[allAbsentMembers.size()];
            for (int i = 0; i < absentMembers.length; i++) {
                absentMembers[i] = allAbsentMembers.get(i);
            }
            for (int i = 0; i < absentMembers.length; i++) {
                //Executing statement below:
                PreparedStatement selectSpecificRanking = con.prepareStatement(selectSpecificRankingStatement);
                selectSpecificRanking.setString(1, absentMembers[i]);
                //Receiving information from select statement below:
                ResultSet specificRanking = selectSpecificRanking.executeQuery();
                ResultSetMetaData specificRankingTableInfo = specificRanking.getMetaData();
                //Getting the column count of the database below:
                element = specificRankingTableInfo.getColumnCount();
                //Creating while loop to test if ResultSet has any data left below:
                ArrayList<String> newRankingList = new ArrayList<>();
                while (specificRanking.next()) {
                    //Creating for loop to populate array list below:
                    for (int z = 1; z < element + 1; z++) {
                        newRankingList.add(specificRanking.getString(z));
                    }
                }
                //Converting arraylist below:
                String[] newRanking = new String[newRankingList.size()];
                for (int j = 0; j < newRanking.length; j++) {
                    newRanking[j] = newRankingList.get(j);
                }
                //Formatting rank below:
                double rank = Double.parseDouble(newRanking[0]);
                rank = rank * 0.92;
                DecimalFormat formatDecimal = new DecimalFormat("#.###");
                rank = Double.valueOf(formatDecimal.format(rank));
                if (rank > 10.000) {
                    rank = 10.000;
                }
                //Executing statement below:
                PreparedStatement updateSpecificRanking = con.prepareStatement(updateRankingStatement);
                updateSpecificRanking.setDouble(1, rank);
                updateSpecificRanking.setString(2, absentMembers[i]);
                updateSpecificRanking.executeUpdate();
            }
            ArrayList<String> nonParticipatingMembersList = new ArrayList<>();
            for (int i = 0; i < user.length; i++) {
                //Checking if user exists below:
                boolean exists = false;
                for (int j = 0; j < countryUser.length; j++) {
                    if (user[i].equals(countryUser[j])) {
                        exists = true;
                    }
                }
                if (exists == false) {
                    nonParticipatingMembersList.add(user[i]);
                }
            }
            //Converting arraylist below:
            String[] nonParticipatingMembers = new String[nonParticipatingMembersList.size()];
            for (int i = 0; i < nonParticipatingMembers.length; i++) {
                nonParticipatingMembers[i] = nonParticipatingMembersList.get(i);
            }
            for (int i = 0; i < nonParticipatingMembers.length; i++) {
                //Executing statement below:
                PreparedStatement selectSpecificRanking = con.prepareStatement(selectSpecificRankingStatement);
                selectSpecificRanking.setString(1, nonParticipatingMembers[i]);
                //Receiving information from select statement below:
                ResultSet specificRanking = selectSpecificRanking.executeQuery();
                ResultSetMetaData specificRankingTableInfo = specificRanking.getMetaData();
                //Getting the column count of the database below:
                element = specificRankingTableInfo.getColumnCount();
                //Creating while loop to test if ResultSet has any data left below:
                ArrayList<String> newRankingList = new ArrayList<>();
                while (specificRanking.next()) {
                    //Creating for loop to populate array list below:
                    for (int z = 1; z < element + 1; z++) {
                        newRankingList.add(specificRanking.getString(z));
                    }
                }
                //Converting arraylist below:
                String[] newRanking = new String[newRankingList.size()];
                for (int j = 0; j < newRanking.length; j++) {
                    newRanking[j] = newRankingList.get(j);
                }
                //Formatting rank below:
                double rank = Double.parseDouble(newRanking[0]);
                rank = rank * 0.95;
                DecimalFormat formatDecimal = new DecimalFormat("#.###");
                rank = Double.valueOf(formatDecimal.format(rank));
                if (rank > 10.000) {
                    rank = 10.000;
                }
                //Executing statement below:
                PreparedStatement updateSpecificRanking = con.prepareStatement(updateRankingStatement);
                updateSpecificRanking.setDouble(1, rank);
                updateSpecificRanking.setString(2, nonParticipatingMembers[i]);
                updateSpecificRanking.executeUpdate();
            }
            con.close();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            statusCheck = false;
        }
        return statusCheck;
    }
    
    public boolean convertToUserNames() {
        boolean status = true;
        Connection con = null;
        this.setConnectionURL("jdbc:mysql://localhost:3306/" + "debateDatabase");
        String selectCountryUserStatement = "SELECT userName FROM countryMatrix WHERE countryName = ?";
        ArrayList<String> userList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debateDatabase", "root", "mysql1");
            
            //Executing select statement to get info from the database below:
            PreparedStatement bestUserStatement = con.prepareStatement(selectCountryUserStatement);
            //Replacing ? with actual values below:
            bestUserStatement.setString(1, this.getBestUser());
            //Receiving information from select statement below:
            ResultSet bestUserTable = bestUserStatement.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData bestUserTableInfo = bestUserTable.getMetaData();
            //Getting the column count of the database below:
            int element = bestUserTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (bestUserTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    userList.add(bestUserTable.getString(z));
                }
            }
            //Converting arraylist to array below:
            String[] bestUserList = new String[userList.size()];
            for (int i = 0; i < bestUserList.length; i++) {
                bestUserList[i] = userList.get(i);
            }
            //Setting new value below:
            this.setBestUser(bestUserList[0]);
            userList.clear();
            
            //Executing select statement to get info from the database below:
            PreparedStatement outstandingUserStatement = con.prepareStatement(selectCountryUserStatement);
            //Replacing ? with actual values below:
            outstandingUserStatement.setString(1, this.getOutstandingUser());
            //Receiving information from select statement below:
            ResultSet outstandingUserTable = outstandingUserStatement.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData outstandingUserTableInfo = outstandingUserTable.getMetaData();
            //Getting the column count of the database below:
            element = outstandingUserTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (outstandingUserTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    userList.add(outstandingUserTable.getString(z));
                }
            }
            //Converting arraylist to array below:
            String[] outstandingUserList = new String[userList.size()];
            for (int i = 0; i < outstandingUserList.length; i++) {
                outstandingUserList[i] = userList.get(i);
            }
            //Setting new value below:
            this.setOutstandingUser(outstandingUserList[0]);
            userList.clear();
            
            //Executing select statement to get info from the database below:
            PreparedStatement honorableUserStatement = con.prepareStatement(selectCountryUserStatement);
            //Replacing ? with actual values below:
            honorableUserStatement.setString(1, this.getHonorableUser());
            //Receiving information from select statement below:
            ResultSet honorableUserTable = honorableUserStatement.executeQuery();
            //Calling meta data to access methods below:
            ResultSetMetaData honorableUserTableInfo = honorableUserTable.getMetaData();
            //Getting the column count of the database below:
            element = honorableUserTableInfo.getColumnCount();
            //Creating while loop to test if ResultSet has any data left below:
            while (honorableUserTable.next()) {
                //Creating for loop to populate array list below:
                for (int z = 1; z < element + 1; z++) {
                    userList.add(honorableUserTable.getString(z));
                }
            }
            //Converting arraylist to array below:
            String[] honorableUserList = new String[userList.size()];
            for (int i = 0; i < honorableUserList.length; i++) {
                honorableUserList[i] = userList.get(i);
            }
            //Setting new value below:
            this.setHonorableUser(honorableUserList[0]);
            userList.clear();
            
            con.close();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
    //Creating main method below:
//    public static void main (String[] args) {
//        studentRanking testObj = new studentRanking("debateDatabase", "userRanking", "jdbc:mysql://localhost:3306/debateDatabase", "rika", "aditya", "loic", "best", 4);
//        boolean status = testObj.mockAwardInput();
//        System.out.println(status);
//        ArrayList<ArrayList<String>> data = testObj.allRankingsDisplay();
//        System.out.println(data);
//        status = testObj.regularAwardInput();
//        System.out.println(status);
//        status = testObj.verifyRanking();
//        System.out.println(status);
//        testObj.setBestUser("Afghanistan");
//        testObj.setOutstandingUser("Algeria");
//        testObj.setHonorableUser("Angola");
//        status = testObj.convertToUserNames();
//        System.out.println(status);
//        System.out.println(testObj.getBestUser());
//        System.out.println(testObj.getOutstandingUser());
//        System.out.println(testObj.getHonorableUser());
//    }
}