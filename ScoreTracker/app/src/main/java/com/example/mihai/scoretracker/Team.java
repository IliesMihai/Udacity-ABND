package com.example.mihai.scoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Team extends AppCompatActivity {

    public String firstTeam;
    public String secondTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);


    }

    public void trackButton(View view) {

        EditText firstTeam = findViewById(R.id.first_team);
        this.firstTeam = firstTeam.getText().toString();

        EditText secondTeam = findViewById(R.id.second_team);
        this.secondTeam = secondTeam.getText().toString();

        Intent intent = new Intent(Team.this, Track.class);
        intent.putExtra("FIRST_TEAM", this.firstTeam);
        intent.putExtra("SECOND_TEAM", this.secondTeam);
        startActivity(intent);
    }
}
