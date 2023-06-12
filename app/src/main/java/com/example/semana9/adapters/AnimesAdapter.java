package com.example.semana9.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9.Anime;
import com.example.semana9.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimesAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Anime> items;
    public List<Anime> getItems() {
        return items;
    }
    public AnimesAdapter(List<Anime> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_string, parent, false);
        AnimesAdapter.AnimeViewHolder viewHolder = new AnimesAdapter.AnimeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Anime item = items.get(position);
        View view = holder.itemView;

        TextView tvName = view.findViewById(R.id.tvName);
        ImageView imageView = view.findViewById(R.id.imageView);
        tvName.setText(item.getDescripcion());
        Picasso.get().load(item.getImagenes()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder {

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
