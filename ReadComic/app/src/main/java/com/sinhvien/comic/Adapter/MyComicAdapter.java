package com.sinhvien.comic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.comic.ChaptersActivity;
import com.sinhvien.comic.Common.Common;
import com.sinhvien.comic.Interface.IRecylerItemClickListener;
import com.sinhvien.comic.Model.Comic;
import com.sinhvien.comic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {

    Context context;
    List<Comic> comicList;
    LayoutInflater inflater;

    public MyComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.comic_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load(comicList.get(i).Image).into(myViewHolder.comic_image);
        myViewHolder.comic_name.setText(comicList.get(i).Name);

        myViewHolder.setRecylerItemClickListener(new IRecylerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.comicSelected = comicList.get(position);
                context.startActivity(new Intent(context, ChaptersActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView comic_name;
        ImageView comic_image;

        IRecylerItemClickListener recylerItemClickListener;

        public void setRecylerItemClickListener(IRecylerItemClickListener recylerItemClickListener) {
            this.recylerItemClickListener = recylerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comic_name = (TextView)itemView.findViewById(R.id.comic_name);
            comic_image=(ImageView) itemView.findViewById(R.id.image_comic);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recylerItemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
