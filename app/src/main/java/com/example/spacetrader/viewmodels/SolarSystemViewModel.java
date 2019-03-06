package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.Display;

import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;

import java.util.List;

public class SolarSystemViewModel extends AndroidViewModel{
    private Model model;

    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public List<SolarSystem> getSolarSystems() {
        return model.getSolarSystems();
    }
}
