package com.example.test_database;
import java.sql.*;
import java.util.Scanner;
public class account {
    public static void main(String[] args)throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        String url="jdbc:mysql://localhost:3306/akash";
        String user,password;
        System.out.println("Enter Username:");
        user =sc.nextLine();
        System.out.println("Enter Password:");
        password =sc.nextLine();

        String withdrawquery="update account set balance=balance-? where acc_no=?";
        String depositquery="update account set balance=balance+? where acc_no=?";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver load successfully...");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("connected to the database");
            System.out.println(connection);
            connection.setAutoCommit(false);
            try{
            PreparedStatement withdraw = connection.prepareStatement(withdrawquery);
            PreparedStatement deposit = connection.prepareStatement(depositquery);
                System.out.println("Amount:");
                double amount=sc.nextDouble();
                System.out.println("Account1:");
                String account1=sc.nextLine();
                System.out.println("Account2:");
                String account2=sc.nextLine();

                withdraw.setDouble(1, amount);
            withdraw.setString(2, account1);
            deposit.setDouble(1, amount);
            deposit.setString(2, account2);
            int rowaffectedwithdrawl=withdraw.executeUpdate();
            int rowaffecteddeposit=deposit.executeUpdate();
            if(rowaffectedwithdrawl>0 && rowaffecteddeposit>0) {
                connection.commit();
                System.out.println("Transaction successful");
            }else{
                connection.rollback();
                System.out.println("Transaction failed");
            }
        }catch (SQLException e){
                System.out.println(e.getMessage());
        }
            connection.close();
            System.out.println(" ");
            System.out.println("Connection close successfully");
        }
        catch (SQLException e) {
            System.out.println("Connection failed: "+e.getMessage());
        }
    }
}
