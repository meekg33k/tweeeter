package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TweeeterDB;
import com.codepath.apps.restclienttemplate.utils.AppUtils;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * https://github.com/codepath/android-rest-client-template#1-configure-the-rest-client
 */

@Table(database = TweeeterDB.class)
public class Tweet extends BaseModel {
    // Define database columns and associated fields
    @PrimaryKey
    @Column
    Long id;
    @Column
    String timestamp;
    @Column
    String body;
    @ForeignKey
    @Column
    User user;


    public Tweet() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public Tweet(JSONObject object){
        super();

        try {
            this.id = object.getLong("id");
            this.timestamp = AppUtils.getRelativeTimeAgo(object.getString("created_at"));
            this.body = object.getString("text");
            this.user = new User(object.getJSONObject("user"));
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tweet tweet = new Tweet(tweetJson);
            tweet.save();
            tweets.add(tweet);
        }
        return tweets;
    }
}