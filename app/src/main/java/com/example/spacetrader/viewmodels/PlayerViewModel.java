package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.models.Model;

import java.util.AbstractMap;

/**
 * ViewModel for the Player
 * @author ScrumBucket
 * @version 1.0
 */
public class PlayerViewModel extends AndroidViewModel{
    private final Model model;

    /**
     * Constructor
     * @param application context
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    /**
     * Creates a new player instance.
     * @param name player name
     * @param pilot pilot skill points
     * @param fighter fighter skill points
     * @param trader trader skill points
     * @param engineer engineer skill points
     */
    public void createPlayer(String name, int pilot, int fighter, int trader, int engineer) {
        model.createPlayer(name, pilot, fighter, trader, engineer);
    }

    /**
     * Serializes the player object into a file in internal storage
     * @param context Context from the main activity
     */
    public void savePlayer(Context context) {
        model.savePlayer(context);
    }

    /**
     * Loads the serialized player from internal storage
     * @param context context from main activity
     * @return if player was successfully loaded
     */
    public boolean continuePlayer(Context context) {
        return model.continuePlayer(context);
    }

    /**
     * @return instance of player
     */
    public Player getPlayer() {
        return model.getPlayer();
    }

    /**
     *
     * @return player inventory of goods
     */
    public AbstractMap<Good, Integer> getPlayerInventory() {
        return getPlayer().getInventory();
    }
}
