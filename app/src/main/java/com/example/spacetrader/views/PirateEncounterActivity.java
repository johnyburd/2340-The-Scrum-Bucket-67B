package com.example.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.models.Model;

public class PirateEncounterActivity extends AppCompatActivity {
    private TextView encounter;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pirate_encounter);

        player = Model.getInstance().getPlayer();

        encounter = findViewById(R.id.pirate_encounter_label);
        encounter.setText("On your way to " + player.getLocation().getName()
                + ", you encounter a pirate hornet.\n\nYour opponent attacks.");
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
