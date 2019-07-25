package com.robottitto.jsonplaceholder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.jsonplaceholder.api.model.APIService;
import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIService apiService = new APIService();
    ArrayList<String> postsTitles = new ArrayList<>();
    ArrayAdapter postsListAdapter;
    ListView postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.main_activity_title);
        getPosts();
    }

    private void getPosts() {
        postsListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, postsTitles);
        postsList = findViewById(R.id.posts_list);
        postsList.setAdapter(postsListAdapter);

        Callback<List<Post>> responseCallback = new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for (Post post : response.body()) {
                    addPostTitle(post.getTitle());
                }
                updatePostsAdapter();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        };
        apiService.getPosts(responseCallback);
    }

    private void addPostTitle(String postTitle) {
        postsTitles.add(postTitle);
    }

    private void updatePostsAdapter() {
        postsListAdapter.notifyDataSetChanged();
    }
}