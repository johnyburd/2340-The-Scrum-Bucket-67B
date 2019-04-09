package com.example.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Difficulty;

public class EditDifficulty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> standingDifficulty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        standingDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(standingDifficulty);
    }
}
