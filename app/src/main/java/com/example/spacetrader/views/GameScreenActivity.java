package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.List;

public class GameScreenActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private SolarSystemViewModel systemViewModel;

    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_home_activity);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        systemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

        List<SolarSystem> systems = systemViewModel.getSolarSystems();
        String s = "";
        s += playerViewModel.getPlayer().toString() + "\n\n";
        s += "Solar System Info:\n";
        for (SolarSystem system : systems) {
            s += system.toString() + "\n";
        }
    }
}
