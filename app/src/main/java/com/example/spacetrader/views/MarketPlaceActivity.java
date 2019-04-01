package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import java.util.Random;

public class MarketPlaceActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private MarketPlaceViewModel marketPlaceViewModel;
    private SolarSystemViewModel systemViewModel;

    private TextView credits;
    private TextView storage;
    private TextView fuel;
    //private TextView info;

    private TextView planet_info;
    private TextView event_info;

    private Player player;

    private SellItemAdapter sellItemAdapter;
    private BuyItemAdapter buyItemAdapter;

    private Market market;

    private Observer creditsObserver;
    private Observer storageObserver;
    private Observer locationObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = playerViewModel.getPlayer();

        marketPlaceViewModel = ViewModelProviders.of(this).get(MarketPlaceViewModel.class);

        systemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        SolarSystem planet = systemViewModel.getSolarSystems().get(0);
        player.setLocation(planet);

        // fix this so that event is not always boredom
        marketPlaceViewModel.createMarket(player, planet, Event.BOREDOM);
        market = marketPlaceViewModel.getMarket();

        credits = findViewById(R.id.marketplace_credits);
        credits.setText("Credits: " + player.getCredits());

        creditsObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                credits.setText("Credits: " + arg);
            }
        };

        player.addObserver(creditsObserver);

        storage = findViewById(R.id.marketplace_storage);
        storage.setText("Storage: " + player.getTotalGoods() + "/15");

        storageObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                storage.setText("Storage: " + player.getTotalGoods() + "/15");
            }
        };
        player.addObserver(storageObserver);

        fuel = findViewById(R.id.market_fuel);
        //fuel.setText("Fuel: " + player.getShip());

        planet_info = findViewById(R.id.market_planet_label);
        planet_info.setText("Current Planet: " + player.getLocation().toString());

        event_info = findViewById(R.id.market_event_label);
        event_info.setText("Current Event:\n" + market.getEvent());

        final RecyclerView sellView = findViewById(R.id.sell_recycler);
        sellView.setLayoutManager(new LinearLayoutManager(this));
        sellView.setHasFixedSize(true);

        sellItemAdapter = new SellItemAdapter();
        sellView.setAdapter(sellItemAdapter);

        final RecyclerView buyView = findViewById(R.id.buy_recycler);
        buyView.setLayoutManager(new LinearLayoutManager(this));
        buyView.setHasFixedSize(true);

        buyItemAdapter = new BuyItemAdapter();
        buyView.setAdapter(buyItemAdapter);

        final Random rand = new Random();
        locationObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                marketPlaceViewModel.createMarket(player, player.getLocation(),
                        Event.values()[rand.nextInt(Event.values().length)]);
                market = marketPlaceViewModel.getMarket();
                sellItemAdapter = new SellItemAdapter();
                sellView.setAdapter(sellItemAdapter);
                buyItemAdapter = new BuyItemAdapter();
                buyView.setAdapter(buyItemAdapter);
                planet_info.setText("Current Planet: " + player.getLocation().toString());
                event_info.setText("Current Event:\n" + market.getEvent());
                //fuel.setText();
            }
        };
        player.addObserver(locationObserver);
    }

    public void onTravelPressed(View view) {
        startActivity(new Intent(MarketPlaceActivity.this, WarpActivity.class));
    }
}
