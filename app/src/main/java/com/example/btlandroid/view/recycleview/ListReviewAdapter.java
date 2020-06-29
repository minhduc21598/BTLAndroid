package com.example.btlandroid.view.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlandroid.R;
import com.example.btlandroid.model.UserReview;

import java.util.ArrayList;

public class ListReviewAdapter extends RecyclerView.Adapter<ListReviewAdapter.ViewHolder>{

    ArrayList<UserReview> listReviews;
    Context context;

    public ListReviewAdapter(ArrayList<UserReview> listReviews, Context context) {
        this.listReviews = listReviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_review, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.author.setText(listReviews.get(position).getUser().getFullName());
        holder.comment.setText(listReviews.get(position).getUserComment());
    }

    @Override
    public int getItemCount() {
        return listReviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView author, comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
