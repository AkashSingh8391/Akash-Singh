package com.example.test_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/akash";
        String user="root";
        String password="Shitlamaa@8989";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver load successfully...");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try(Connection connection= DriverManager.getConnection(url,user,password)) {
            System.out.println("connected to the database");
            System.out.println(connection);
            Statement statement=connection.createStatement();
            int rowsaffected=statement.executeUpdate("delete from employees where id=3;");

            if(rowsaffected>0){
                System.out.println("Deletion  successfully: "+rowsaffected+" row(s) affected..");
            }
            else {
                System.out.println("Deletion  failed...");
            }

            statement.close();
            connection.close();
            System.out.println(" ");
            System.out.println("Connection close successfully");
        }

        catch (SQLException e) {
            System.out.println("Connection failed: "+e.getMessage());
        }
    }
}
