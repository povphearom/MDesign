package com.example.phearom.mdesign.UI.viewmodel;

import com.example.phearom.mdesign.UI.model.FileInfo;

public class SuperMainViewModel extends MainViewModel
{
    public SuperMainViewModel(FileInfo model)
    {
        super(model);
    }

    public String getGroup()
    {
        return "Droid";
    }
}