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
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.viewmodels.MarketPlaceViewModel;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class MarketPlaceActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private MarketPlaceViewModel marketPlaceViewModel;

    private TextView credits;
    private TextView storage;
    private TextView fuel;
    private TextView hull;

    private TextView planet_info;

    private Player player;

    private SellItemAdapter sellItemAdapter;
    private BuyItemAdapter buyItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = playerViewModel.getPlayer();

        marketPlaceViewModel = ViewModelProviders.of(this).get(MarketPlaceViewModel.class);

        SolarSystemViewModel systemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        SolarSystem planet = player.getLocation();
        if (planet == null) {
            planet = systemViewModel.getSolarSystems().get(0);
        }
        player.setLocation(planet);

        marketPlaceViewModel.createMarket(player, planet);

        credits = findViewById(R.id.marketplace_credits);
        credits.setText(String.format(Locale.US, "Credits: %d", player.getCredits()));

        Observer creditsObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                credits.setText(String.format(Locale.US, "Credits: %d", (int) arg));
            }
        };

        player.addObserver(creditsObserver);

        storage = findViewById(R.id.marketplace_storage);
        storage.setText(String.format(Locale.US, "Storage: %d/15", player.getTotalGoods()));

        Observer storageObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                storage.setText(String.format(Locale.US, "Storage: %d/15", player.getTotalGoods()));
            }
        };
        player.addObserver(storageObserver);

        fuel = findViewById(R.id.market_fuel);
        fuel.setText(String.format(Locale.US,"Fuel: %d",
                player.getShip().getCurrentFuel()));

        planet_info = findViewById(R.id.market_planet_label);
        planet_info.setText(String.format(Locale.US, "Current Planet: %s",
                player.getLocation().getName()));

        hull = findViewById(R.id.hull_label);
        hull.setText(String.format(Locale.US, "Hull: %d/%d",
                player.getShip().getCurrHull(), player.getShip().getHull()));

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

        Observer locationObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (player.isMarketLocationChanged()) {
                    marketPlaceViewModel.createMarket(player, player.getLocation());
                    sellItemAdapter = new SellItemAdapter();
                    sellView.setAdapter(sellItemAdapter);
                    buyItemAdapter = new BuyItemAdapter();
                    buyView.setAdapter(buyItemAdapter);
                    planet_info.setText(String.format(Locale.US, "Current Planet: %s",
                            player.getLocation().getName()));
                    fuel.setText(String.format(Locale.US,"Fuel: %d",
                            player.getShip().getCurrentFuel()));
                    hull.setText(String.format(Locale.US, "Hull: %d/%d",
                            player.getShip().getCurrHull(), player.getShip().getHull()));
                } else {
                    sellItemAdapter.notifyDataSetChanged();
                    buyItemAdapter.notifyDataSetChanged();
                }
            }
        };
        player.addObserver(locationObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerViewModel.savePlayer(MarketPlaceActivity.this);
    }

    public void onTravelPressed(View view) {
        startActivity(new Intent(MarketPlaceActivity.this, WarpActivity.class));
    }
}
