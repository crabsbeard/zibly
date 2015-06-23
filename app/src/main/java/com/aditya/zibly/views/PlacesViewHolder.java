package com.aditya.zibly.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aditya.zibly.R;

/**
 * Created by devad_000 on 23-06-2015.
 */
public class PlacesViewHolder extends RecyclerView.ViewHolder {

    private ImageView iv_place_cover;
    private TextView tv_place_distance;
    private ImageView iv_place_icon;
    private TextView tv_place_name;
    private TextView tv_place_address;
    private ImageView iv_like_icon;
    private RatingBar rb_place_rating;

    public PlacesViewHolder(View itemView) {
        super(itemView);
        iv_place_cover = (ImageView) itemView.findViewById(R.id.iv_location_image);
        tv_place_distance = (TextView)itemView.findViewById(R.id.tv_location_distance);
        iv_place_icon = (ImageView)itemView.findViewById(R.id.iv_location_icon);
        tv_place_name = (TextView) itemView.findViewById(R.id.tv_location_name);
        tv_place_address = (TextView) itemView.findViewById(R.id.tv_location_address);
        iv_like_icon = (ImageView) itemView.findViewById(R.id.iv_like_icon);
    }

    public ImageView getIv_place_cover() {
        return iv_place_cover;
    }

    public TextView getTv_place_distance() {
        return tv_place_distance;
    }

    public ImageView getIv_place_icon() {
        return iv_place_icon;
    }

    public TextView getTv_place_name() {
        return tv_place_name;
    }

    public TextView getTv_place_address() {
        return tv_place_address;
    }

    public ImageView getIv_like_icon() {
        return iv_like_icon;
    }

    public RatingBar getRb_place_rating() {
        return rb_place_rating;
    }
}
