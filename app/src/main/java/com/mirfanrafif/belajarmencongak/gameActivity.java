package com.mirfanrafif.belajarmencongak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class gameActivity extends AppCompatActivity {

    private TextView angka1Text;
    private TextView angka2Text;
    private TextView operatorText;
    private EditText answerText;
    private TextView timerText;
    private TextView skorText;
    private CountDownTimer timer;
    private Button submitButton;
    private int skor;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        angka1Text = (TextView) findViewById(R.id.angka1Text);
        angka2Text = (TextView) findViewById(R.id.angka2Text);
        operatorText = (TextView) findViewById(R.id.operatorText);
        answerText = (EditText) findViewById(R.id.answerText);
        timerText = (TextView) findViewById(R.id.timerText);
        skorText = (TextView) findViewById(R.id.scoreText);
        submitButton = (Button) findViewById(R.id.submitButton);

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(gameActivity.this, resultActivity.class);
                intent.putExtra("EXTRA_SKOR", skor);
                startActivity(intent);
                finish();
            }
        };

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Cara Bermain").setMessage(R.string.alertMessage);

        builder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mulai();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });
    }

    private void mulai() {
        timer.start();
        skor = 0;
        skorText.setText(String.valueOf(skor));
        getSoal();
    }

    private void submitAnswer() {
        if(checkAnswer()) {
            getSoal();
            skor += 1;
            skorText.setText(String.valueOf(skor));
            answerText.setText("");
        }else{
            Toast.makeText(this, "Jawaban anda salah. Coba Lagi", Toast.LENGTH_SHORT).show();
            answerText.setText("");
        }
    }

    private boolean checkAnswer() {
        int angka1 = Integer.parseInt(angka1Text.getText().toString());
        int angka2 = Integer.parseInt(angka2Text.getText().toString());
        int jawaban;
        if (answerText.getText().toString().equals("")) {
            jawaban = 0;
        }else {
            jawaban = Integer.parseInt(answerText.getText().toString());
        }
        String operator = operatorText.getText().toString();
        switch (operator) {
            case "+":
                return angka1 + angka2 == jawaban;
            case "x":
                return angka1 * angka2 == jawaban;
            default:
                return false;
        }
    }

    private void getSoal() {
        String [] operatorList = {"+", "x"};
        int angka1 = (int) (Math.random() * 10) + 1;
        int angka2 = (int) (Math.random() * 10) + 1;
        String operator = operatorList[(int) (Math.random() * 2)];

        angka1Text.setText(String.valueOf(angka1));
        angka2Text.setText(String.valueOf(angka2));
        operatorText.setText(operator);
    }
}