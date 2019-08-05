package com.robottitto.jsonplaceholder.api.model;

import com.robottitto.jsonplaceholder.api.model.entity.Comment;
import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPIService {
    final static String API_COMMENTS_ROUTE = "/comments";
    final static String API_POSTS_ROUTE = "/posts";

    @GET(API_COMMENTS_ROUTE)
    Call<List<Comment>> getComments();

    @GET(API_COMMENTS_ROUTE )
    Call<List<Comment>> getCommentsByPostId(@Query("postId") int postId);

    @GET(API_POSTS_ROUTE)
    Call<List<Post>> getPosts();

    @GET(API_POSTS_ROUTE + "/{id}")
    Call<Post> getPost(@Path("id") int postId);
}