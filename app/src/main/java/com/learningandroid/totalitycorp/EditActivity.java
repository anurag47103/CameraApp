package com.learningandroid.totalitycorp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.learningandroid.totalitycorp.databinding.ActivityEditBinding;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.UUID;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().getExtras() != null) {
            imageUri = (Uri) getIntent().getExtras().get("uri");
            binding.backgroundImageView.setImageURI(imageUri);
            binding.backgroundImageView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }

        String dest_uri = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();

        UCrop.Options options = new UCrop.Options();
        options.setBrightnessEnabled(false);
        options.setContrastEnabled(false);
        options.setSaturationEnabled(false);
        options.setSharpnessEnabled(false);


        UCrop.of(imageUri, Uri.fromFile(new File(getCacheDir(), dest_uri)))
                .withOptions(options)
                .withAspectRatio(0,0)
                .useSourceImageAspectRatio()
                .withMaxResultSize(2000, 2000)
                .start(EditActivity.this);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Intent returnIntent = new Intent(EditActivity.this, HomeAcitivity.class);
            returnIntent.putExtra("RESULTuri", resultUri);
//            setResult(-1, returnIntent);
            startActivity(returnIntent);
            finish();
        }
        else if(resultCode== UCrop.RESULT_ERROR){
            final Throwable cropError = UCrop.getError(data);
        }
    }
}