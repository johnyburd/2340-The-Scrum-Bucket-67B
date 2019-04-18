package com.example.spacetrader.viewmodels;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;

/**
 * ViewModel for the MarketPlace
 * @author Scrum Bucket
 * @version 1.0
 */
public class MarketPlaceViewModel extends AndroidViewModel{
    private final Model model;

    /**
     * constructor for ViewModel
     * @param application context
     */
    public MarketPlaceViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    /**
     * Creates a new market
     * @param player player
     * @param planet solarSystem of new market
     */
    public void createMarket(Player player, SolarSystem planet) {
        model.createMarket(player, planet);
    }

    /**
     * @return an instance of the current market
     */
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
