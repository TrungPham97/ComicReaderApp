package com.sinhvien.comic.Interface;

import com.sinhvien.comic.Model.Comic;

import java.util.List;

public interface IComicLoadDone {
    void onComicLoadDoneListener(List<Comic> comicList);
}
