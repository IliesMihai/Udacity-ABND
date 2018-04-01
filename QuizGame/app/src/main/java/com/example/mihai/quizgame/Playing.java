package com.example.mihai.quizgame;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;

public class Playing extends AppCompatActivity {

    ArrayList<String> correctAnswer = new ArrayList<>();

    private int score = 0, questionAnswered = 0;
    private RadioGroup radioGroup1, radioGroup2;
    private RadioButton radioButton1, radioButton2;
    private EditText editText, editText2;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        correctAnswer.add("cards");
        correctAnswer.add("rexxar");
        correctAnswer.add("mammoth");
        correctAnswer.add("jaina");

        radioGroup1 = findViewById(R.id.group1);
        radioGroup2 = findViewById(R.id.group2);
        editText = findViewById(R.id.second_q_answer);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);

        editText2 = findViewById(R.id.last_q_answer);
        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer();

                Intent intent = new Intent(Playing.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("QUESTIONS", questionAnswered);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getAnswer() {

        int radioId1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
        int radioId2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = findViewById(radioId2);

        if (radioId1 != -1) {
            if (correctAnswer.contains(radioButton1.getText().toString().toLowerCase())) {
                score += 25;
                questionAnswered += 1;
            }
        }

        if (correctAnswer.contains(editText.getText().toString().toLowerCase())) {
            score += 15;
            questionAnswered += 1;
        }

        if (radioId2 != -1) {
            if (correctAnswer.contains(radioButton2.getText().toString().toLowerCase())) {
                score += 15;
                questionAnswered += 1;
            }
        }

        if ((checkBox1.isChecked() && checkBox3.isChecked()) && !(checkBox2.isChecked() || checkBox4.isChecked())) {
            score += 25;
            questionAnswered += 1;
        }

        if (correctAnswer.contains(editText2.getText().toString().toLowerCase())) {
            score += 20;
            questionAnswered += 1;
        }
    }
}
