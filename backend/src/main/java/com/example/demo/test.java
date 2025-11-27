package com.example.demo;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sqlStatement = "SELECT * FROM book";
        Connection conn = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/esun_library", "root", "");
        if(conn != null){
            System.out.println("成功連線");
        } else {
            System.out.println("連線失敗");
        }

        PreparedStatement ps = conn.prepareStatement(sqlStatement);
        ResultSet rs = ps.executeQuery();
    }

    
}
