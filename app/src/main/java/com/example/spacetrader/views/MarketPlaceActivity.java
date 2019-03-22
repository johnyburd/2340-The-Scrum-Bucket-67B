package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MarketPlaceActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private MarketPlaceViewModel marketPlaceViewModel;
    private SolarSystemViewModel systemViewModel;

    private TextView credits;
    private TextView storage;
    private TextView info;

    private Player player;

    private SellItemAdapter sellItemAdapter;
    private BuyItemAdapter buyItemAdapter;

    private Market market;

    private Observer creditsObserver;
    private Observer storageObserver;

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
        storage.setText("Storage: " + player.getTotalGoods() + "/30");

        storageObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                storage.setText("Storage: " + player.getTotalGoods() + "/30");
            }
        };
        player.addObserver(storageObserver);

        info = findViewById(R.id.marketplace_info_label);
        info.setText("Current planet: " + player.getLocation().toString()
                + "\nCurrent Event: " + market.getEvent().toString());

        RecyclerView sellView = findViewById(R.id.sell_recycler);
        sellView.setLayoutManager(new LinearLayoutManager(this));
        sellView.setHasFixedSize(true);

        sellItemAdapter = new SellItemAdapter();
        sellView.setAdapter(sellItemAdapter);

        RecyclerView buyView = findViewById(R.id.buy_recycler);
        buyView.setLayoutManager(new LinearLayoutManager(this));
        buyView.setHasFixedSize(true);

        buyItemAdapter = new BuyItemAdapter();
        buyView.setAdapter(buyItemAdapter);
    }
}
