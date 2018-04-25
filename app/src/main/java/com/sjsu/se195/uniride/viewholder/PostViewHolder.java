package com.sjsu.se195.uniride.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjsu.se195.uniride.PostDetailActivity;
import com.sjsu.se195.uniride.PostInfo;
import com.sjsu.se195.uniride.R;
import com.sjsu.se195.uniride.models.DriverOfferPost;
import com.sjsu.se195.uniride.models.Post;
import com.sjsu.se195.uniride.models.RideRequestPost;

import org.w3c.dom.Text;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView sourceView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView destinationView;
    public ImageView background;
    public TextView to;

    public TextView postTripDateText;
    public TextView postDateTimeText;

    public PostViewHolder(View itemView) {
        super(itemView);

        sourceView = (TextView) itemView.findViewById(R.id.post_source);
        authorView = (TextView) itemView.findViewById(R.id.post_cardview_author_name);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        destinationView = (TextView) itemView.findViewById(R.id.post_destination);
        background = (ImageView) itemView.findViewById(R.id.card_background);
        to = (TextView) itemView.findViewById(R.id.post_card_address_to);

        postTripDateText = itemView.findViewById(R.id.post_date);
        postDateTimeText = itemView.findViewById(R.id.post_time);
    }

    public void bindToPost(String name, Boolean postType, Post post, View.OnClickListener starClickListener) {
        authorView.setText(name);
        sourceView.setText(trimAddress(post.source));
        numStarsView.setText(String.valueOf(post.starCount));
        destinationView.setText(trimAddress(post.destination));
        String t ="to";
        to.setText(t);

        System.out.println("PostViewHolder:bindToPost: post.postType = " + post.postType);

        if (post.postType == Post.PostType.RIDER) {
            //...
        }
        else if (post.postType == Post.PostType.DRIVER) {
            //...
        }
        else if (post.postType == Post.PostType.CARPOOL) {
            sourceView.setText("CARPOOL: \n" + sourceView.getText()); // DEBUG ONLY.

            // TODO: add R.drawable.carpool_card??? view elements for # passengers, waypoints, trip time, etc.
        }

        postTripDateText.setText(PostInfo.getTripDateText(post));
        postDateTimeText.setText("Arrive at " + PostInfo.getArrivalDateTimeText(post));

        if(postType){
            background.setImageResource(R.drawable.driver_card);
        }
        else{
            background.setImageResource(R.drawable.rider_card);
        }
        starView.setOnClickListener(starClickListener);
    }

    private String trimAddress(String adr){
        String result = adr.substring(0, adr.indexOf(", CA"));
        String[] r = result.split(" ");
        boolean firstCharacter = false;
        if(r.length>1){
            result = "";
            for(int i = 0; i<r.length; i++){
                if(i==0){
                    if(r[0].matches("\\d+(?:\\.\\d+)?")){
                        continue;
                    }
                    else{
                        result = r[0] + " " + r[1];
                        firstCharacter = true;
                    }
                    continue;
                }
                if(i==1 && !firstCharacter){
                    result = r[1];
                }else{
                    result = result + " " + r[i];
                }
            }
        }
        return result;
    }
}
