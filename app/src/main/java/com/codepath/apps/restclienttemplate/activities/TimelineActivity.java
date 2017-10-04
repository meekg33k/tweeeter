package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweeeterApp;
import com.codepath.apps.restclienttemplate.TweeeterClient;
import com.codepath.apps.restclienttemplate.adapters.TweetsAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TweeeterClient client;
    private TweetsAdapter mTweetsAdapter;
    private ArrayList<Tweet> mTweetList;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTweetList = new ArrayList<>();
        mTweetsAdapter = new TweetsAdapter(this, mTweetList);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvTweets);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRecyclerView.setAdapter(mTweetsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateTimeline(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        client = TweeeterApp.getRestClient();
        populateTimeline(0);
    }

    private void populateTimeline(int page){

        progressBar.setVisibility(ProgressBar.VISIBLE);

        client.getHomeTimeline(page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray tweetResults) {
                Log.i("apiSuccess", tweetResults.toString());
                mTweetList.clear();
                mTweetsAdapter.addAll(Tweet.fromJson(tweetResults));
                swipeContainer.setRefreshing(false);
                progressBar.setVisibility(ProgressBar.INVISIBLE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                Log.e("apiError", "Error retrieving data from API call");
            }
        });
    }


    /*private void demoWithStetho(){
        OkHttpClient httpClient = new OkHttpClient();

        // Add Stetho interceptor
        httpClient.networkInterceptors().add(new StethoInterceptor());

        try {
            // Fetch the contents of http://httpbin.org/ip
            Response response = httpClient.newCall(
                    new Request.Builder().url("http://httpbin.org/ip").build()
            ).execute();

        } catch(IOException ioe) {
            Log.d("StethoTut", ioe.getMessage());
        }
    }*/

}
