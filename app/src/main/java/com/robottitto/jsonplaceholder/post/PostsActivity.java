package com.robottitto.jsonplaceholder.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.jsonplaceholder.R;
import com.robottitto.jsonplaceholder.api.model.APIService;
import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {
    public static final String POST_ID = "com.robottitto.jsonplaceholder.POST_ID";
    APIService apiService = new APIService();
    ArrayList<Post> posts = new ArrayList<>();
    ArrayAdapter postsListAdapter;
    ListView postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        getSupportActionBar().setTitle(R.string.posts_activity_title);
        getPosts();
    }

    private void getPosts() {
        postsListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, posts);
        postsList = findViewById(R.id.posts_list);
        postsList.setAdapter(postsListAdapter);
        postsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedPostId = posts.get(position).getId();
                showPostDetail(selectedPostId);
            }
        });

        Callback<List<Post>> responseCallback = new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for (Post post : response.body()) {
                    addPost(post);
                }
                updatePosts();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        };

        apiService.getPosts(responseCallback);
    }

    private void addPost(Post post) {
        posts.add(post);
    }

    private void updatePosts() {
        postsListAdapter.notifyDataSetChanged();
    }

    private void showPostDetail(int selectedPostId) {
        Intent postDetailActivityIntent = new Intent(PostsActivity.this, PostDetailActivity.class);
        postDetailActivityIntent.putExtra(POST_ID, String.valueOf(selectedPostId));
        startActivity(postDetailActivityIntent);
    }
}
