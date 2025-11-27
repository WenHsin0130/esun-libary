package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DatabaseConnection implements CommandLineRunner {

    @Autowired
    // 使用 Autowired 自動取得 Spring Boot 建立的 DataSource 物件，DataSource 內包含資料庫連線設定
    // (application.properties)
    private DataSource dataSource;

    public static void main(String[] args) {
        // 啟動 Spring Boot
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
     * return: List<BookData> books
     */
    public List<BookDao> queryBooks() {
        // SQL: 取得所有書籍資訊
        String sqlQueryBooks = "SELECT * FROM book";

        // 建立空的 Lists 用於儲存書籍資訊，格式設定為 Book
        List<BookDao> books = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlQueryBooks);
                ResultSet rs = stmt.executeQuery()) {
                // executeQuery() 用來執行 SELECT 查詢，並回傳查詢結果（ResultSet）
                // ResultSet rs: 資料庫的查詢結果集合，可用 rs.next() 一筆一筆取資料

            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String name = rs.getString("Name");
                String author = rs.getString("Author");
                String intro = rs.getString("Introduction");

                books.add(new BookDao(isbn, name, author, intro));
            }

        } catch (Exception e) {
            System.out.println("查詢 book 資料時發生錯誤");
            e.printStackTrace();
        }

        return books;

    }
}
