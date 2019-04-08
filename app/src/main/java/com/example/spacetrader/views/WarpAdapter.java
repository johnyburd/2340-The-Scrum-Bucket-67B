package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Ship;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.models.Model;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class WarpAdapter extends RecyclerView.Adapter<WarpAdapter.WarpItemViewHolder> {

    private List<SolarSystem> solarSystems;
    private Player player;
    private Ship ship;

    public WarpAdapter() {
        solarSystems = Model.getInstance().getSolarSystems();
        player = Model.getInstance().getPlayer();
    }

    class WarpItemViewHolder extends RecyclerView.ViewHolder {
        private TextView planet;
        private TextView location;
        private Button warp;
        private SolarSystem system;

        public WarpItemViewHolder(@NonNull View itemView) {
            super(itemView);
            planet = itemView.findViewById(R.id.planet_label);
            location = itemView.findViewById(R.id.location_label);
            warp = itemView.findViewById(R.id.warp_button);
            warp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (player.getShip().Travel(player, system)) {
                        player.setLocation(system);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public WarpItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.warp_item, parent, false);
        return new WarpItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WarpAdapter.WarpItemViewHolder holder, int i) {
        SolarSystem planet = solarSystems.get(i);
        holder.location.setText(planet.getLocation().toString());
        holder.planet.setText(planet.getName());
        holder.system = planet;
    }

    @Override
    public int getItemCount() {
        return solarSystems.size();
    }
}
