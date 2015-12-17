package com.example.phearom.mdesign.UI.binder;

import com.example.phearom.mdesign.UI.viewmodel.UserViewModel;
import com.example.phearom.mdesign.adapter.binder.ConditionalDataBinder;

public class UserBinder extends ConditionalDataBinder<UserViewModel>
{
    public UserBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(UserViewModel model)
    {
        return true;
    }
}