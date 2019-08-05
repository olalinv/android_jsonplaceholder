package com.robottitto.jsonplaceholder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.jsonplaceholder.post.PostsActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent postActivityIntent = new Intent(MainActivity.this, PostsActivity.class);
        startActivity(postActivityIntent);
    }
}