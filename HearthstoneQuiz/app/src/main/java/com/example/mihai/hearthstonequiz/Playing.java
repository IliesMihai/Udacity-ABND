package com.example.mihai.hearthstonequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mihai.hearthstonequiz.Common.Common;
import com.squareup.picasso.Picasso;

public class Playing extends AppCompatActivity implements View.OnClickListener {

    private int index=0, score=0, thisQuestion=0, totalQuestion, correctAnswer;

    private ImageView questionImage;
    private Button btnA, btnB, btnC, btnD;
    private TextView txtScore, txtQuestionNr, questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        questionImage = findViewById(R.id.question_image);
        questionText = findViewById(R.id.question_text);
        txtScore = findViewById(R.id.txt_score);
        txtQuestionNr = findViewById(R.id.txt_total_question);

        btnA = findViewById(R.id.btn_answerA);
        btnB = findViewById(R.id.btn_answerB);
        btnC = findViewById(R.id.btn_answerC);
        btnD = findViewById(R.id.btn_answerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (index < totalQuestion) {
            Button clickButton = (Button) view;
            if (clickButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())) {
                score += 10;
                correctAnswer++;
                showQuestion(++index);
            } else {
                Intent intent = new Intent(Playing.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("CORRECT",correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d", score));
        }
    }

    private void showQuestion(int index) {
        if (index < totalQuestion) {
            thisQuestion++;
            txtQuestionNr.setText(String.format("%d / %d", thisQuestion, totalQuestion));


            if (Common.questionList.get(index).getIsImageQuestion().equals("true")) {
                Picasso.with(getBaseContext())
                        .load(Common.questionList.get(index).getQuestion())
                        .into(questionImage);
                questionImage.setVisibility(View.VISIBLE);
                questionText.setVisibility(View.INVISIBLE);
            } else {
                questionText.setText(Common.questionList.get(index).getQuestion());

                questionImage.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.VISIBLE);
            }

            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());
        } else {

            Intent intent = new Intent(Playing.this, Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = Common.questionList.size();
        showQuestion(index);
    }
}
