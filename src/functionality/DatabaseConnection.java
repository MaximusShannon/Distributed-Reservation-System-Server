package functionality;

import models.User;

import java.sql.*;


public class DatabaseConnection {

    Connection con;

    public DatabaseConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation system", "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean checkIfUserExists(String userName){

        boolean userExists = false;
        String result = "";

        try{

            String query = "select * from users where fname = '" + userName +"'";
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(query);

            while (results.next()){

                result += "\n" + results.getInt(1) + " " + results.getString(2);
            }
            userExists = !result.equals("");

        }catch (Exception e){
            e.printStackTrace();
        }

        return userExists;
    }

    public User retreiveUser(String userName) {

        User retreivedUser = null;

        try {
            String query = "select * from users where fname = '" + userName + "'";
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {

                retreivedUser = new User(results.getInt(1), results.getString(2),
                        results.getString(3), results.getString(4), results.getString(5),
                        results.getString(6), results.getString(7), results.getString(8));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return retreivedUser;

    }


}
