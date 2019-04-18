package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.models.Model;

import java.util.EnumMap;
import java.util.Locale;

/**
 * Adapter for item in MarketPlace sellRecycler.
 * @author Scrum Bucket
 * @version 1.0
 */
public class SellItemAdapter extends RecyclerView.Adapter<SellItemAdapter.SellItemViewHolder> {

    private final Market market;
    private final EnumMap<Good, Integer> inventory;

    /**
     * Adapter constructor
     */
    public SellItemAdapter() {
        market = Model.getInstance().getMarket();
        inventory = Model.getInstance().getPlayer().getInventory();
    }

    /**
     * ViewHolder class for SellItemAdapter.
     */
    public class SellItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView message;
        private final TextView number;
        private final TextView credits;
        private final Button sell;
        private Good good;

        SellItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sell_item_name);
            message = itemView.findViewById(R.id.sell_item_message);
            number = itemView.findViewById(R.id.sell_item_num);
            credits = itemView.findViewById(R.id.sell_item_credit);
            sell = itemView.findViewById(R.id.sell_item_button);
            sell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message.setText(market.sell(good, 1));
                    number.setText(String.format(Locale.US, "%d", inventory.get(good)));
                }
            });
        }
    }

    @NonNull
    @Override
    public SellItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sell_item, parent, false);
        return new SellItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SellItemViewHolder holder, int position) {
        Good good = Good.values()[position];
        holder.good = good;
        holder.name.setText(good.getName());
        holder.number.setText(String.format(Locale.US, "%d", inventory.get(good)));
        holder.credits.setText(String.format(Locale.US, "%d", market.getPlayerPrices()[good.getNum()]));
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }
}
