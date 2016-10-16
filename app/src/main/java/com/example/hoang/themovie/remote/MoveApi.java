package com.example.hoang.themovie.remote;

import com.example.hoang.themovie.model.Movie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hoang on 10/13/2016.
 */

public interface MoveApi {
    final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @GET("popular")
    Call<Movie> getMovies(@Query("api_key") String apiKey);


    class Factory {
        private static MoveApi service;

        public static MoveApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(MoveApi.class);
                return service;
            } else {
                return service;
            }
        }
    }


}
