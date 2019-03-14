package com.example.spacetrader.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;

import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.TechLevel;

public class Model {

    private Player player;
    private List<SolarSystem> systems;
    private Market market;

    public Model() {
        systems = new ArrayList<>();
        loadSolarSystems();
    }

    private static Model instance = new Model();
    public static Model getInstance() {
        return instance;
    }

    private void loadSolarSystems() {
        EnumSet<Planet> planets = EnumSet.allOf(Planet.class);
        TechLevel[] levels = TechLevel.values();
        Resource[] resources = Resource.values();
        for(Planet p : planets) {
            int l = (int) (Math.random() * levels.length);
            int r = (int) (Math.random() * resources.length);
            SolarSystem s = new SolarSystem(p, levels[l], resources[r]);
            systems.add(s);
            Log.i("SOLAR SYSTEM", s.toString());
        }
    }

    public List<SolarSystem> getSolarSystems() {
        return systems;
    }

    public Player getPlayer() {
        return player;
    }

    public void createPlayer(String name, int pilot, int fighter, int trader, int engineer) {
        player = new Player().setName(name)
                .setPilotPoints(pilot).setFighterPoints(fighter)
                .setTraderPoints(trader).setEngineerPoints(engineer);
        market = new Market(player, systems.get(0), Event.BOREDOM);
    }

    public Market getMarket() {
        return market;
    }

    public void createMarket(Player player, SolarSystem planet, Event event) {
        market = new Market(player, planet, event);
    }
}
