//package com.example.hoang.themovie.adapter;
//
//import android.content.Context;
//import android.content.res.Configuration;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.hoang.themovie.R;
//import com.example.hoang.themovie.model.Result;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
///**
// * Created by hoang on 10/11/16.
// */
//
//public class ColorMovieAdapter extends ArrayAdapter<Result> {
//    private List<Result> mMovies;
//
//    public ColorMovieAdapter(Context context, int item_movie, List<Result> movies) {
//        super(context, -1, movies);
//        mMovies = movies;
//    }
//
//    @Override
//    public int getCount() {
//        return mMovies.size();
//    }
//
//    @Nullable
//    @Override
//    public Result getItem(int position) {
//        return mMovies.get(position);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder;
//        if (convertView == null){
//            convertView = LayoutInflater.from(getContext())
//                    .inflate(R.layout.item_movie, parent, false);
////            parent.addView(convertView);
//            viewHolder = new ViewHolder();
//            viewHolder.tvTitile = (TextView) convertView.findViewById(R.id.tvTitle);
//            viewHolder.tvOverView = (TextView) convertView.findViewById(R.id.tv);
//            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        Result movie = getItem(position);
//
//        //// fill the date
//        viewHolder.tvTitile.setText(movie.getTitle());
//        viewHolder.tvOverView.setText(movie.getOverview());
//
//        // get configuration
//
//        Configuration configuration = getContext().getResources()
//                .getConfiguration();
//        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Picasso.with(getContext())
//                    .load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath())
//                    .placeholder(R.drawable.preloading)
//                    .into(viewHolder.ivCover);
//        }else {
//            Picasso.with(getContext())
//                    .load("https://image.tmdb.org/t/p/w342" + movie.getBackdropPath())
//                    .placeholder(R.drawable.preloading)
//                    .into(viewHolder.ivCover);
//
//        }
//
//        return convertView;
//    }
//
//    private class ViewHolder{
//        public TextView tvTitile;
//        public TextView tvOverView;
//        public ImageView ivCover;
//    }
//}
//
//
//
////package com.example.hoang.themovie.adapter;
////
////import android.content.Context;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ArrayAdapter;
////import android.widget.TextView;
////
////import com.example.hoang.themovie.R;
////import com.example.hoang.themovie.model.Result;
////
////import java.util.List;
////
////
/////**
//// * Created by hoang on 10/14/2016.
//// */
////
////public class MovieAdapter extends ArrayAdapter<Result> {
////
////    public MovieAdapter(Context context, int resource, List<Result> items) {
////        super(context, resource, items);
////    }
////
////
////
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////
////        View view = convertView;
////        if (view == null) {
////            LayoutInflater inflater = LayoutInflater.from(getContext());
////            view =  inflater.inflate(R.layout.item_movie, null);
////        }
////        Result p = getItem(position);
////        if (p != null) {
////            // Anh xa + Gan gia tri
////            TextView txttitle = (TextView) view.findViewById(R.id.tvTitle);
////
////
////            /// fill the data
////            txttitle.setText("abc");
////
////
////
////        }
////        return view;
////    }
////
////}