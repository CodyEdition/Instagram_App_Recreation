package com.example.instagramrecreation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Posts RecyclerView
        RecyclerView postsRecycler = findViewById(R.id.recycler_posts);
        postsRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        List<Integer> posts = new ArrayList<>();
        
        // Add only the three actual post images from the Instagram profile
        posts.add(R.drawable.post_music_video);
        posts.add(R.drawable.post_electronic_device);
        posts.add(R.drawable.post_remove_before_flight);
        
        postsRecycler.setAdapter(new PostsAdapter(posts));
    }
}

// Simple Adapter for Posts
class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private final List<Integer> posts;
    PostsAdapter(List<Integer> posts) { this.posts = posts; }
    @Override
    public PostViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        // Create a FrameLayout to hold both the image and carousel indicator
        android.widget.FrameLayout frameLayout = new android.widget.FrameLayout(parent.getContext());
        int size = parent.getResources().getDisplayMetrics().widthPixels / 3;
        frameLayout.setLayoutParams(new RecyclerView.LayoutParams(size, size));
        frameLayout.setPadding(1, 1, 1, 1);
        
        // Create the main image view
        ImageView imageView = new ImageView(parent.getContext());
        android.widget.FrameLayout.LayoutParams imageParams = new android.widget.FrameLayout.LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frameLayout.addView(imageView);
        
        return new PostViewHolder(frameLayout, imageView);
    }
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.imageView.setImageResource(posts.get(position));
        
        // Add carousel indicator to the second post (position 1)
        if (position == 1) {
            ImageView carouselIndicator = new ImageView(holder.itemView.getContext());
            android.widget.FrameLayout.LayoutParams indicatorParams = new android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
            );
            indicatorParams.gravity = android.view.Gravity.TOP | android.view.Gravity.END;
            indicatorParams.setMargins(0, 8, 8, 0);
            carouselIndicator.setLayoutParams(indicatorParams);
            carouselIndicator.setImageResource(R.drawable.ic_carousel_indicator);
            carouselIndicator.setScaleX(-0.8f); // Negative scaleX flips horizontally
            carouselIndicator.setScaleY(0.8f);
            ((android.widget.FrameLayout) holder.itemView).addView(carouselIndicator);
        }
    }
    @Override
    public int getItemCount() { return posts.size(); }
    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        PostViewHolder(android.view.View itemView, ImageView imageView) {
            super(itemView);
            this.imageView = imageView;
        }
    }
} 