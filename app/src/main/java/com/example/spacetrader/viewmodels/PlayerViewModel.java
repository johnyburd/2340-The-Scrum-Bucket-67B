package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.models.Model;

public class PlayerViewModel extends AndroidViewModel{
    private Model model;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public void createPlayer(String name, int pilot, int fighter, int trader, int engineer) {
        model.createPlayer(name, pilot, fighter, trader, engineer);
    }

    public void savePlayer(Context context) {
        model.savePlayer(context);
    }

    public boolean continuePlayer(Context context) {
        return model.continuePlayer(context);
    }

    public boolean validatePoints(int pilot, int fighter, int trader, int engineer) {
        return pilot + fighter + trader + engineer <= 16 &&
                pilot >= 0 && fighter >= 0 && trader >= 0 && engineer >= 0;
    }

    public Player getPlayer() {
        return model.getPlayer();
    }
}
