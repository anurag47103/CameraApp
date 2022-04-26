package com.learningandroid.totalitycorp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.learningandroid.totalitycorp.databinding.ActivityHomeAcitivityBinding;

import java.security.Permissions;

public class HomeAcitivity extends AppCompatActivity {

    private static final int GALLERY_CODE = 101;
    private Uri imageUri;
    ActivityResultLauncher<String> mGetContent;

    ActivityHomeAcitivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeAcitivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.takeSelfieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeAcitivity.this, CameraAcitivity.class));
                finish();
            }
        });

        binding.uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mGetContent.launch("image/*");
            }
        });
        
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.backgroundImageView.setImageURI(result);
                binding.addImageView.setVisibility(View.INVISIBLE);
                binding.backgroundImageView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                Intent newIntent = new Intent(HomeAcitivity.this, EditActivity.class);
                newIntent.putExtra("uri",result);
                startActivity(newIntent);
                finish();
            }
        });

        if(getIntent().getExtras()!=null) {
            Uri receivedUri = (Uri) getIntent().getExtras().get("RESULTuri");
            binding.backgroundImageView.setImageURI(receivedUri);
            binding.backgroundImageView.setBackgroundColor(getColor(android.R.color.transparent));
            binding.addImageView.setVisibility(View.INVISIBLE);
        }

    }


}