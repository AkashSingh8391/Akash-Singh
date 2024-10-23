package com.example.test_database;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Batch {
    public static void main(String[] args) throws IOException {
        String url="jdbc:mysql://localhost:3306/akash";
        String user="root";
        String password="Shitlamaa@8989";
      //  String query="insert into employees() values(?,?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver load successfully...");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println("connected to the database");
            System.out.println(connection);
            connection.setAutoCommit(false);
           /*
            Statement statement=connection.createStatement();
            statement.addBatch("insert into employees() values(7,'rohit','cyber security analyst',61000.00)");
            statement.addBatch("insert into employees() values(8,'rohan','App_developer',81000.00)");
            statement.addBatch("insert into employees() values(9,'aman','web_develpoer',78000.00)");
            int [] Batchresult=statement.executeBatch();

            */
            String query="insert into employees() values(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner scanner=new Scanner(System.in);
            while(true){
                System.out.println("Enter employee id:");
                int id=scanner.nextInt();
                System.out.println("Enter employee name:");
                String name=scanner.nextLine();
                System.out.println("Enter job_title");
                String job_title=scanner.nextLine();
                System.out.println("Enter employee salary:");
                double salary=scanner.nextDouble();
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,job_title);
                preparedStatement.setDouble(4,salary);
                preparedStatement.addBatch();
                System.out.println("Add more value(Y/N):");
                String decision=scanner.next();
                if(decision.toUpperCase().equals("Y")){
                    break;
                }

            }
            int [] batchresult=preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch execute successfully...");

            preparedStatement.close();
            connection.close();
            System.out.println(" ");
            System.out.println("Connection close successfully");
        }
        catch (SQLException e) {
            System.out.println("Connection failed: "+e.getMessage());
        }
    }
}
