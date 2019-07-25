package com.robottitto.jsonplaceholder.api.model;

import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIService {
    final static String API_POSTS_ROUTE = "/posts";

    @GET(API_POSTS_ROUTE)
    Call<List<Post>> getPost();
}