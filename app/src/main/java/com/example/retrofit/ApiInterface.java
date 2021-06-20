package com.example.retrofit;


import com.example.model.GetVideoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("videolist")
    Call<List<GetVideoResponse>> getVideos();


}



