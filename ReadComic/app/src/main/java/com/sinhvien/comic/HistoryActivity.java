package com.sinhvien.comic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sinhvien.comic.Adapter.MyComicAdapter;
import com.sinhvien.comic.Model.Comic;

import java.util.ArrayList;
import java.util.List;

import static com.sinhvien.comic.Common.Common.comicHistory;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recycler_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        List<Comic> comic_history = new ArrayList<>();
        for (Comic comicHistory : comicHistory)
            if (!comic_history.contains(comicHistory))
                comic_history.add(comicHistory);

        recycler_history = findViewById(R.id.recycler_history);
        recycler_history.setHasFixedSize(true);
        recycler_history.setLayoutManager(new GridLayoutManager(this, 1));
        recycler_history.setAdapter(new MyComicAdapter(getBaseContext(), comic_history));
    }
}
