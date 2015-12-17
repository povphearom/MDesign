package com.example.phearom.mdesign.UI.binder;

import com.example.phearom.mdesign.UI.viewmodel.MainViewModel;
import com.example.phearom.mdesign.UI.viewmodel.SuperMainViewModel;
import com.example.phearom.mdesign.adapter.binder.ConditionalDataBinder;

public class SuperMainBinder extends ConditionalDataBinder<MainViewModel>
{
    public SuperMainBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(MainViewModel model)
    {
        return model instanceof SuperMainViewModel;
    }
}