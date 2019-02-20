package com.example.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;

public class MainActivity extends AppCompatActivity {

    private Player player;
    private int totalPoints;

    private EditText name;
    private TextView limit;

    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private TextView pilotText;
    private TextView fighterText;
    private TextView traderText;
    private TextView engineerText;

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

        name = findViewById(R.id.name_text);
        limit = findViewById(R.id.points_limit_label);

        pilotText = findViewById(R.id.pilot_skill);
        fighterText = findViewById(R.id.fighter_skill);
        traderText = findViewById(R.id.trader_skill);
        engineerText = findViewById(R.id.engineer_skill);
        pilotPoints = 4;
        fighterPoints = 4;
        traderPoints = 4;
        engineerPoints = 4;
        player = new Player();

        Button addPilot = findViewById(R.id.pilot_plus);
        Button addFighter = findViewById(R.id.fighter_plus);
        Button addTrader = findViewById(R.id.trader_plus);
        Button addEngineer = findViewById(R.id.engineer_plus);
        Button minusPilot = findViewById(R.id.pilot_minus);
        Button minusFighter = findViewById(R.id.fighter_minus);
        Button minusTrader = findViewById(R.id.trader_minus);
        Button minusEngineer = findViewById(R.id.engineer_minus);
    }

    public void onPilotAddPressed(View view) {
        totalPoints++;
        pilotPoints++;
        pilotText.setText(String.format("%02d", pilotPoints));
    }
    public void onPilotMinusPressed(View view) {
        if (pilotPoints > 0) {
            totalPoints--;
            pilotPoints--;
            pilotText.setText(String.format("%02d", pilotPoints));
        }
    }
    public void onFighterAddPressed(View view) {
        totalPoints++;
        fighterPoints++;
        fighterText.setText(String.format("%02d", fighterPoints));
    }
    public void onFighterMinusPressed(View view) {
        if (fighterPoints > 0) {
            totalPoints--;
            fighterPoints--;
            fighterText.setText(String.format("%02d", fighterPoints));
        }
    }
    public void onTraderPlusPressed(View view) {
        totalPoints++;
        traderPoints++;
        traderText.setText(String.format("%02d", traderPoints));
    }
    public void onTraderMinusPressed(View view) {
        if (traderPoints > 0) {
            totalPoints--;
            traderPoints--;
            traderText.setText(String.format("%02d", traderPoints));
        }
    }
    public void onEngineerPlusPressed(View view) {
        totalPoints++;
        engineerPoints++;
        engineerText.setText(String.format("%02d", engineerPoints));
    }
    public void onEngineerMinusPressed(View view) {
        if (engineerPoints > 0) {
            totalPoints--;
            engineerPoints--;
            engineerText.setText(String.format("%02d", engineerPoints));
        }
    }

    public void onCreatePlayer(View view) {
        if (totalPoints > 16) {
            limit.setText("The number of skill points exceeds 16.");
        } else if (totalPoints < 16) {
            limit.setText("The number of skill points is below 16.");
        } else {
            player.setName(name.getText().toString())
                    .setPilotPoints(pilotPoints)
                    .setFighterPoints(fighterPoints)
                    .setTraderPoints(traderPoints)
                    .setEngineerPoints(engineerPoints);
            Log.i("Player output", player.toString());
        }
    }
}
