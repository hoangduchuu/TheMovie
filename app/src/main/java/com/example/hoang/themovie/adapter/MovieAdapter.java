package com.example.hoang.themovie.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hoang.themovie.R;
import com.example.hoang.themovie.model.Result;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by hoang on 10/11/16.
 */

public class MovieAdapter extends ArrayAdapter<Result> {
    private List<Result> mMovies;

    public MovieAdapter(Context context, int item_movie, List<Result> movies) {
        super(context, -1, movies);
        mMovies = movies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Nullable
    @Override
    public Result getItem(int position) {
        return mMovies.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
//            parent.addView(convertView);
            viewHolder = new ViewHolder();
            viewHolder.tvTitile = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverView = (TextView) convertView.findViewById(R.id.tvOverView);
            viewHolder.tvOverViewBottom = (TextView) convertView.findViewById(R.id.tvOverViewBottom);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tvStar);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            viewHolder.lilaBg = (LinearLayout)convertView.findViewById(R.id.lilaBg);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Result movie = getItem(position);
        viewHolder.tvStar.setText(String.valueOf(movie.getVoteAverage()));

        double rate = movie.getVoteAverage();
        if(rate > 6){
            /// vote star > 5 --> set full Background
            viewHolder.ivCover.setVisibility(View.GONE);
            viewHolder.tvOverView.setVisibility(View.GONE);
            viewHolder.tvOverViewBottom.setText(movie.getOverview());
            Picasso.with(getContext()).load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath()).into(new Target() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    viewHolder.lilaBg.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
                }
                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Log.d("TAG", "FAILED");

                }
                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Log.d("TAG", "Prepare Load");
                }
            });
            // follow this thread: http://stackoverflow.com/questions/29777354/how-do-i-set-background-image-with-picasso-in-code
            // end vote star > 5 --> set full Background
        }else{
            // what else
            viewHolder.tvOverViewBottom.setVisibility(convertView.GONE);
        }

        //// fill the date
        viewHolder.tvTitile.setText(movie.getTitle());
        viewHolder.tvOverView.setText(movie.getOverview());

        // get configuration

        Configuration configuration = getContext().getResources()
                .getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getContext())
                    .load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath())
                    .placeholder(R.drawable.preloading)
                    .into(viewHolder.ivCover);
        }else {
            Picasso.with(getContext())
                    .load("https://image.tmdb.org/t/p/w342" + movie.getBackdropPath())
                    .placeholder(R.drawable.preloading)
                    .into(viewHolder.ivCover);

        }

        return convertView;
    }

    private class ViewHolder{
        public TextView tvTitile;
        public TextView tvOverView, tvOverViewBottom, tvStar;
        public ImageView ivCover;
        public LinearLayout lilaBg;
    }
}


