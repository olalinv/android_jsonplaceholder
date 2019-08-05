package com.robottitto.jsonplaceholder.api.model;

import com.robottitto.jsonplaceholder.api.model.entity.Comment;
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

    public void getComments(Callback<List<Comment>> callback) {
        iAPIService.getComments().enqueue(callback);
    }

    public void getCommentsByPostId(int postId, Callback<List<Comment>> callback) {
        iAPIService.getCommentsByPostId(postId).enqueue(callback);
    }

    public void getPosts(Callback<List<Post>> callback) {
        iAPIService.getPosts().enqueue(callback);
    }

    public void getPost(int postId, Callback<Post> callback) {
        iAPIService.getPost(postId).enqueue(callback);
    }

}
