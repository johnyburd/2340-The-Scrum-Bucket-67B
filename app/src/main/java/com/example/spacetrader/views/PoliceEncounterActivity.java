package com.example.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.models.Model;

import java.util.Locale;

public class PoliceEncounterActivity extends AppCompatActivity {

    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_encounter);
        player = Model.getInstance().getPlayer();

        TextView encounter = findViewById(R.id.police_encounter_label);
        encounter.setText(String.format(Locale.US,
                "On the way to %s, you encounter a police wasp.\n\nThe police summon you to submit to an inspection.",
                player.getLocation().getName()));
    }

    public void onPoliceAttackPressed(View view) {
        player.attackPolice(System.nanoTime());
        finish();
    }

    public void onPoliceFleePressed(View view) {
        player.fleePolice(System.nanoTime());
        finish();
    }

    public void onPoliceSubmitPressed(View view) {
        player.submit();
        finish();
    }

    public void onPoliceBribePressed(View view) {
        player.payBribe(player.bribeAmount(System.nanoTime()));
        finish();
    }
}
