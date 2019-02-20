package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

public class ConfigurationViewModel extends AndroidViewModel {
    /**
     * Make a new view model,  system should call this automatically for us
     * @param application
     */

    public ConfigurationViewModel(@NonNull Application application) {
        super (application);
    }

    public boolean validatePoints(int pilot, int fighter, int trader, int engineer) {
        return pilot + fighter + trader + engineer <= 16 &&
                pilot >= 0 && fighter >= 0 && trader >= 0 && engineer >= 0;
    }
}
