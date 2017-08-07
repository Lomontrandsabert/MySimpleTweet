package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

//
public class TweetsArrayAdapter extends RecyclerView.Adapter<TweetsArrayAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public ImageView ivProfileImage;
            public TextView tvUserName;
            public TextView tvBody;
            public TextView tvDate;
            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
                tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
                tvBody = (TextView) itemView.findViewById(R.id.tvBody);
                tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            }
        }

        private List<Tweet> mTweets;
        //
        private Context mContext;

        public TweetsArrayAdapter(Context context, List<Tweet> tweet){
            mTweets = tweet;
            mContext = context;
        }
        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }

    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetsArrayAdapter.ViewHolder holder, int position) {

        // Get the data model based on position
        Tweet tweet = mTweets.get(position);

        // Set item views based on your views and data model
        TextView tvUser = holder.tvUserName;
        TextView tvBody = holder.tvBody;
        TextView tvDate = holder.tvDate;
        ImageView ivProfileImage= holder.ivProfileImage;
        tvUser.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        tvDate.setText(tweet.getCreatedAt());
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).placeholder(R.drawable.ic_launcher).resize(75, 75).into(ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }
}
