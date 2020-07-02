package com.example.btlandroid.view.recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.utils.SaveCurrentMovie;
import com.example.btlandroid.view.MovieDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListSimilarAdapter extends RecyclerView.Adapter<ListSimilarAdapter.ViewHolder> {

    ArrayList<Movie> listSimilar;
    Context context;

    public ListSimilarAdapter(ArrayList<Movie> listSimilar, Context context) {
        this.listSimilar = listSimilar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_similar_movie, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.similarContainer.getLayoutParams().width = 300;
        Picasso.get().load(Constant.BASE_URL_IMAGE + listSimilar.get(position).getPosterPath()).into(holder.similarImage);
        holder.similarName.setText(listSimilar.get(position).getName());
        holder.similarContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetail.class);
            intent.putExtra("movie", listSimilar.get(position));
            SaveCurrentMovie.setCurrentMovie(listSimilar.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listSimilar.size();
    }

    public void setList(ArrayList<Movie> listSimilar) {
        this.listSimilar = listSimilar;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout similarContainer;
        ImageView similarImage;
        TextView similarName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            similarContainer = itemView.findViewById(R.id.similarContainer);
            similarImage = itemView.findViewById(R.id.similaImage);
            similarName = itemView.findViewById(R.id.similarName);
        }
    }
}
