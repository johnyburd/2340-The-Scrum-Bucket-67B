package com.example.spacetrader.models;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.EnumSet;

import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.Ship;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Spaceship;
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

    public boolean continuePlayer(Context context) {
      //  Ship spaceship = new Ship(Spaceship.GNAT);
      //  EnumMap<Good, Integer> inventory = new EnumMap<>(Good.class);
        //SolarSystem location = new SolarSystem();
       // player = new Player(4, 4, 4, 4, 2000, spaceship, inventory, false, 0, 0)
        Log.e("io", "resuming");

        FileInputStream inStream = null;
        try {
            //File f = new File(Environment.getExternalStorageDirectory(), "/player.dat");
            //inStream = new FileInputStream(f);
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

    public void savePlayer(Context context) {
        Log.e("io", "saving");
        FileOutputStream outStream = null;
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
