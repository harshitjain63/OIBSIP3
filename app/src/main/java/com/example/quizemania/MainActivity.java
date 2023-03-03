package com.example.quizemania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionTV;
    TextView questionTV;
    Button ansA,ansB,ansC,ansD;
    Button submitbtn;
    int score=0;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTV = findViewById(R.id.total_question);
        questionTV=findViewById(R.id.question);
        ansA=findViewById(R.id.Ans_A);
        ansB=findViewById(R.id.Ans_B);
        ansC=findViewById(R.id.Ans_C);
        ansD=findViewById(R.id.Ans_D);
        submitbtn= findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalQuestionTV.setText("Total Question: "+totalQuestion);

        loadNewQuestions();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

     Button clickedbutton = (Button) view;
     if(clickedbutton.getId()==R.id.submit_btn){
         if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
             score++;
         }
              currentQuestionIndex++;
              loadNewQuestions();

     }else{
         //choice button is clicked
         selectedAnswer=clickedbutton.getText().toString();
         clickedbutton.setBackgroundColor(Color.MAGENTA);// clicked buttton color willl change to magenta
     }

    }

    void loadNewQuestions(){

        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        questionTV.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }
    void finishQuiz(){// here we will show the score and the app will not crash if limit exceeds given question
        String passstatus="";
        if(score>totalQuestion*0.60){
            passstatus="Passed";
        }else {
            passstatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstatus)
                .setMessage("Score is"+score+"out of"+totalQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestions();
    }
}