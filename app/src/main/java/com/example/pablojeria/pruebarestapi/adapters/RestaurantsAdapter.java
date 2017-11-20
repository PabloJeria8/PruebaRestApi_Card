package com.example.pablojeria.pruebarestapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pablojeria.pruebarestapi.R;
import com.example.pablojeria.pruebarestapi.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablojeria on 19-11-17.
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>  {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Restaurant restaurant = restaurants.get(position);
        final Context context = holder.itemView.getContext();

        Picasso.with(holder.itemView.getContext()).load(restaurant.getImage_url()).centerCrop().fit().into(holder.photo);
        holder.titleView.setText(restaurant.getTitle());
        holder.descriptionView.setText(restaurant.getDescription());

        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = restaurants.get(holder.getAdapterPosition()).getUrl();
                webIntent(url, context);
            }
        });


    }

    private void webIntent(String url, Context context) {
        if (url != null && url.trim().length() > 0) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


//    public void update() {
//        restaurants.clear();
//        restaurants.addAll(restaurants);
//        notifyDataSetChanged();
//    }

    public void update(List<Restaurant> restaurantslist) {
        restaurants.addAll(restaurantslist);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo, url;
        private TextView titleView, descriptionView;

        public ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.restaurantIv);
            url = (ImageView) itemView.findViewById(R.id.urlBtn);
            descriptionView = (TextView) itemView.findViewById(R.id.descriptionTv);
            titleView = (TextView) itemView.findViewById(R.id.titleTv);
        }

    }

}
