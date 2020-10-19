package com.mirfanrafif.belajarmencongak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {

    private int EXTRA_SKOR;
    private TextView skorText;
    private Button kembaliButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        skorText = (TextView) findViewById(R.id.skorAkhirText);

        final Intent intent = getIntent();
        EXTRA_SKOR = intent.getIntExtra("EXTRA_SKOR", 0);
        skorText.setText("Dalam 1 Menit, Anda dapat Menyelesaikan " + EXTRA_SKOR + " Soal");

        kembaliButton = (Button) findViewById(R.id.kembaliButton);
        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(resultActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}