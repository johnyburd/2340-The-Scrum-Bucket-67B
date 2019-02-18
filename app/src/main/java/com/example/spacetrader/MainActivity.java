package com.example.spacetrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Player player;
    private int totalPoints;

    private EditText name;
    private EditText pilotPoints;
    private EditText fighterPoints;
    private EditText traderPoints;
    private EditText engineerPoints;
    private Spinner difficultySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalPoints = 16;

        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> difficulty_adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, Difficulty.values());
        difficultySpinner.setAdapter(difficulty_adapter);
    }
}
