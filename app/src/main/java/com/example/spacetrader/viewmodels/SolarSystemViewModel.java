package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;

import java.util.List;

/**
 * ViewModel for SolarSystem access
 * @author ScrumBucket
 * @version 1.0
 */
public class SolarSystemViewModel extends AndroidViewModel{
    private final Model model;

    /**
     * constructor
     * @param application application
     */
    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    /**
     *
     * @return list of loaded solarSystems
     */
    public List<SolarSystem> getSolarSystems() {
        return model.getSolarSystems();
    }
}
