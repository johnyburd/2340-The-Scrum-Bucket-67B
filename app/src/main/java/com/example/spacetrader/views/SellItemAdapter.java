package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.models.Model;

import java.util.EnumMap;

public class SellItemAdapter extends RecyclerView.Adapter<SellItemAdapter.SellItemViewHolder> {

    private Market market;
    private EnumMap<Good, Integer> inventory;

    public SellItemAdapter() {
        market = Model.getInstance().getMarket();
        inventory = Model.getInstance().getPlayer().getInventory();
    }

    public class SellItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView number;
        private TextView credits;
        //private Button sell;
        private Good good;

        public SellItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sell_item_name);
            number = itemView.findViewById(R.id.sell_item_num);
            credits = itemView.findViewById(R.id.sell_item_credit);
            //sell = itemView.findViewById(R.id.sell_item_button);
        }
/*
        public void onSellPressed(View view) {
            String message = market.sell(good, Event.BOREDOM, 1);
            name.setText(name.getText() + "\n" + message);
            number.setText(inventory.get(good));
        }*/
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
        holder.number.setText(inventory.get(good).toString());
        holder.credits.setText("" + good.getPrice());
        //holder.name.setText("test name " + position);
    }

    @Override
    public int getItemCount() {
        return Good.values().length;
    }
}
