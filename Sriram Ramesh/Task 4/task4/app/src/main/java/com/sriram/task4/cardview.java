package com.sriram.task4;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class cardview extends AppCompatActivity {
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        image = findViewById(R.id.imageView);
        text = findViewById(R.id.breed);


    }
}
