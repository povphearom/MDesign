package com.example.phearom.mdesign.UI.viewmodel;

import android.databinding.BaseObservable;

import com.example.phearom.mdesign.UI.model.FileInfo;

public class MainViewModel extends BaseObservable
{
    private final FileInfo model;

    public MainViewModel(FileInfo model)
    {
        this.model = model;
    }

    public FileInfo getModel()
    {
        return model;
    }

    public String getTitle()
    {
        return model.getTitle();
    }

    public int getImage()
    {
        return model.getImage();
    }
}