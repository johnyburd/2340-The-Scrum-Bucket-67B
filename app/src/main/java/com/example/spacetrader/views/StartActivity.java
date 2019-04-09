package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodels.PlayerViewModel;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    public void onNewGamePressed(View view) {
        startActivity(new Intent(StartActivity.this, CreatePlayer.class));
    }

    public void onContinuePressed(View view) {
        PlayerViewModel viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        if (viewModel.continuePlayer(StartActivity.this)) {
            startActivity(new Intent(StartActivity.this, MarketPlaceActivity.class));
        } else {
            Toast toast = Toast.makeText(StartActivity.this, "No save data", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
