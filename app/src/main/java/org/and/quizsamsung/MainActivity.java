package org.and.quizsamsung;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    MediaPlayer  mediaPlayerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayerOptions = MediaPlayer.create(this, R.raw.sound_options);
Window w = getWindow();
w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
Button butstart = (Button) findViewById(R.id.butstart);
View.OnClickListener oclbutstart = new View.OnClickListener(){
    @Override
    public void onClick(View v){
       Intent intent = new Intent(MainActivity.this,Rules.class) ;
       startActivity(intent);
        mediaPlayerOptions.start();
    }
};
butstart.setOnClickListener(oclbutstart);
    }



}