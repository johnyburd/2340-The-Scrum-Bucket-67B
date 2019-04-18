package com.example.spacetrader.models;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.TechLevel;

/**
 * Model for MVVM
 * @author Scrum Bucket
 * @version 1.0
 */
public final class Model {

    private Player player;
    private final List<SolarSystem> systems;
    private Market market;

    /**
     * Constructor initializing solarSystem list
     */
    private Model() {
        systems = new ArrayList<>();
        loadSolarSystems();
    }

    private static final Model instance = new Model();

    /**
     *
     * @return instance of the model
     */
    public static Model getInstance() {
        return instance;
    }

    private void loadSolarSystems() {
        Planet[] planets = Planet.values();
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

    /**
     *
     * @return list of the solarSystems
     */
    public List<SolarSystem> getSolarSystems() {
        return Collections.unmodifiableList(systems);
    }

    /**
     *
      * @return player from the model instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Creates a new player
     * @param name player name
     * @param pilot pilot skill points
     * @param fighter fighter skill points
     * @param trader trader skill points
     * @param engineer engineer skill points
     */
    public void createPlayer(String name, int pilot, int fighter, int trader, int engineer) {
        player = new Player(name, pilot, fighter, trader, engineer);
        market = new Market(player, systems.get(0));
    }

    /**
     * Loads the serialized player from internal storage
     *
     * @param context Context from the main activity
     * @return successfully loaded the player
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
            ObjectOutput objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(player);
            objectOutStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Creates a new market
     * @param player player
     * @param planet market location
     */
    public void createMarket(Player player, SolarSystem planet) {
        market = new Market(player, planet);
    }
}
