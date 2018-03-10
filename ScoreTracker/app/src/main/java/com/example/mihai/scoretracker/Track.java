package com.example.mihai.scoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Track extends AppCompatActivity {

    private int scoreTeamA;
    private int scoreTeamB;
    private int foulsTeamA;
    private int foulsTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        TextView FirstTeamName = findViewById(R.id.first_team_name);
        FirstTeamName.setText(getIntent().getStringExtra("FIRST_TEAM"));

        TextView SecondTeamName = findViewById(R.id.second_team_name);
        SecondTeamName.setText(getIntent().getStringExtra("SECOND_TEAM"));
    }


    public void displayTeamAScore(int score) {
        TextView textView = findViewById(R.id.team_a_score);
        textView.setText(String.valueOf(score));
    }

    public void displayTeamAFouls(int foul) {
        TextView textView = findViewById(R.id.team_a_fouls);
        textView.setText(String.valueOf(foul));
    }

    public void addThreePointsTeamA(View view) {
        scoreTeamA += 3;
        displayTeamAScore(scoreTeamA);
    }

    public void addTwoPointsTeamA(View view) {
        scoreTeamA += 2;
        displayTeamAScore(scoreTeamA);
    }

    public void addOnePointTeamA(View view) {
        scoreTeamA += 1;
        displayTeamAScore(scoreTeamA);
    }

    public void foulTeamA(View view) {
        foulsTeamA += 1;
        displayTeamAFouls(foulsTeamA);
    }

    private void displayTeamBScore(int score) {
        TextView textView = findViewById(R.id.team_b_score);
        textView.setText(String.valueOf(score));
    }

    private void displayTeamBFouls(int foul) {
        TextView textView = findViewById(R.id.team_b_fouls);
        textView.setText(String.valueOf(foul));
    }

    public void addThreePointsTeamB(View view) {
        scoreTeamB += 3;
        displayTeamBScore(scoreTeamB);
    }

    public void addTwoPointsTeamB(View view) {
        scoreTeamB += 2;
        displayTeamBScore(scoreTeamB);
    }

    public void addOnePointTeamB(View view) {
        scoreTeamB += 1;
        displayTeamBScore(scoreTeamB);
    }

    public void foulTeamB(View view) {
        foulsTeamB += 1;
        displayTeamBFouls(foulsTeamB);
    }

    public void resetScore(View view) {
        scoreTeamA = 0;
        displayTeamAScore(scoreTeamA);
        scoreTeamB = 0;
        displayTeamBScore(scoreTeamB);
        foulsTeamA = 0;
        displayTeamAFouls(foulsTeamA);
        foulsTeamB = 0;
        displayTeamBFouls(foulsTeamB);

        Intent intent = new Intent(Track.this, MainActivity.class);
        startActivity(intent);
    }

}
