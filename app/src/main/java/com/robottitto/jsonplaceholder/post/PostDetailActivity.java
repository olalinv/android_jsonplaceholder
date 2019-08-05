package com.robottitto.jsonplaceholder.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.jsonplaceholder.R;
import com.robottitto.jsonplaceholder.api.model.APIService;
import com.robottitto.jsonplaceholder.api.model.entity.Comment;
import com.robottitto.jsonplaceholder.api.model.entity.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailActivity extends AppCompatActivity {
    APIService apiService = new APIService();
    ArrayList<Comment> postComments = new ArrayList<>();
    ArrayAdapter postCommentsListAdapter;
    ListView postCommentsList;
    TextView postCommentsHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getSupportActionBar().setTitle(R.string.post_detail_activity_title);
        postCommentsHeader = (TextView) findViewById(R.id.postCommentsHeader);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int postId = Integer.valueOf(intent.getStringExtra(PostsActivity.POST_ID));
        getPostDetail(postId);
        getPostComments(postId);
    }

    private void getPostDetail(int postId) {
        Callback<Post> responseCallback = new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                showPostDetail(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        };

        apiService.getPost(postId, responseCallback);
    }

    private void getPostComments(int postId) {
        postCommentsListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, postComments);
        postCommentsList = findViewById(R.id.post_comments_list);
        postCommentsList.setAdapter(postCommentsListAdapter);

        Callback<List<Comment>> responseCallback = new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                for (Comment comment : response.body()) {
                    addPostComment(comment);
                }
                updatePostComments();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
            }
        };

        apiService.getCommentsByPostId(postId, responseCallback);
    }

    private void showPostDetail(Post post) {
        TextView postTitle = findViewById(R.id.postTitle);
        TextView postBody = findViewById(R.id.postBody);
        postTitle.setText(post.getTitle());
        postBody.setText(post.getBody());
    }

    private void addPostComment(Comment comment) {
        postComments.add(comment);
    }

    private void updatePostComments() {
        postCommentsListAdapter.notifyDataSetChanged();
        postCommentsHeader.setVisibility(View.VISIBLE);
    }
}
