package org.and.quizsamsung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    MediaPlayer mediaPlayerSound_res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        final AppCompatButton menu = findViewById(R.id.menu);
        final TextView correctA = findViewById(R.id.correctA);

        final int getCorrectAnswers = getIntent().getIntExtra("correct",0);

        correctA.setText(String.valueOf(getCorrectAnswers));
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Results.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Results.this, MainActivity.class));
        finish();
    }
}