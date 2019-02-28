package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
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

    public void updatePlayer(String name, int pilot, int fighter, int trader, int engineer) {
        Player p = new Player().setName(name)
                .setPilotPoints(pilot)
                .setFighterPoints(fighter)
                .setTraderPoints(trader)
                .setEngineerPoints(engineer);
        model.updatePlayer(p);
    }

    public boolean validatePoints(int pilot, int fighter, int trader, int engineer) {
        return pilot + fighter + trader + engineer <= 16 &&
                pilot >= 0 && fighter >= 0 && trader >= 0 && engineer >= 0;
    }

    public Player getPlayer() {
        return model.getPlayer();
    }
}
