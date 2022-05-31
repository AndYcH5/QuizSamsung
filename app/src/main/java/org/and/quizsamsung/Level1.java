package org.and.quizsamsung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Level1 extends AppCompatActivity {
    MediaPlayer mediaPlayerSound_Fail_answer, mediaPlayerOptions, mediaPlayerSound_True_answer, mediaPlayerSound_Hint;

    private TextView textlvl1, hint;

    private AppCompatButton lvl1a1, lvl1a2, lvl1a3, btnNext;
    private AppCompatButton lvl1h;

    private Timer lvlTimer;

    private int second = 0;
    private int minute = 1;

    private int count = 0;
    private int point = 0;

    public ProgressBar pb;
    private List<LevelList> levelList;

    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        final TextView timer = findViewById(R.id.timer);


        mediaPlayerSound_Fail_answer = MediaPlayer.create(this, R.raw.sound_fail_answer);
        mediaPlayerOptions = MediaPlayer.create(this, R.raw.sound_options);
        mediaPlayerSound_True_answer = MediaPlayer.create(this, R.raw.sound_true_answer);
        mediaPlayerSound_Hint = MediaPlayer.create(this, R.raw.sound_hint);
        textlvl1 = findViewById(R.id.textlvl1);

        lvl1a1 = findViewById(R.id.lvl1a1);
        lvl1a2 = findViewById(R.id.lvl1a2);
        lvl1a3 = findViewById(R.id.lvl1a3);
        lvl1h = findViewById(R.id.lvl1h);
        hint = findViewById(R.id.hint);
        btnNext = findViewById(R.id.btnNext);


        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");

        levelList = QuestionsBank.getQuestions(getSelectedTopic);
        new CountDownTimer(300000,1000){




            @Override
            public void onTick(long millisUntilFinished) {
                ObjectAnimator animator = ObjectAnimator.ofInt(pb,"progress", 0, 100);
timer.setText ("Осталось - "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish(){
                Toast.makeText(Level1.this, "Время вышло!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Level1.this, Results.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    startActivity(intent);
                    finish();
           }
        }.start();
        //startTimer(timer);
        textlvl1.setText(levelList.get(0).getTextView1());
        lvl1a1.setText(levelList.get(0).getLvl1a1());
        lvl1a2.setText(levelList.get(0).getLvl1a2());
        lvl1a3.setText(levelList.get(0).getLvl1a3());
        lvl1h.setText(levelList.get(0).getLvl1h());

        lvl1a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = lvl1a1.getText().toString();
                    lvl1a1.setBackgroundResource(R.drawable.round_back_red20);
                    lvl1a1.setTextColor(Color.WHITE);

                    revealAnswer();
                    levelList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                    soundForAnswerOption(selectedOptionByUser);
                }
            }
        });
        lvl1a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = lvl1a2.getText().toString();
                    lvl1a2.setBackgroundResource(R.drawable.round_back_red20);
                    lvl1a2.setTextColor(Color.WHITE);

                    revealAnswer();
                    levelList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                    soundForAnswerOption(selectedOptionByUser);
                }
            }
        });
        lvl1a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = lvl1a3.getText().toString();
                    lvl1a3.setBackgroundResource(R.drawable.round_back_red20);
                    lvl1a3.setTextColor(Color.WHITE);

                    revealAnswer();
                    levelList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                    soundForAnswerOption(selectedOptionByUser);
                }
            }
        });
        lvl1h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayerSound_Hint.start();
                TextView hint = findViewById(R.id.hint);
                hint.setText(levelList.get(0).getHint());
                hint.setText(levelList.get(currentQuestionPosition).getHint());
                count++;
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(Level1.this, "Пожалуйста, сделайте выбор", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayerOptions.start();
                    changeNextQuestion();
                    hint.setText("");
                }
            }
        });

    }


//    private void startTimer(TextView timerTextView) {
//        lvlTimer = new Timer();
//        lvlTimer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if ((second == 0) && (minute!= 0)) {
//                    minute--;
//                    second = 59;
//                } else if ((minute == 0) && (second == 0)) {
//                    lvlTimer.purge();
//                    lvlTimer.cancel();
//
//                    Toast.makeText(Level1.this, "Время вышло!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Level1.this, Results.class);
//                    intent.putExtra("correct", getCorrectAnswers());
//                    intent.putExtra("correct", getInCorrectAnswers());
//
//
//                    startActivity(intent);
//                    finish();
//                } else {
//                    second--;
//
//
//                }
//                runOnUiThread(new Runnable() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void run() {
//
//                        String finalMinutes = String.valueOf(minute);
//                        String finalSecond = String.valueOf(second);
//
//                        if (finalMinutes.length() == 1) {
//                            finalMinutes = "0" + finalMinutes;
//                        }
//                        if (finalSecond.length() == 1) {
//                           finalSecond = "0" + finalSecond;
//                        }
//                        timerTextView.setText(finalMinutes + ":" + finalSecond);
//
//
//                    }
//                });
//            }
//        }, 1000, 1000);
//    }

    private int getCorrectAnswers() {
        int correctAnswers = 0;
        for (int i = 0; i < levelList.size(); i++) {
            final String getUserSelectedAnswer = levelList.get(i).getUserSelectedAnswer();
            final String getAnswer = levelList.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;
            }
        }
        return correctAnswers * 2 - count;
    }



    private void revealAnswer() {
        final String getAnswer = levelList.get(currentQuestionPosition).getAnswer();
        if (lvl1a1.getText().toString().equals(getAnswer)) {
            lvl1a1.setBackgroundResource(R.drawable.round_back_green20);
            lvl1a1.setTextColor(Color.WHITE);

        } else if (lvl1a2.getText().toString().equals(getAnswer)) {
            lvl1a2.setBackgroundResource(R.drawable.round_back_green20);
            lvl1a2.setTextColor(Color.WHITE);

        } else if (lvl1a3.getText().toString().equals(getAnswer)) {
            lvl1a3.setBackgroundResource(R.drawable.round_back_green20);
            lvl1a3.setTextColor(Color.WHITE);

        }
    }

    private void changeNextQuestion() {
        currentQuestionPosition++;
        if ((currentQuestionPosition + 1) == levelList.size()) {
            btnNext.setText("Узнать результат");
        }
        if (currentQuestionPosition < levelList.size()) {
            selectedOptionByUser = "";
            lvl1a1.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
            lvl1a1.setTextColor(Color.parseColor("#1F6BB8"));
            lvl1a2.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
            lvl1a2.setTextColor(Color.parseColor("#1F6BB8"));
            lvl1a3.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
            lvl1a3.setTextColor(Color.parseColor("#1F6BB8"));

            textlvl1.setText(levelList.get(currentQuestionPosition).getTextView1());
            lvl1a1.setText(levelList.get(currentQuestionPosition).getLvl1a1());
            lvl1a2.setText(levelList.get(currentQuestionPosition).getLvl1a2());
            lvl1a3.setText(levelList.get(currentQuestionPosition).getLvl1a3());
            lvl1h.setText(levelList.get(currentQuestionPosition).getLvl1h());


        } else {
            Intent intent = new Intent(Level1.this, Results.class);
            intent.putExtra("correct", getCorrectAnswers());
            startActivity(intent);
            finish();
        }
    }

    private void soundForAnswerOption(String selectedOptionByUser) {
        final String getAnswer = levelList.get(currentQuestionPosition).getAnswer();

        if (selectedOptionByUser.equals(getAnswer)) {
mediaPlayerSound_True_answer.start();
        }
        else{
            mediaPlayerSound_Fail_answer.start();
        }
    }
}
