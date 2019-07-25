package com.robottitto.jsonplaceholder.api.model;

import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private final static String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    private Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    private IAPIService iAPIService = retrofit.create(IAPIService.class);

    public void getPosts(Callback<List<Post>> callback) {
        iAPIService.getPost().enqueue(callback);
    }
}
