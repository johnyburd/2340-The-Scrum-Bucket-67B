package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.List;

/**
 * Adapter class for items in the WarpRecycler.
 */
public class WarpAdapter extends RecyclerView.Adapter<WarpAdapter.WarpItemViewHolder> {

    private final List<SolarSystem> solarSystems;
    private final Player player;

    /**
     * Adapter constructor.
     * @param context context from which the Adapter is made
     */
    public WarpAdapter(Context context) {
        //Model model = Model.getInstance();
        solarSystems = ViewModelProviders.of(
                (FragmentActivity) context).get(SolarSystemViewModel.class)
                .getSolarSystems();
        player = ViewModelProviders.of(
                (FragmentActivity) context).get(PlayerViewModel.class)
                .getPlayer();
    }

    /**
     * ViewHolder for WarpItem.
     */
    class WarpItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView planet;
        private final TextView location;
        private final Button warp;
        private SolarSystem system;

        WarpItemViewHolder(@NonNull View itemView) {
            super(itemView);
            planet = itemView.findViewById(R.id.planet_label);
            location = itemView.findViewById(R.id.location_label);
            warp = itemView.findViewById(R.id.warp_button);
            warp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.travel(system);
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
        holder.location.setText(planet.getLocationString());
        holder.planet.setText(planet.getName());
        holder.system = planet;
    }

    @Override
    public int getItemCount() {
        return solarSystems.size();
    }
}
