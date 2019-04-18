package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.spacetrader.entity.Difficulty;
import com.example.spacetrader.R;
import com.example.spacetrader.viewmodels.PlayerViewModel;

import java.util.Locale;

/**
 * Activity for the Create Player screen.
 * @author Scrum Bucket
 * @version 1.0
 */
public class CreatePlayer extends AppCompatActivity {

    private final int MAX_POINTS = 16;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        Spinner difficultySpinner = findViewById(R.id.difficulty_spinner);
        SpinnerAdapter difficulty_adapter = new ArrayAdapter<>
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
        totalPoints = pilotPoints + fighterPoints + traderPoints + engineerPoints;
    }

    /**
     * Increments pilot skill points and sets the textView.
     * @param view View for button press
     */
    public void onPilotAddPressed(View view) {
        if (validatePoints(pilotPoints + 1,
                fighterPoints, traderPoints, engineerPoints)) {
            pilotPoints++;
            totalPoints++;
            pilotText.setText(String.format(Locale.US,"%02d", pilotPoints));
        }
    }

    /**
     * Decrements pilot skill points and sets the textView.
     * @param view View for button press
     */
    public void onPilotMinusPressed(View view) {
        if (validatePoints(pilotPoints - 1,
                fighterPoints, traderPoints, engineerPoints)) {
            pilotPoints--;
            totalPoints--;
            pilotText.setText(String.format(Locale.US, "%02d", pilotPoints));
        }
    }

    /**
     * Increments fighter skill points and sets the textView.
     * @param view View for button press
     */
    public void onFighterAddPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints + 1,
                traderPoints, engineerPoints)) {
            fighterPoints++;
            totalPoints++;
            fighterText.setText(String.format(Locale.US, "%02d", fighterPoints));
        }
    }

    /**
     * Decrements fighter skill points and sets the textView.
     * @param view View for button press
     */
    public void onFighterMinusPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints - 1,
                traderPoints, engineerPoints)) {
            fighterPoints--;
            totalPoints--;
            fighterText.setText(String.format(Locale.US, "%02d", fighterPoints));
        }
    }

    /**
     * Increments trader skill points and sets the textView.
     * @param view View for button press
     */
    public void onTraderPlusPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints,
                traderPoints + 1, engineerPoints)) {
            traderPoints++;
            totalPoints++;
            traderText.setText(String.format(Locale.US, "%02d", traderPoints));
        }
    }

    /**
     * Decrements trader skill points and sets the textView.
     * @param view View for button press
     */
    public void onTraderMinusPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints,
                traderPoints - 1, engineerPoints)) {
            traderPoints--;
            totalPoints--;
            traderText.setText(String.format(Locale.US, "%02d", traderPoints));
        }
    }

    /**
     * Increments engineer skill points and sets the textView.
     * @param view View for button press
     */
    public void onEngineerPlusPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints,
                traderPoints, engineerPoints + 1)) {
            engineerPoints++;
            totalPoints++;
            engineerText.setText(String.format(Locale.US, "%02d", engineerPoints));
        }
    }

    /**
     * Decrements engineer skill points and sets the textView.
     * @param view View for button press
     */
    public void onEngineerMinusPressed(View view) {
        if (validatePoints(pilotPoints, fighterPoints,
                traderPoints, engineerPoints - 1)) {
            engineerPoints--;
            totalPoints--;
            engineerText.setText(String.format(Locale.US, "%02d", engineerPoints));
        }
    }

    /**
     * Checks that skill points are valid and creates Player.
     * Opens MarketPlaceActivity
     * @param view View for button press
     */
    public void onCreatePlayer(View view) {
        if (totalPoints < MAX_POINTS) {
            limit.setText(getString(R.string.invalid_skill_points));
        } else {
            PlayerViewModel pvm = ViewModelProviders.of(this).get(PlayerViewModel.class);
            pvm.createPlayer(name.getText().toString(),
                            pilotPoints, fighterPoints, traderPoints, engineerPoints);
            startActivity(new Intent(CreatePlayer.this, MarketPlaceActivity.class));
        }
    }

    private boolean validatePoints(int pilot, int fighter, int trader, int engineer) {
        return ((pilot + fighter + trader + engineer) <= MAX_POINTS) &&
                (pilot >= 0) && (fighter >= 0) && (trader >= 0) && (engineer >= 0);
    }
}
