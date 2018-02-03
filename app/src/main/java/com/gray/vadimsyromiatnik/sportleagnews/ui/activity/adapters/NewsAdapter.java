package com.gray.vadimsyromiatnik.sportleagnews.ui.activity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.models.NewsList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<NewsList> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitleItemNews)TextView tvTitleItemNews;
        @BindView(R.id.tvBodyNews)TextView tvBodyNews;
        @BindView(R.id.tvLinkNews)TextView tvLinkNews;
        @BindView(R.id.tvPhotoNews)TextView tvPhotoNews;
        @BindView(R.id.tvSubTitle)TextView tvSubTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

    public NewsAdapter(ArrayList <NewsList> dataset) {
        mDataset = dataset;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitleItemNews.setText(mDataset.get(position).getTitle());
        holder.tvSubTitle.setText(mDataset.get(position).getSubtitle());
        holder.tvBodyNews.setText(mDataset.get(position).getBody());
        holder.tvLinkNews.setText(mDataset.get(position).getLink());
        holder.tvPhotoNews.setText(mDataset.get(position).getPhoto());

        holder.tvTitleItemNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvSubTitle.setVisibility(View.GONE);
                holder.tvBodyNews.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}