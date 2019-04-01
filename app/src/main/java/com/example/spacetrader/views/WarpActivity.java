package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;
import com.example.spacetrader.viewmodels.MarketPlaceViewModel;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.Observable;
import java.util.Observer;

public class WarpActivity extends AppCompatActivity {

    private Player player;

    private RecyclerView planets;
    private WarpAdapter adapter;

    private Button cancel;
    private TextView text;

    private Observer locationObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warp);

        player = ViewModelProviders.of(this).get(PlayerViewModel.class).getPlayer();

        text = findViewById(R.id.warp_label);
        cancel = findViewById(R.id.warp_cancel);

        text.setText("Current planet: " + player.getLocation().toString());

        planets = findViewById(R.id.warp_recycler);
        planets.setLayoutManager(new LinearLayoutManager(this));
        planets.setHasFixedSize(true);

        adapter = new WarpAdapter();
        planets.setAdapter(adapter);

        locationObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                text.setText("Current planet: " + player.getLocation().toString());
                finish();
            }
        };

        player.addObserver(locationObserver);
    }

    public void onWarpCancelPressed(View view) {
        onBackPressed();
    }
}
