package com.gray.vadimsyromiatnik.sportleagnews.ui.activity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.ui.App;
import com.gray.vadimsyromiatnik.sportleagnews.ui.models.NewsList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, Animation.AnimationListener {

    private final static int VIEW_MAIN = 0;
    private final static int VIEW_LAST = 1;

    ArrayList<NewsList> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitleItemNews)TextView tvTitleItemNews;
        @BindView(R.id.tvBodyNews)TextView tvBodyNews;
        @BindView(R.id.tvLinkNews)TextView tvLinkNews;
        @BindView(R.id.tvPhotoNews)TextView tvPhotoNews;
        @BindView(R.id.tvSubTitle)TextView tvSubTitle;
        @BindView(R.id.imageClose)ImageView imageClose;
        @BindView(R.id.imageInformation)ImageView imageInformation ;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


    public static class ViewHolderAnimation extends RecyclerView.ViewHolder {

        @BindView(R.id.imageAnimationFootball)ImageView imageAnimationFootball;

        public ViewHolderAnimation(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


    public NewsAdapter(ArrayList <NewsList> dataset) {
        mDataset = dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_MAIN){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animation, parent, false);
            return new ViewHolderAnimation(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;

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
            holder.imageInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvSubTitle.setVisibility(View.GONE);
                    holder.imageInformation.setVisibility(View.INVISIBLE);
                    holder.tvBodyNews.setVisibility(View.VISIBLE);
                    holder.imageClose.setVisibility(View.VISIBLE);
                }
            });
            holder.imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvSubTitle.setVisibility(View.VISIBLE);
                    holder.tvBodyNews.setVisibility(View.GONE);
                    holder.imageClose.setVisibility(View.GONE);
                    holder.imageInformation.setVisibility(View.VISIBLE);
                }
            });
        } else if (viewHolder instanceof ViewHolderAnimation) {
            ViewHolderAnimation holder = (ViewHolderAnimation) viewHolder;
                    Animation animation = AnimationUtils.loadAnimation(App.getInstance(), R.anim.anim_football);
                    holder.imageAnimationFootball.setAnimation(animation);
                  animation.setAnimationListener(this);

        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }



    @Override
    public void onClick(View v) {

    }


    @Override
    public int getItemCount() {
        return mDataset.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == mDataset.size() ? VIEW_LAST : VIEW_MAIN;
    }
}