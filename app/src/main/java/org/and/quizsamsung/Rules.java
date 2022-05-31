package org.and.quizsamsung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Rules extends AppCompatActivity {
    MediaPlayer mediaPlayerFalse, mediaPlayerOptions;
    private String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mediaPlayerFalse = MediaPlayer.create(this, R.raw.sound_false);
        mediaPlayerOptions = MediaPlayer.create(this, R.raw.sound_options);

        final LinearLayout linearL = findViewById(R.id.linearL);
        final Button butPlay = findViewById(R.id.butPlay);
        linearL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "linearL";
                linearL.setBackgroundResource(R.drawable.round_back_white_stroke10);
                mediaPlayerOptions.start();
            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTopic.isEmpty()) {
                    Toast.makeText(Rules.this, "Нажмите на поле", Toast.LENGTH_SHORT).show();

                } else {
                    mediaPlayerOptions.start();
                    Intent intent = new Intent(Rules.this, Level1.class);
                    intent.putExtra("selectedTopic", selectedTopic);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}