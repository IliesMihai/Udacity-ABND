package com.example.mihai.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Done extends AppCompatActivity {

    private TextView scoreTxt, questionsTxt;
    private Button tryAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        scoreTxt = findViewById(R.id.score_txt);
        questionsTxt = findViewById(R.id.question_txt);
        tryAgainBtn = findViewById(R.id.try_again_btn);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int score = extra.getInt("SCORE");
            int questionAnswerd = extra.getInt("QUESTIONS");

            Toast.makeText(Done.this, String.format("Your score is : %d",score), Toast.LENGTH_SHORT).show();

            scoreTxt.setText(String.format("SCORE : %d",score));
            questionsTxt.setText(String.format("CORRECT ANSWERS : %d",questionAnswerd));
        }

        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
