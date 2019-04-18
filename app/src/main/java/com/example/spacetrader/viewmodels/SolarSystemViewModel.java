package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;

import java.util.List;

public class SolarSystemViewModel extends AndroidViewModel{
    private final Model model;

    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public List<SolarSystem> getSolarSystems() {
        return model.getSolarSystems();
    }
}
