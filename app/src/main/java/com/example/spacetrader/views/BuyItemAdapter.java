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

public class BuyItemAdapter extends RecyclerView.Adapter<BuyItemAdapter.BuyItemViewHolder> {

    private Market market;
    private EnumMap<Good, Integer> inventory;

    public BuyItemAdapter() {
        market = Model.getInstance().getMarket();
        inventory = market.getInventory();
    }

    public class BuyItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView message;
        private TextView number;
        private TextView credits;
        private Button buy;
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
        holder.number.setText(String.format(Locale.US, "%s", inventory.get(good).toString()));
        holder.credits.setText(String.format(Locale.US, "%d", market.getPlanetPrices()[good.getNum()]));
    }

    @Override
    public int getItemCount() {
        return Good.values().length;
    }
}
