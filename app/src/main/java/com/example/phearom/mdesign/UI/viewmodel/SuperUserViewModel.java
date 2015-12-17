package com.example.phearom.mdesign.UI.viewmodel;

import com.example.phearom.mdesign.UI.model.User;

public class SuperUserViewModel extends UserViewModel
{
    public SuperUserViewModel(User model)
    {
        super(model);
    }

    public String getGroup()
    {
        return "Droid";
    }
}