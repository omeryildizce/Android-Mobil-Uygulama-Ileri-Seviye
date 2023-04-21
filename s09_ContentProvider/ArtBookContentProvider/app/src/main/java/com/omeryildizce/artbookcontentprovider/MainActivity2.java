package com.omeryildizce.artbookcontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Button saveButton, deleteButton, updateButton;
    TextView textView;
    Bitmap selectedImage;
    String firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initalize();
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if (info.matches("new")) {
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
            editText.setText("");
            textView.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.GONE);
            updateButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);

        } else {
            textView.setText("");
            saveButton.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            String name = intent.getStringExtra("name");
            firstName = name;
            editText.setText(name);
            int position = intent.getIntExtra("position", 0);
            imageView.setImageBitmap(MainActivity.artImageList.get(position));


        }
        ArtContentProvider.DATABASE_NAME.length();
    }

    private void initalize() {
        imageView = findViewById(R.id.imageViewArt);
        editText = findViewById(R.id.editTextArtName);
        saveButton = findViewById(R.id.buttonSave);
        updateButton = findViewById(R.id.buttonDelete);
        deleteButton = findViewById(R.id.buttonUpdate);
        textView = findViewById(R.id.textView);

    }

    public void saveRecord(View view) {
        String artName = editText.getText().toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] bytes = outputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtContentProvider.NAME, artName);
        contentValues.put(ArtContentProvider.IMAGE, bytes);
        getContentResolver().insert(ArtContentProvider.CONTENT_URI, contentValues);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void updateRecord(View view) {
        String artName = editText.getText().toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] bytes = outputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtContentProvider.NAME, artName);
        contentValues.put(ArtContentProvider.IMAGE, bytes);
        String[] selectionArguments = {firstName};
        getContentResolver().update(ArtContentProvider.CONTENT_URI, contentValues, "name=?", selectionArguments);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void deleteRecord(View view) {
        String recordName = editText.getText().toString();
        String[] selectionArguments = {recordName};
        getContentResolver().delete(ArtContentProvider.CONTENT_URI, "name = ?", selectionArguments);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void selectImage(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri image = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                imageView.setImageBitmap(selectedImage);
                textView.setVisibility(View.GONE);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}