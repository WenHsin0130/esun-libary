package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DatabaseConnection implements CommandLineRunner {

    @Autowired
    // 使用 Autowired 自動取得 Spring Boot 建立的 DataSource 物件，DataSource 內包含資料庫連線設定 (application.properties)
    private DataSource dataSource;

    public static void main(String[] args) {
        //  啟動 Spring Boot
        SpringApplication.run(DatabaseConnection.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 檢查資料庫連線
        try (Connection conn = dataSource.getConnection()) {
            // 判斷連線物件是否存在且不是關閉狀態
            if (conn != null && !conn.isClosed()) {
                System.out.println("已連接成功");
                queryBooks();  
            } else {
                System.out.println("資料庫連接失敗");
            }
        } catch (Exception e) {
            System.out.println(false);
            e.printStackTrace(); // 印出詳細錯誤訊息
        }
    }

    /**
     * 查詢 book 資料表並印出內容
     */
    private void queryBooks() {
        String sqlQueryBooks = "SELECT * FROM book";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlQueryBooks);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("查詢結果：");
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String name = rs.getString("Name");
                String author = rs.getString("Author");
                String intro = rs.getString("Introduction");

                System.out.printf("isbn=%s | name=%s | author=%s | intro=%s%n", isbn, name, author, intro);
            }

        } catch (Exception e) {
            System.out.println("查詢 book 資料時發生錯誤");
            e.printStackTrace();
        }

    }
}
