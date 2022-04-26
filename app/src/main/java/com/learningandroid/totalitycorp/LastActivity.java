package com.learningandroid.totalitycorp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class LastActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        imageView = findViewById(R.id.imageView);

        Uri imageUri = (Uri) getIntent().getExtras().get("RESULTuri");
        imageView.setImageURI(imageUri);
    }
}