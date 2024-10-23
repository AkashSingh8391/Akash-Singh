package com.example.test_database;

import java.sql.*;

public class HelloApplication {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/akash";
        String user="root";
        String password="Shitlamaa@8989";
        String query="Select * from employees";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver load successfully...");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try(Connection connection=DriverManager.getConnection(url,user,password)) {
            System.out.println("connected to the database");
            System.out.println(connection);
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String job=resultSet.getString("job_title");
                Double salary=resultSet.getDouble("salary");

                System.out.println(id);
                System.out.println(name);
                System.out.println(job);
                System.out.println(salary);
                System.out.println("===================================");
            }

            resultSet.close();
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