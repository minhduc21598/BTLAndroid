package com.example.btlandroid.view.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder>{

    ArrayList<Movie> listMovies;
    Context context;

    public ListMovieAdapter(ArrayList<Movie> listMovies, Context context) {
        this.listMovies = listMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(Constant.BASE_URL_IMAGE + listMovies.get(position).getPosterPath()).into(holder.posterMovie);
        holder.container.getLayoutParams().width = (Constant.WIDTH_SCREEN - 15) / 2;
        holder.movieName.setBackgroundColor(Color.argb((float) 0.5,0,0,0));
        holder.movieName.setText(listMovies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieName;
        ImageView posterMovie;
        LinearLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            posterMovie = itemView.findViewById(R.id.posterMovie);
            container = itemView.findViewById(R.id.moviecontainer);
        }
    }

}
