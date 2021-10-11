package com.devendrasaini.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    Context context;
    List<PhotoModel> photos;

    public PhotoListAdapter(Context context, List<PhotoModel> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.photo_list_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(photos.get(position).getDownloadUrl())
                .into(holder.wallPaper);
        holder.authorName.setText("Author: " + photos.get(position).getAuthor());
        holder.wallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Author: " + photos.get(position).getAuthor(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wallPaper;
        TextView authorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wallPaper = itemView.findViewById(R.id.imageUrl);
            authorName = itemView.findViewById(R.id.textAuthor);
        }
    }
}
