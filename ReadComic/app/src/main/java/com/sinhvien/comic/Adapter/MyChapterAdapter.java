package com.sinhvien.comic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinhvien.comic.Common.Common;
import com.sinhvien.comic.Interface.IRecylerItemClickListener;
import com.sinhvien.comic.Model.Chapter;
import com.sinhvien.comic.R;
import com.sinhvien.comic.ViewComicActivity;

import java.util.List;


public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {
    Context context;
    List<Chapter> chapterList;
    LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        inflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.chapter_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_chapter_numb.setText(chapterList.get(i).Name);

        myViewHolder.setRecylerItemClickListener(new IRecylerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.chapterSelected = chapterList.get(position);
                Common.chapterIndex = position;
                context.startActivity(new Intent(context, ViewComicActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_chapter_numb;
        IRecylerItemClickListener recylerItemClickListener;

        public void setRecylerItemClickListener(IRecylerItemClickListener recylerItemClickListener) {
            this.recylerItemClickListener = recylerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_chapter_numb = itemView.findViewById(R.id.txt_chapter_numb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recylerItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
