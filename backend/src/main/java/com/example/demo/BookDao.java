package com.example.demo;

public class BookDao {
    // Book Model (設定資料型態，提供資料取得的時候使用)
    private String ISBN;
    private String name;
    private String author;
    private String introduction;

    public BookDao(String ISBN, String name, String author, String introduction) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.introduction = introduction;
    }

    // Getter（JSON 自動使用）
    public String getISBN() { return ISBN; }
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public String getIntroduction() { return introduction; }
}
