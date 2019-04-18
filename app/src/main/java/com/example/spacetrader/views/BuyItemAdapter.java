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
import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.viewmodels.MarketPlaceViewModel;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Objects;

/**
 * Adapter for item in the Marketplace buyRecycler.
 * @version 1.0
 * @author Scrum Bucket
 */
public class BuyItemAdapter extends RecyclerView.Adapter<BuyItemAdapter.BuyItemViewHolder> {

    private final Market market;
    private final EnumMap<Good, Integer> inventory;

    /**
     * Adapter constructor.
     * @param context context from which the Adapter is made
     */
    public BuyItemAdapter(Context context) {
        MarketPlaceViewModel marketPlaceViewModel = ViewModelProviders.of(
                (FragmentActivity) context).get(MarketPlaceViewModel.class);
        market = marketPlaceViewModel.getMarket();
        inventory = market.getInventory();
    }

    /**
     * ViewHolder class for the BuyItemAdapter.
     */
    class BuyItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView message;
        private final TextView number;
        private final TextView credits;
        private final Button buy;
        private Good good;

        BuyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.buy_item_name);
            message = itemView.findViewById(R.id.buy_item_message);
            number = itemView.findViewById(R.id.buy_item_num);
            credits = itemView.findViewById(R.id.buy_item_credit);
            buy = itemView.findViewById(R.id.buy_item_button);
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message.setText(market.buy(good, 1));
                    number.setText(String.format(Locale.US, "%d", inventory.get(good)));
                }
            });
        }
    }

    @NonNull
    @Override
    public BuyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buy_item, parent, false);
        return new BuyItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyItemViewHolder holder, int position) {
        Good good = Good.values()[position];
        holder.good = good;
        holder.name.setText(good.getName());
        holder.number.setText(String.format(
                Locale.US, "%s",
                Objects.requireNonNull(inventory.get(good)).toString()));
        holder.credits.setText(String.format(Locale.US,
                "%d", market.getPlanetPrices()[good.getNum()]));
    }

    @Override
    public int getItemCount() {
        return Good.values().length;
    }
}
