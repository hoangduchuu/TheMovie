package com.example.hoang.themovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.themovie.R;
import com.example.hoang.themovie.model.Trailer;
import com.example.hoang.themovie.remote.TrailerApi;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends YouTubeBaseActivity {
    String AIP_YOUTUBE_KEY = "AIzaSyCdp4vTvzIzAL1nzID7mNFiUJ9dhgnCplw";
    private TextView tvName, tvDate, tvOverView, tvStar;

    String SHA = "5B:1A:37:88:0C:3F:F9:EE:8D:AE:4B:66:FF:C7:1C:85:27:77:94:0E";
    YouTubePlayerView mYouTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent it = getIntent();
        final int id = it.getIntExtra("idMovie", -11);

        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        tvName = (TextView) findViewById(R.id.tvTitle);
        tvDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvOverView = (TextView) findViewById(R.id.tvDescription);
        tvStar = (TextView) findViewById(R.id.textViewStar);

        tvName.setText(it.getStringExtra("name"));
        tvStar.setText(it.getStringExtra("star"));
        tvOverView.setText(it.getStringExtra("overView"));
        tvDate.setText(it.getStringExtra("releaseDate"));

        TrailerApi.Factory.getTrailler().getVideos(id).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                Log.d("Success", String.valueOf(response.isSuccessful()));
                final String videLink = response.body().getResults().get(0).getKey();
                mYouTubePlayerView.initialize(AIP_YOUTUBE_KEY,
                        new YouTubePlayer.OnInitializedListener() {

                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                youTubePlayer.loadVideo(videLink);
                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                            }
                        });
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                Log.e("Failed", t.getMessage());
            }
        });
    }

}
