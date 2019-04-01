package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.viewmodels.MarketPlaceViewModel;
import com.example.spacetrader.viewmodels.PlayerViewModel;
import com.example.spacetrader.viewmodels.SolarSystemViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarketPlaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketPlaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketPlaceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private PlayerViewModel playerViewModel;
    private MarketPlaceViewModel marketPlaceViewModel;
    private SolarSystemViewModel systemViewModel;

    private TextView credits;
    private TextView storage;
    private TextView info;

    private Player player;

    private SellItemAdapter sellItemAdapter;
    private BuyItemAdapter buyItemAdapter;

    private Market market;

    private Observer creditsObserver;
    private Observer storageObserver;

    public MarketPlaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MarketPlaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketPlaceFragment newInstance() {//String param1, String param2) {
        MarketPlaceFragment fragment = new MarketPlaceFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //setContentView(R.layout.marketplace);

        return inflater.inflate(R.layout.fragment_market_place, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        player = playerViewModel.getPlayer();

        marketPlaceViewModel = ViewModelProviders.of(this).get(MarketPlaceViewModel.class);

        systemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        SolarSystem planet = systemViewModel.getSolarSystems().get(0);
        player.setLocation(planet);

        // fix this so that event is not always boredom
        marketPlaceViewModel.createMarket(player, planet, Event.BOREDOM);
        market = marketPlaceViewModel.getMarket();

        credits = view.findViewById(R.id.marketplace_credits);
        credits.setText("Credits: " + player.getCredits());

        creditsObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                credits.setText("Credits: " + arg);
            }
        };

        player.addObserver(creditsObserver);

        storage = view.findViewById(R.id.marketplace_storage);
        storage.setText("Storage: " + player.getTotalGoods() + "/15");

        storageObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                storage.setText("Storage: " + player.getTotalGoods() + "/15");
            }
        };
        player.addObserver(storageObserver);

        info = view.findViewById(R.id.marketplace_info_label);
        info.setText("Current planet: " + player.getLocation().toString()
                + "\nCurrent Event: " + market.getEvent().toString());

        RecyclerView sellView = view.findViewById(R.id.sell_recycler);
        sellView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sellView.setHasFixedSize(true);

        sellItemAdapter = new SellItemAdapter();
        sellView.setAdapter(sellItemAdapter);

        RecyclerView buyView = view.findViewById(R.id.buy_recycler);
        buyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buyView.setHasFixedSize(true);

        buyItemAdapter = new BuyItemAdapter();
        buyView.setAdapter(buyItemAdapter);
    }
}
