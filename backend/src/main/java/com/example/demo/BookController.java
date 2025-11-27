package com.example.demo;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 宣告 BookData 的 REST API 控制器，回傳的資料會自動轉成 JSON
 */
@RestController
public class BookController {

    // 宣告資料庫服務物件（DatabaseConnection）
    private final DatabaseConnection db;

    public BookController(DatabaseConnection db) {
        this.db = db;
    }

    @GetMapping("/api/books")
    // 當使用者在瀏覽器輸入 http://localhost:8080/api/books 時執行以下內容
    public List<BookDao> getBooks() {
        System.out.print(db.queryBooks());
        return db.queryBooks();
    }
}

