package com.example.mihai.hearthstonequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Done extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtTotalScore, txtQuestionsAnswered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        btnTryAgain = findViewById(R.id.btn_try_again);
        txtTotalScore = findViewById(R.id.txt_total_score);
        txtQuestionsAnswered = findViewById(R.id.txt_questions_answered);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();

        if(extra != null) {
            int score = extra.getInt("SCORE");
            int questionsNr = extra.getInt("TOTAL");
            int correctAnswered = extra.getInt("CORRECT");

            txtTotalScore.setText(String.format("SCORE : %d" , score));
            txtQuestionsAnswered.setText(String.format("PASSED : %d / %d",correctAnswered, questionsNr));
        }
    }
}
