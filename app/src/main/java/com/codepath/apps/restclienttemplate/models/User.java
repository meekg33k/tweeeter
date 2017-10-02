package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TweeeterDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Uduak Obong-Eren
 * @since 9/29/17.
 */

@Table(database = TweeeterDB.class)
public class User extends BaseModel {

    @PrimaryKey
    @Column
    Long id;
    @Column
    String name;
    @Column
    String screenName;
    @Column
    String profileImageURL;

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public User(JSONObject object){
        super();

        try {
            this.id = object.getLong("id");
            this.name = object.getString("name");
            this.screenName = object.getString("screen_name");
            this.profileImageURL = object.getString("profile_image_url_https");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
