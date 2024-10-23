package com.example.test_database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Scanner;

public class image_insert {
    public static void main(String[] args) throws IOException {
        String url="jdbc:mysql://localhost:3306/akash";
        String user="root";
        String password="Shitlamaa@8989";
       // String image_path="C:\\akash singh\\hgtyfryryyu\\gurjar\\Pictures\\IMG20200720083603.jpg";
        String folder_path="C:\\akash singh\\hgtyfryryyu\\gurjar\\Pictures\\";
        String query="insert into image_table(image_data) values(?)";
        String query1="select image_data from image_table where image_id=(?)";
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
           /* FileInputStream fileInputStream=new FileInputStream(image_path);
            byte[] imagedata=new byte[fileInputStream.available()];
            fileInputStream.read(imagedata);
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setBytes(1,imagedata);
            int rowaffected=preparedStatement.executeUpdate();
            if(rowaffected>0){
                System.out.println("insertion successfully....");
            }
            else{
                System.out.println("insertion failed....");
            }*/
            PreparedStatement preparedStatement=connection.prepareStatement(query1);
            preparedStatement.setInt(1,1);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data=resultSet.getBytes("image_data");
                String image1_path=folder_path+"extractedImage through java.jpg";
                OutputStream outputStream=new FileOutputStream(image1_path);
                outputStream.write(image_data);
                System.out.println("image inserted successfully");
            }
            else {
                System.out.println("No image data found");
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
