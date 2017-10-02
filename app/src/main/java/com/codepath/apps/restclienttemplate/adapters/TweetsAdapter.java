package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * @author Uduak Obong-Eren
 * @since 10/1/17.
 */

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivUserImage;
        public TextView tvUser;
        public TextView tvUserName;
        public TextView tvDateSince;
        public TextView tvTweetBody;

        public ViewHolder(View itemView) {
            super(itemView);
            ivUserImage = (ImageView) itemView.findViewById(R.id.userImage);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvDateSince = (TextView) itemView.findViewById(R.id.tvCountTime);
            tvTweetBody = (TextView) itemView.findViewById(R.id.tvTweetBody);
        }

        /*public void bind(final Tweet item, final OnNewsItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNewsItemClick(item);
                }
            });
        }*/
    }

    private List<Tweet> mTweets;
    private Context mContext;
    //private final OnNewsItemClickListener listener;


    public TweetsAdapter(Context context, List<Tweet> tweetsList) {
        mTweets = tweetsList;
        mContext = context;
        //listener = newsItemClickListener;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public TweetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(TweetsAdapter.ViewHolder viewHolder, int position) {
        Tweet tweet = mTweets.get(position);

        //tweet.getUser().
        //viewHolder.bind(tweet, listener);

        User user = tweet.getUser();

        if (user != null){
            viewHolder.tvUser.setText(user.getName());

            Resources res = mContext.getResources();
            String userName = res.getString(R.string.username, user.getScreenName());
            viewHolder.tvUserName.setText(userName);

            viewHolder.tvUser.setText(user.getName());
            viewHolder.tvTweetBody.setText(tweet.getBody());
            viewHolder.tvDateSince.setText(tweet.getTimestamp());

            Picasso.with(mContext).load(user.getProfileImageURL())
                    .transform(new RoundedCornersTransformation(5, 5)).into(viewHolder.ivUserImage);
        }

    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

}
