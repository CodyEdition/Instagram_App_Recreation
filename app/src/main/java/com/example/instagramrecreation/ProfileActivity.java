package com.example.instagramrecreation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Highlights RecyclerView
        RecyclerView highlightsRecycler = findViewById(R.id.recycler_highlights);
        highlightsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<String> highlights = new ArrayList<>();
        for (int i = 0; i < 6; i++) highlights.add("Highlight " + (i+1));
        highlightsRecycler.setAdapter(new HighlightsAdapter(highlights));

        // Posts RecyclerView
        RecyclerView postsRecycler = findViewById(R.id.recycler_posts);
        postsRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        List<Integer> posts = new ArrayList<>();
        for (int i = 0; i < 12; i++) posts.add(android.R.drawable.ic_menu_gallery);
        postsRecycler.setAdapter(new PostsAdapter(posts));
    }
}

// Simple Adapter for Highlights
class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.HighlightViewHolder> {
    private final List<String> highlights;
    HighlightsAdapter(List<String> highlights) { this.highlights = highlights; }
    @Override
    public HighlightViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view = android.view.LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new HighlightViewHolder(view);
    }
    @Override
    public void onBindViewHolder(HighlightViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(highlights.get(position));
    }
    @Override
    public int getItemCount() { return highlights.size(); }
    static class HighlightViewHolder extends RecyclerView.ViewHolder {
        HighlightViewHolder(android.view.View itemView) { super(itemView); }
    }
}

// Simple Adapter for Posts
class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private final List<Integer> posts;
    PostsAdapter(List<Integer> posts) { this.posts = posts; }
    @Override
    public PostViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        int size = parent.getResources().getDisplayMetrics().widthPixels / 3;
        imageView.setLayoutParams(new RecyclerView.LayoutParams(size, size));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(2,2,2,2);
        return new PostViewHolder(imageView);
    }
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.imageView.setImageResource(posts.get(position));
    }
    @Override
    public int getItemCount() { return posts.size(); }
    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        PostViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
} 