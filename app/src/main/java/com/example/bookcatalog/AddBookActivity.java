package com.example.bookcatalog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText editBookTitle, editBookAuthor, editBookGenre, editBookDescription;
    private Button buttonAdd;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_xml);

        editBookTitle = findViewById(R.id.edit_book_title);
        editBookAuthor = findViewById(R.id.edit_book_author);
        editBookGenre = findViewById(R.id.edit_book_genre);
        editBookDescription = findViewById(R.id.edit_book_description);
        buttonAdd = findViewById(R.id.button_add);

        databaseHelper = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(v -> {
            String title = editBookTitle.getText().toString().trim();
            String author = editBookAuthor.getText().toString().trim();
            String genre = editBookGenre.getText().toString().trim();
            String description = editBookDescription.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || description.isEmpty()) {
                Toast.makeText(AddBookActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            Book newBook = new Book(title, author, genre, R.drawable.unknown, description);
            long result = databaseHelper.addBook(newBook);

            if (result != -1) {
                Toast.makeText(AddBookActivity.this, "Книга добавлена", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddBookActivity.this, "Ошибка при добавлении книги", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
