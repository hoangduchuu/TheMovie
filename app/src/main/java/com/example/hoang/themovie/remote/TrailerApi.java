package com.example.hoang.themovie.remote;

import com.example.hoang.themovie.model.Trailer;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hoang on 10/13/2016.
 */

public interface TrailerApi {
    final String BASE_URL = "https://api.themoviedb.org/3/";
    final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    // @GET("group/{id}/users")
    @GET("movie/{id}/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<Trailer> getVideos(@Path("id") int groupId);


    class Factory {
        private static TrailerApi service;
        public static TrailerApi getTrailler() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(TrailerApi.class);
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
