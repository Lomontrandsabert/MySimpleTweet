package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.ParseException;

public class TimelineActivity extends ActionBarActivity {

    private TwitterClient client;
    ArrayList <Tweet> tweets;
    TweetsArrayAdapter aTweets;
    // private ListView lvTweets;
    private RecyclerView rvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        //showFragmentDialog();

        //
        //lvTweets = (ListView) findViewById(R.id.lvTweets);
        rvTweets = (RecyclerView) findViewById(R.id.rvTweets);
        //
        tweets = new ArrayList<>();
        //

        //
        // lvTweets.setAdapter(aTweets);
        //rvTweets.setAdapter(aTweets);
        //
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void showFragmentDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentDialog fragmentDialog = FragmentDialog.newInstance("ivProfilePhoto");
        fragmentDialog.show(fm, "fragment_tweet");
    }

    //
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
        //ArrayList tweets
            //Adapter aTweets;
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                //

                aTweets =new TweetsArrayAdapter(getApplicationContext(), tweets);
                rvTweets.setAdapter(aTweets);
                tweets.addAll(Tweet.fromJSONArray(json));
                rvTweets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
               /* aTweets = new TweetsArrayAdapter(getApplicationContext(), tweets);
                tweets.addAll(Tweet.fromJSONArray(json));
                rvTweets.setAdapter(aTweets);
                rvTweets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                aTweets.notifyDataSetChanged();*/

                Log.d("DEBUG", aTweets.toString());
            }

            //
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if(errorResponse != null)
                    Log.d("DEBUG", errorResponse.toString());

                if(throwable != null)
                    throwable.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        int id = item.getItemId();

        //
        if (id == R.id.action_settings) {
            showFragmentDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SendTweet(View view) {
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}