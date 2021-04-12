package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputName;

    public void startQuiz(View view){
        if(inputName.getText().toString() == null) {
            Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, QuestionScreen.class);
            intent.putExtra("name", inputName.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        inputName.setText(name);
    }
}