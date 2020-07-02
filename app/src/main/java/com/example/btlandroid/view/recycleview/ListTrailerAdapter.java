package com.example.btlandroid.view.recycleview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.btlandroid.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTrailerAdapter extends RecyclerView.Adapter<ListTrailerAdapter.ViewHolder> {

    ArrayList<Trailer> listTrailers;
    Context context;

    public ListTrailerAdapter(ArrayList<Trailer> listTrailers, Context context) {
        this.listTrailers = listTrailers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.thumbnail_video, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(Constant.BASE_URL_IMAGE + listTrailers.get(position).getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.getLayoutParams().width = 350;
        holder.name.setText(listTrailers.get(position).getName());
        holder.videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(listTrailers.get(position).getUrl()));
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTrailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout videoLayout;
        ImageView thumbnail;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            name = itemView.findViewById(R.id.videoName);
            videoLayout = itemView.findViewById(R.id.trailer);
        }
    }
}
