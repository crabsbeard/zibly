package com.aditya.zibly.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.zibly.R;
import com.aditya.zibly.pojo.ExploreCard;
import com.aditya.zibly.views.PlacesViewHolder;

import java.util.ArrayList;

/**
 * Created by devad_000 on 23-06-2015.
 */
public class AdapterPlacesHome extends RecyclerView.Adapter<PlacesViewHolder> {

    private ArrayList<ExploreCard> exploreCardsList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    //constructor to inflate the layout inflater

    public AdapterPlacesHome(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    //setter method for array list of explore cards

    private void setExploreCardsList(ArrayList<ExploreCard> exploreCardsList){
        this.exploreCardsList = exploreCardsList;
        notifyItemRangeChanged(0, exploreCardsList.size());
    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view_home, parent, false);
        PlacesViewHolder placesViewHolder = new PlacesViewHolder(view);
        return placesViewHolder;
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        ExploreCard currentCard = exploreCardsList.get(position);

        //setting data in the cards

        holder.getTv_place_name().setText(currentCard.getPlace_name());
        holder.getTv_place_address().setText(currentCard.getPlace_address());
        holder.getTv_place_distance().setText(currentCard.getPlace_distance());

    }

    @Override
    public int getItemCount() {
        return exploreCardsList.size();
    }
}
