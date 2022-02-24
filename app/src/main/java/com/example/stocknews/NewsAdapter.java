package com.example.stocknews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsModel> newsModelList=new ArrayList<>();
    Context context;

    public NewsAdapter(List<NewsModel> newsModelList, Context context) {
        this.newsModelList = newsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.news_item_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url=newsModelList.get(position).getUrlImg();
        String title=newsModelList.get(position).getTitle();
        holder.setData(url,title);

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titletxt;

        public ViewHolder(View itemView){
        super(itemView);
        imageView=itemView.findViewById(R.id.img1);
        titletxt=itemView.findViewById(R.id.txt1);


    }

        public void setData(String url, String title) {
            Glide.with(context).load(url).into(imageView);
            titletxt.setText(title);

        }
    }

}
