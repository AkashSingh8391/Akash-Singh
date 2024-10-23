package com.example.test_database;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class prepStatement {
    public static void main(String[] args) throws IOException {
        String url="jdbc:mysql://localhost:3306/akash";
        String user="root";
        String password="Shitlamaa@8989";
        String query="insert into employees() values(?,?,?,?)";
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
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            System.out.println("enter the data:");
            Scanner scanner=new Scanner(System.in);
            int id= scanner.nextInt();
            String name= scanner.nextLine();
            String job_title= scanner.nextLine();
            Double salary= scanner.nextDouble();

           preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,job_title);
            preparedStatement.setDouble(4,salary);
            int rowaffected=preparedStatement.executeUpdate();
            if(rowaffected>0){
                System.out.println("insertion successfully....");
            }
            else{
                System.out.println("insertion failed....");
            }

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
