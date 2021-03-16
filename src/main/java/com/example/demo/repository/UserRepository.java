package com.example.demo.repository;

import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.sql.*;

public class UserRepository{
    //Repository til vores brugere
    private Connection connection;

    public boolean setConnection(){
        boolean bres = false;
        String url = "jdbc:mysql://127.0.0.1:3306/kinoxp?serverTimezone=UTC";
        try{
            connection = DriverManager.getConnection(url,"username","password");
            bres = true;
        }
        catch (SQLException e){
            System.out.println("No connection to server="+e.getMessage());
        }
        return bres;
    }

    public int createProfile(String firstname, String surname, Date birthday, String username, String email, String password){
        String insertStatement = "INSERT INTO user_model (firstname, surname, birthday, username, email, password) VALUES ( ? , ? , ?, ?, ?, ?)";
        int id = -1;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setDate(3, birthday);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);

            preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            System.out.println("Profile creation failed="+e.getMessage());
        }
        return id;
    }

    public UserModel getUserInfo(int id){
        ResultSet res = null;
        String selectSQL =
                "SELECT * FROM user_model " +
                        "WHERE id = ?";

        String firstname = null;
        String surname = null;
        Date birthday = null;
        String username = null;
        String email = null;
        String password = null;
        int userID = -1;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            res =preparedStatement.executeQuery();
            res.next();

            firstname = res.getString("firstname");
            surname = res.getString("surname");
            birthday = res.getDate("birthday");
            username = res.getString("username");
            email = res.getString("email");
            password = res.getString("password");
            id = res.getInt("id");

        }
        catch (SQLException e){
            System.out.println("GetUserInfoError="+e.getMessage());
        }

        UserModel userModel = new UserModel(firstname, surname, birthday, username, email, password);
        return userModel;
    }

    public UserRepository deleteProfile(int id){
        String deleteStatement = "DELETE FROM user_model WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Failed to delete profile="+e.getMessage());
        }
        return null;
    }

    //LoginService
    public int getUserFromLogin(String username, String password){
        String selectStatement =
                "SELECT id FROM user_model " +
                        "WHERE username = ? AND password = ?";
        int userID = -1;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");
        }
        catch (SQLException e){
            System.out.println("Retrieving userID from login failed="+e.getMessage());
        }
        return userID;
    }

}
