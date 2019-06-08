package com.sinhvien.comic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.sinhvien.comic.Adapter.MyComicAdapter;
import com.sinhvien.comic.Common.Common;
import com.sinhvien.comic.Model.Comic;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recycler_categories;
    TextView edt_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final List<Comic> comic_category = new ArrayList<>();
        recycler_categories = findViewById(R.id.recycler_categories);
        edt_category=findViewById(R.id.edt_category);

        for (Comic comic: Common.comicList)
            comic_category.add(comic);
        recycler_categories.setHasFixedSize(true);
        recycler_categories.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));
        recycler_categories.setAdapter(new MyComicAdapter(getBaseContext(),comic_category));
    }
}
