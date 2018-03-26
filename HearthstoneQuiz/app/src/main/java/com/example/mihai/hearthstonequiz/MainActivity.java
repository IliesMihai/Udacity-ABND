package com.example.mihai.hearthstonequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mihai.hearthstonequiz.Common.Common;
import com.example.mihai.hearthstonequiz.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private Button startButton;

    private FirebaseDatabase database;
    private DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

        loadQuestion();

        startButton = findViewById(R.id.btn_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Playing.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestion() {

        if (Common.questionList.size() > 0) {
            Common.questionList.clear();
        }

        questions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Question question = postSnapshot.getValue(Question.class);
                    Common.questionList.add(question);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Collections.shuffle(Common.questionList);
    }
}
