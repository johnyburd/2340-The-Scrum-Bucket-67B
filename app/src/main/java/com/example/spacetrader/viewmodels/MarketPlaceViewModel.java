package com.example.spacetrader.viewmodels;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;

public class MarketPlaceViewModel extends AndroidViewModel{
    private final Model model;

    public MarketPlaceViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public void createMarket(Player player, SolarSystem planet) {
        model.createMarket(player, planet);
    }

    public Market getMarket() {
        return model.getMarket();
    }

  //  public MutableLiveData<String> getPlayerCredits() {
   //     return new
   // }

    //public M getPlayer() {
      //  return model.getPlayer();
   // }
}
