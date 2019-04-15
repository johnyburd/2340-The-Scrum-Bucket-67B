package com.example.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.models.Model;

import java.util.Locale;

public class PirateEncounterActivity extends AppCompatActivity {
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pirate_encounter);

        player = Model.getInstance().getPlayer();

        TextView encounter = findViewById(R.id.pirate_encounter_label);
        encounter.setText(String.format(Locale.US,
                "On your way to %s, you encounter a pirate hornet.\n\nYour opponent attacks.",
                player.getLocation().getName()));
    }

    public void onPirateAttackPressed(View view) {
        player.attackPirates(System.nanoTime());
        finish();
    }

    public void onPirateFleePressed(View view) {
        player.fleePirates(System.nanoTime());
        finish();
    }

    public void onPirateSurrenderPressed(View view) {
        player.surrender();
        finish();
    }
}
