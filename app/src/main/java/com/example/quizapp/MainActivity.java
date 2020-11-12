package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText questionInput;
    Switch answerInput;
    Button addQuestion;
    Button startQuiz;

    ArrayList<Question> questionList = new ArrayList<>();
    boolean questionAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionInput = (EditText) findViewById(R.id.question_input);
        answerInput = (Switch) findViewById(R.id.answer_input);
        addQuestion = (Button) findViewById(R.id.button_add_question);
        startQuiz = (Button) findViewById(R.id.button_start_quiz);

        answerInput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    questionAnswer = true;
                } else {
                    questionAnswer = false;
                }
            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                questionList.add(new Question(questionInput.getText().toString(), questionAnswer));

                showMessage();

                questionInput.setText("");
                questionInput.setHint("Skriv fråga");
                answerInput.setChecked(false);
            }
        });

        startQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                i.putParcelableArrayListExtra("questionList", questionList);

                startActivity(i);
                finish();
            }
        });
    }

    private void showMessage() {
        Toast myToast = Toast.makeText(MainActivity.this, "Fråga tillagd", Toast.LENGTH_SHORT);
        myToast.show();
    }
}