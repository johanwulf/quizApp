package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Question> questionList;
    private int qIndex = 0;

    TextView questionText;
    Button trueButton;
    Button falseButton;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionList = this.getIntent().getParcelableArrayListExtra("questionList");
        Collections.shuffle(questionList);

        questionText = (TextView) findViewById(R.id.textView_question);
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);

        questionText.setText(questionList.get(qIndex).getQuestion());

        trueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(questionList.get(qIndex).checkAnswer(true)) {
                    score++;
                }

                updateQuestion(qIndex);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(questionList.get(qIndex).checkAnswer(false)) {
                    score++;
                }

                updateQuestion(qIndex);
            }
        });
    }


    private void updateQuestion(int num) {
        qIndex++;
        if(qIndex > questionList.size()-1) {
            gameOver();
        } else {
            questionText.setText(questionList.get(qIndex).getQuestion());
        }
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder
                .setMessage("Du fick " + score + " av " + questionList.size() + " rätt")
                .setCancelable(false)
                .setPositiveButton("Starta om",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton("Stäng quiz",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
        alertDialogBuilder.show();
    }
}