package com.example.hoang.themovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hoang.themovie.R;
import com.example.hoang.themovie.adapter.MovieAdapter;
import com.example.hoang.themovie.model.Movie;
import com.example.hoang.themovie.model.Result;
import com.example.hoang.themovie.remote.MoveApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    private ListView lvMovie;
    private ProgressBar pgLoading;
    private ArrayList<Result> arrayListMovie;

//    @Bind(R.id.swipeContainer)
//    SwipeRefreshLayout swipeContainer;
//    @BindView(R.id.lvMovie)
//    ListView lvMovie;
//    @BindView(R.id.pgLoading)
//    ProgressBar pgLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);
        lvMovie = (ListView) findViewById(R.id.lvMovie);
        arrayListMovie = new ArrayList<Result>();

        fetchMovie();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lvMovie.setAdapter(null);
                fetchMovie();
                Toast.makeText(MainActivity.this, "Date updated or not??", Toast.LENGTH_SHORT).show();
            }
        });
        // color of swipeContainer
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = arrayListMovie.get(i).getId();
                Intent it = new Intent(getApplicationContext(), DetailActivity.class);
                it.putExtra("idMovie", id);
                startActivity(it);
            }
        });
    }

    public void fetchMovie() {
        MoveApi.Factory.getInstance().getMovies(MoveApi.API_KEY).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.d("Success", String.valueOf(response.isSuccessful()));

                int totaValue = response.body().getResults().size();
                for (int i = 0; i < totaValue; i++) {
                    String title = response.body().getResults().get(i).getTitle();
                    String overView = response.body().getResults().get(i).getOverview();
                    String posterPatch = response.body().getResults().get(i).getPosterPath();
                    String dropPatch = response.body().getResults().get(i).getBackdropPath();
                    int id = response.body().getResults().get(i).getId();
                    double voteAverage = response.body().getResults().get(i).getVoteAverage();
                    arrayListMovie.add(new Result(
                            posterPatch,
                            title,
                            dropPatch,
                            overView,
                            id,
                            voteAverage
                    ));
                }
                MovieAdapter adapter = new MovieAdapter(
                        getApplicationContext(),
                        R.layout.item_movie,
                        arrayListMovie
                );
                lvMovie.setAdapter(adapter);
                pgLoading.setVisibility(View.GONE);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("FailedCmnr", t.getMessage());
            }
        });
    }
}
