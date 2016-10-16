package com.example.hoang.themovie.adapter;

import android.content.Context;
import android.content.res.Configuration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.themovie.R;
import com.example.hoang.themovie.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitile = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverView = (TextView) convertView.findViewById(R.id.tvOverView);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tvStar);
            viewHolder.tvOverViewBottom = (TextView) convertView.findViewById(R.id.tvOverViewBottom);
            viewHolder.ivbg = (ImageView) convertView.findViewById(R.id.dlaksjdlksaj);
            viewHolder.hot = (ImageView) convertView.findViewById(R.id.ivHot2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Result movie = getItem(position);
        double star = movie.getVoteAverage();
        viewHolder.tvStar.setText(String.valueOf(star));
        viewHolder.tvTitile.setText(movie.getTitle());
        viewHolder.tvOverView.setText(movie.getOverview());
        viewHolder.hot.setVisibility(View.GONE);
        viewHolder.tvOverView.setText(movie.getOverview());
        Configuration configuration = getContext().getResources()
                .getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext())
                    .load("https://image.tmdb.org/t/p/w342/" + movie.getPosterPath())
                    .transform(new RoundedCornersTransformation(40, 0, RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT))
                    .placeholder(R.drawable.preloading)
                    .into(viewHolder.ivCover);
        } else {
            Picasso.with(getContext())
                    .load("https://image.tmdb.org/t/p/w342/" + movie.getBackdropPath())
                    .transform(new RoundedCornersTransformation(240, 0, RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT))
                    .placeholder(R.drawable.preloading)
                    .into(viewHolder.ivCover);
        }

        if (star > 5) {
            viewHolder.hot.setVisibility(View.VISIBLE);
//            viewHolder.tvOverViewBottom.setText("////");
        } else {
            viewHolder.hot.setVisibility(View.GONE);

        }

        return convertView;
    }


    private class ViewHolder {
        public TextView tvTitile;
        public TextView tvOverView;
        public TextView tvOverViewBottom;
        public TextView tvStar;
        public ImageView ivCover;
        public ImageView ivbg;
        public ImageView hot;
    }
}
