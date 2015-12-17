package com.example.phearom.mdesign.UI.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.example.phearom.mdesign.UI.model.FileInfo;

public class MainsViewModel extends BaseObservable
{
    @Bindable
    public ObservableArrayList<MainViewModel> files;

    public MainsViewModel()
    {
        this.files = new ObservableArrayList<>();
    }

    public void addData(String name, int image)
    {
        this.files.add(new MainViewModel(new FileInfo(name, image)));
    }
}