package com.example.spacetrader.models;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        Event[] events = Event.values();
        for(Planet p : planets) {
            int l = (int) (Math.random() * levels.length);
            int r = (int) (Math.random() * resources.length);
            int e = (int) (Math.random() * events.length);
            SolarSystem s = new SolarSystem(p, levels[l], resources[r], events[e]);
            systems.add(s);
            //Log.i("SOLAR SYSTEM", s.toString());
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
        market = new Market(player, systems.get(0));
    }

    /**
     * Loads the serialized player from internal storage
     *
     * @param context Context from the main activity
     * @return succesfully loaded the player
     */
    public boolean continuePlayer(Context context) {
        Log.i("io", "resuming");

        FileInputStream inStream;
        try {
            inStream = context.openFileInput("player.dat");
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            player = ((Player) objectInStream.readObject());
            objectInStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return player != null;
    }

    /**
     * Serializes the player object into a file in internal storage
     * @param context Context from the main activity
     */
    public void savePlayer(Context context) {
        Log.i("io", "saving");
        FileOutputStream outStream;
        try {
           // File f = new File(Environment.getExternalStorageDirectory(), "/player.dat");
            //outStream = new FileOutputStream(f);
            outStream = context.openFileOutput("player.dat", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(player);
            objectOutStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Market getMarket() {
        return market;
    }

    public void createMarket(Player player, SolarSystem planet) {
        market = new Market(player, planet);
    }
}
