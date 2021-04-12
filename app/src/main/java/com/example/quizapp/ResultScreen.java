package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultScreen extends AppCompatActivity {

    TextView txtName, txtScore;
    Button btnRestart, btnFinish;
    String name;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        score = intent.getStringExtra("score");
        txtName = findViewById(R.id.txtName);
        txtName.setText("Congratulations " + name);

        txtScore = findViewById(R.id.txtScore);
        txtScore.setText(score+"/5");

        // Buttons
        btnRestart = findViewById(R.id.btnRestart);
        btnFinish = findViewById(R.id.btnFinish);
    }

    public void exitApp(View view){
        this.finishAffinity();
    }

    public void startNew(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}