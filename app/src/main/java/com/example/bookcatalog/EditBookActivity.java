package com.example.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity extends AppCompatActivity {

    private EditText editBookTitle, editBookAuthor, editBookGenre, editBookDescription;
    private Button buttonUpdate;
    private ImageView editBookImage;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editBookTitle = findViewById(R.id.edit_book_title);
        editBookAuthor = findViewById(R.id.edit_book_author);
        editBookGenre = findViewById(R.id.edit_book_genre);
        editBookDescription = findViewById(R.id.edit_book_description);
        buttonUpdate = findViewById(R.id.button_update);
        editBookImage = findViewById(R.id.edit_book_image);

        Intent intent = getIntent();
        int bookId = intent.getIntExtra("book_id", -1);
        editBookTitle.setText(intent.getStringExtra("book_title"));
        editBookAuthor.setText(intent.getStringExtra("book_author"));
        editBookGenre.setText(intent.getStringExtra("book_genre"));
        editBookDescription.setText(intent.getStringExtra("book_description"));
        editBookImage.setImageResource(intent.getIntExtra("book_image", R.drawable.unknown));

        databaseHelper = new DatabaseHelper(this);

        buttonUpdate.setOnClickListener(v -> {
            Book updatedBook = new Book(
                    bookId,
                    editBookTitle.getText().toString(),
                    editBookAuthor.getText().toString(),
                    editBookGenre.getText().toString(),
                    R.drawable.unknown,
                    editBookDescription.getText().toString()
            );

            if (databaseHelper.updateBook(updatedBook)) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
