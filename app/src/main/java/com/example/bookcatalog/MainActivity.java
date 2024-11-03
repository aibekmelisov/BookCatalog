package com.example.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private List<Book> books;
    private BookAdapter bookAdapter;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        books = databaseHelper.getAllBooks();

        bookAdapter = new BookAdapter(this, books, book -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("book_id", book.getId());
            intent.putExtra("book_title", book.getTitle());
            intent.putExtra("book_author", book.getAuthor());
            intent.putExtra("book_genre", book.getGenre());
            intent.putExtra("book_image", book.getImageResource());
            intent.putExtra("book_description", book.getDescription());
            startActivity(intent);
        });

        recyclerView.setAdapter(bookAdapter);

        buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        books.clear();
        books.addAll(databaseHelper.getAllBooks());
        bookAdapter.notifyDataSetChanged();
    }
}
