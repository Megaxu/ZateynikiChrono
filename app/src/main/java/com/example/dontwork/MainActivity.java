package com.example.dontwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String REGEX_NAME = "[0-9]+";
    public static TextView informTextOne;
    public static TextView informTextTwo;
    public static EditText inputTextOne;
    public static EditText inputTextTwo;
    public static Button enterButtomOne;
    public static Button enterButtomTwo;
    public static ImageButton startButton;
    public static Chronometer chronometerOne;
    public static Chronometer chronometerTwo;
    public static Button finishButtonOne;
    public static Button finishButtonTwo;
    public static Button childrenListButton;


    public String inputOne = "";
    public String inputTwo = "";
    public static int numberOne;
    public static int numberTwo;
    public static long startMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informTextOne = findViewById(R.id.testingOne);
        informTextTwo = findViewById(R.id.testingTwo);
        inputTextOne = findViewById(R.id.enterTextOne);
        inputTextTwo = findViewById(R.id.enterTextTwo);
        enterButtomOne = findViewById(R.id.testingButtonOne);
        enterButtomTwo = findViewById(R.id.testingButtonTwo);
        startButton = findViewById(R.id.start);
        childrenListButton = findViewById(R.id.childrenList);
        finishButtonOne = findViewById(R.id.finishOne);
        finishButtonTwo = findViewById(R.id.finishTwo);
        chronometerOne = findViewById(R.id.chronoOne);
        chronometerTwo = findViewById(R.id.chronoTwo);

        enterButtomOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputOne = inputTextOne.getText().toString();
                if (inputOne.matches(REGEX_NAME)) {
                    numberOne = Integer.parseInt(inputOne);
                    informTextOne.setText("Участник " + numberOne);
                } else {
                    informTextOne.setText("Неправильно введен номер, повторите ввод");
                }
                inputTextOne.setText("");
            }
        });

        enterButtomTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTwo = inputTextTwo.getText().toString();
                if (inputTwo.matches(REGEX_NAME)) {
                    numberTwo = Integer.parseInt(inputTwo);
                    informTextTwo.setText("Участник " + numberTwo);
                } else {
                    informTextTwo.setText("Неправильно введен номер, повторите ввод");
                }
                inputTextTwo.setText("");
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date start = new Date();
                startMC = start.getTime();
                chronometerOne.setBase(SystemClock.elapsedRealtime());
                chronometerTwo.setBase(SystemClock.elapsedRealtime());
                chronometerOne.start();
                chronometerTwo.start();
            }
        });

        finishButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date finish = new Date();
                long finishOne = finish.getTime();
                Date personalTime = new Date();
                personalTime.setTime(finishOne - startMC);
                chronometerOne.stop();
                SampleClass.addChildrenOne(numberOne, personalTime);
            }
        });

        finishButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date finish = new Date();
                long finishTwo = finish.getTime();
                Date personalTime = new Date();
                personalTime.setTime(finishTwo - startMC);
                chronometerTwo.stop();
                SampleClass.addChildrenTwo(numberTwo, personalTime);
            }
        });

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SampleClass.class);
        intent.putExtra("user", SampleClass.listChildren());
        startActivity(intent);
    }

}