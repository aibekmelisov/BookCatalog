package com.example.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private ImageView bookImage;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookGenre;
    private TextView bookDescription;
    private Button buttonEdit;
    private Button buttonDelete;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        databaseHelper = new DatabaseHelper(this);

        bookImage = findViewById(R.id.detail_book_image);
        bookTitle = findViewById(R.id.detail_book_title);
        bookAuthor = findViewById(R.id.detail_book_author);
        bookGenre = findViewById(R.id.detail_book_genre);
        bookDescription = findViewById(R.id.detail_book_description);
        buttonEdit = findViewById(R.id.button_edit);
        buttonDelete = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        book = new Book(
                intent.getIntExtra("book_id", -1),
                intent.getStringExtra("book_title"),
                intent.getStringExtra("book_author"),
                intent.getStringExtra("book_genre"),
                intent.getIntExtra("book_image", 0),
                intent.getStringExtra("book_description")
        );




        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        bookGenre.setText(book.getGenre());
        bookImage.setImageResource(book.getImageResource());
        bookDescription.setText(book.getDescription());

        buttonEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(BookDetailActivity.this, EditBookActivity.class);
            editIntent.putExtra("book_id", book.getId());
            editIntent.putExtra("book_title", book.getTitle());
            editIntent.putExtra("book_author", book.getAuthor());
            editIntent.putExtra("book_genre", book.getGenre());
            editIntent.putExtra("book_image", book.getImageResource());
            editIntent.putExtra("book_description", book.getDescription());
            startActivityForResult(editIntent, 1);
        });

        buttonDelete.setOnClickListener(v -> {
            databaseHelper.deleteBook(book.getId());
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем активность после удаления
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Book updatedBook = new Book(
                    data.getIntExtra("book_id", -1),
                    data.getStringExtra("book_title"),
                    data.getStringExtra("book_author"),
                    data.getStringExtra("book_genre"),
                    data.getIntExtra("book_image", 0),
                    data.getStringExtra("book_description")
            );
            databaseHelper.updateBook(updatedBook);
            bookTitle.setText(updatedBook.getTitle());
            bookAuthor.setText(updatedBook.getAuthor());
            bookGenre.setText(updatedBook.getGenre());
            bookDescription.setText(updatedBook.getDescription());
        }
    }
}
