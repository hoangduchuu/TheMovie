package com.example.hoang.themovie.remote;

import com.example.hoang.themovie.model.Movie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by hoang on 10/13/2016.
 */

public interface MoveApi {
    final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY = "popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    @GET(API_KEY)
    Call<Movie> getMovies();

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

//        private static OkHttpClient client(){
//            return  new OkHttpClient.Builder()
//                    .addInterceptor()
//                    .build();
//        }
//
//        private static Interceptor apikeyInterceptor(){
//            return  new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    HttpUrl
//                    return null;
//                }
//            };
//        }
    }





}
