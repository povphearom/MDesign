package com.example.phearom.mdesign.UI.binder;

import com.example.phearom.mdesign.UI.model.FileInfo;
import com.example.phearom.mdesign.adapter.binder.ConditionalDataBinder;

public class MainBinder extends ConditionalDataBinder<FileInfo>
{
    public MainBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(FileInfo model)
    {
        return true;
    }
}