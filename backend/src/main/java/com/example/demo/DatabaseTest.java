package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class DatabaseTest implements CommandLineRunner {

    @Autowired
    // Spring Boot 建立的 DataSource 物件，DataSource 內包含資料庫連線設定
    private DataSource dataSource;

    public static void main(String[] args) {
        //  啟動 Spring Boot
        SpringApplication.run(DatabaseTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            // 判斷連線物件是否存在且不是關閉狀態
            if (conn != null && !conn.isClosed()) {
                System.out.println("已連接成功");
            } else {
                System.out.println("資料庫連接失敗");
            }
        } catch (Exception e) {
            System.out.println(false);
            e.printStackTrace(); // 印出詳細錯誤訊息
        }
    }
}
