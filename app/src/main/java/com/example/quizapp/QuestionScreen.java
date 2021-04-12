package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionScreen extends AppCompatActivity {

    Button btnAns1, btnAns2, btnAns3, btnSubmit;

    TextView txtQuestion, lblQuestionTitle, lblDisplayName, txtCurrentScore;

    ProgressBar progressBar;
    int progress = 1;

    int correctAnswers = 0;
    int question = 1;
    boolean questionAnswered = false;
    String name;

    Button correctButton, selectedButton;

    public Button getCorrectButton() {
        return correctButton;
    }

    public Button getSelectedButton() {
        return selectedButton;
    }

    public void setCorrectButton(Button button) {
        correctButton = button;
    }

    public void setSelectedButton(Button button) {
        selectedButton = button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        // Buttons
        btnAns1 = findViewById(R.id.btnAns1);
        btnAns2 = findViewById(R.id.btnAns2);
        btnAns3 = findViewById(R.id.btnAns3);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Title
        lblQuestionTitle = findViewById(R.id.lblQuestionTitle);
        txtCurrentScore = findViewById(R.id.txtCurrentScore);
        txtCurrentScore.setText(question + "/5");

        // Question
        txtQuestion = findViewById(R.id.txtQuestion);

        // Progress Bar
        progressBar = findViewById(R.id.progressBar);

        //Display Name
        lblDisplayName = findViewById(R.id.lblDisplayName);
        lblDisplayName.setText("Welcome " + name);


        startQuestions();
    }

    private void setAllWhite() { // Sets all button backgrounds to white
        btnAns1.setBackgroundColor(Color.WHITE);
        btnAns2.setBackgroundColor(Color.WHITE);
        btnAns3.setBackgroundColor(Color.WHITE);
    }

    public void btn1Clicked(View view) {
        setAllWhite();
        btnAns1.setBackgroundColor(Color.GRAY);
        setSelectedButton(btnAns1);
    }

    public void btn2Clicked(View view) {
        setAllWhite();
        btnAns2.setBackgroundColor(Color.GRAY);
        setSelectedButton(btnAns2);
    }

    public void btn3Clicked(View view) {
        setAllWhite();
        btnAns3.setBackgroundColor(Color.GRAY);
        setSelectedButton(btnAns3);
    }

    private void resetButtons() {
        btnSubmit.setText("Submit");
        setAllWhite();
        enableButtons(true);
        questionAnswered = false;
    }

    public void submitQuestion(View view) {
        if (getSelectedButton() == null) { // if an answer is not selected
            Toast.makeText(getApplicationContext(), "Please Select an answer...", Toast.LENGTH_SHORT).show();
        }else if (questionAnswered){
            startQuestions();
        }
        else{
            if (getCorrectButton() == getSelectedButton()) {
                // Correct answer
                correctAnswers++;
            } else {
                getSelectedButton().setBackgroundColor(Color.RED);
            }
            getCorrectButton().setBackgroundColor(Color.GREEN);
            btnSubmit.setText("Next");
            questionAnswered = true;
            question++;
            progress++;
            progressBar.setProgress(progress);
            txtCurrentScore.setText(question + "/5");
        }
    }

    private void changeButtonText(String btn1, String btn2, String btn3) {
        btnAns1.setText(btn1);
        btnAns2.setText(btn2);
        btnAns3.setText(btn3);
    }

    private void enableButtons(Boolean bool) { // False = button is disabled.
        btnAns1.setEnabled(bool);
        btnAns2.setEnabled(bool);
        btnAns3.setEnabled(bool);
    }

    private void changeQuestion(String question) {
        txtQuestion.setText(question);
    }

    public void newQuestion(String title, String question, String ans1, String ans2, String ans3, Button Correctbutton) {
        lblQuestionTitle.setText(title);
        changeQuestion(question);
        changeButtonText(ans1, ans2, ans3);
        setCorrectButton(Correctbutton);
    }

    private void question1() {
        resetButtons(); // Resets all the buttons from the last question
        newQuestion("Toasts in Android", "Which is the correct way to create a toast?", "Toast.makeText(MainActivity.this, Hello, Toast.LENGTH_LONG).Show()", "Toast.newToast(MainActivity.this, Hello, Toast.LENGTH_LONG).Show()", "Toast toast = new toast(Hello)", btnAns1);
    }

    private void question2() {
        resetButtons(); // Resets all the buttons from the last question
        lblDisplayName.setText("");
        newQuestion("Activity Lifecycle States", "Which one of these are NOT apart of the activity lifecycle?", "Fully Hidden", "Semi-Hidden", "Destroyed", btnAns2);
    }

    private void question3() {
        resetButtons(); // Resets all the buttons from the last question
        newQuestion("Intent", "Which one of these is what Intent is used for?", "Faster Speeds", "Better Accessibility", "Requesting Information", btnAns3);
    }

    private void question4() {
        resetButtons(); // Resets all the buttons from the last question
        newQuestion("Back Stack", "What does the term 'Back Stack' mean?", "When you fall backwards", "The use of memory handling and the monitoring of variables", "A list of activities frozen in their current sate in the order they where opened", btnAns3);
    }

    private void question5() {
        resetButtons(); // Resets all the buttons from the last question
        newQuestion("Stopping & Restarting an activity", "When an activity is fully-obstructed and not visible, the system calls...", "onStop()", "onPause()", "onRestart()", btnAns1);
    }

    private void startQuestions() {
        switch(question){
            case 1:
                question1();
                break;
            case 2:
                question2();
                break;
            case 3:
                question3();
                break;
            case 4:
                question4();
                break;
            case 5:
                question5();
                break;

            default:
                Intent intent = new Intent(this, ResultScreen.class);
                intent.putExtra("name", name);
                intent.putExtra("score", Integer.toString(correctAnswers));
                startActivity(intent);
        }
    }
}