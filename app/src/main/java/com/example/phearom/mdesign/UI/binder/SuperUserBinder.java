package com.example.phearom.mdesign.UI.binder;

import com.example.phearom.mdesign.UI.viewmodel.SuperUserViewModel;
import com.example.phearom.mdesign.UI.viewmodel.UserViewModel;
import com.example.phearom.mdesign.adapter.binder.ConditionalDataBinder;

public class SuperUserBinder extends ConditionalDataBinder<UserViewModel>
{
    public SuperUserBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(UserViewModel model)
    {
        return model instanceof SuperUserViewModel;
    }
}