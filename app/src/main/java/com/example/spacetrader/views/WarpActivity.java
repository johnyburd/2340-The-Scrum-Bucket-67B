package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Ship;
import com.example.spacetrader.viewmodels.PlayerViewModel;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * Activity for Warp Screen.
 * @author Scrum Bucket
 * @version 1.0
 */
public class WarpActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warp);

        player = ViewModelProviders.of(this).get(PlayerViewModel.class).getPlayer();
        Ship ship = player.getShip();

        TextView text = findViewById(R.id.warp_label);
        text.setText(String.format(Locale.US,
                "Current planet: %s\nFuel: %d",
                player.getLocationName(),
                ship.getCurrentFuel()));

        RecyclerView planets = findViewById(R.id.warp_recycler);
        planets.setLayoutManager(new LinearLayoutManager(this));
        planets.setHasFixedSize(true);

        WarpAdapter adapter = new WarpAdapter(this);
        planets.setAdapter(adapter);

        Observer locationObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (player.isLocationChanged()) {
                    if (player.underAttack(System.nanoTime())) {
                        startActivity(new Intent(WarpActivity.this,
                                PirateEncounterActivity.class));
                    } else if (player.underArrest(System.nanoTime())) {
                        startActivity(new Intent(WarpActivity.this,
                                PoliceEncounterActivity.class));
                    }
                    finish();
                }
            }
        };
        player.addObserver(locationObserver);
    }

    /**
     * Returns back to MarketPlace screen.
     * @param view View for button press
     */
    public void onWarpCancelPressed(View view) {
        onBackPressed();
    }
}
