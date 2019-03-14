package com.example.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

public class MarketPlaceActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private SolarSystemViewModel systemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);
    }
}
