package com.example.bookcatalog;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int imageResource;
    private String description;

    public Book(int id, String title, String author, String genre, int imageResource, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.imageResource = imageResource;
        this.description = description;
    }

    // Конструктор для заглушек без ID
    public Book(String title, String author, String genre, int imageResource, String description) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.imageResource = imageResource;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}
