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
import com.example.spacetrader.viewmodels.MarketPlaceViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * Activity for the MarketPlace screen.
 * @author Scrum Bucket
 * @version 1.0
 */
public class MarketPlaceActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    private TextView credits;
    private TextView storage;
    private TextView fuel;
    private TextView hull;

    private TextView planet_info;

    private Player player;
    private Ship ship;

    private SellItemAdapter sellItemAdapter;
    private BuyItemAdapter buyItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = playerViewModel.getPlayer();
        ship = player.getShip();

        if (player.getLocation() == null) {
            player.setLocation(ViewModelProviders.of(this)
                    .get(SolarSystemViewModel.class)
                    .getSolarSystems().get(0));
        }

        ViewModelProviders.of(this).get(MarketPlaceViewModel.class)
                .createMarket(player, player.getLocation());

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
        storage.setText(String.format(Locale.US, "Storage: %d/%d",
                player.getTotalGoods(), ship.getCargo()));

        Observer storageObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                storage.setText(String.format(Locale.US, "Storage: %d/%d",
                        player.getTotalGoods(), ship.getCargo()));
            }
        };
        player.addObserver(storageObserver);

        fuel = findViewById(R.id.market_fuel);
        fuel.setText(String.format(Locale.US,"Fuel: %d",
                ship.getCurrentFuel()));

        planet_info = findViewById(R.id.market_planet_label);
        planet_info.setText(String.format(Locale.US, "Current Planet: %s",
                player.getLocationName()));

        hull = findViewById(R.id.hull_label);
        hull.setText(String.format(Locale.US, "Hull: %d/%d",
                ship.getCurrHull(), ship.getHull()));

        final RecyclerView sellView = findViewById(R.id.sell_recycler);
        sellView.setLayoutManager(new LinearLayoutManager(this));
        sellView.setHasFixedSize(true);

        sellItemAdapter = new SellItemAdapter(this);
        sellView.setAdapter(sellItemAdapter);

        final RecyclerView buyView = findViewById(R.id.buy_recycler);
        buyView.setLayoutManager(new LinearLayoutManager(this));
        buyView.setHasFixedSize(true);

        buyItemAdapter = new BuyItemAdapter(this);
        buyView.setAdapter(buyItemAdapter);

        final MarketPlaceActivity context = this;

        Observer locationObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (player.isMarketLocationChanged()) {
                    ViewModelProviders.of(context).get(MarketPlaceViewModel.class)
                            .createMarket(player, player.getLocation());
                    sellItemAdapter = new SellItemAdapter(context);
                    sellView.setAdapter(sellItemAdapter);
                    buyItemAdapter = new BuyItemAdapter(context);
                    buyView.setAdapter(buyItemAdapter);
                    planet_info.setText(String.format(Locale.US, "Current Planet: %s",
                            player.getLocationName()));
                    fuel.setText(String.format(Locale.US,"Fuel: %d",
                            ship.getCurrentFuel()));
                    hull.setText(String.format(Locale.US, "Hull: %d/%d",
                            ship.getCurrHull(), ship.getHull()));
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

    /**
     * Opens the warp screen.
     * @param view View for button press
     */
    public void onTravelPressed(View view) {
        startActivity(new Intent(MarketPlaceActivity.this, WarpActivity.class));
    }
}
