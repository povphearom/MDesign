package com.example.phearom.mdesign.UI.viewmodel;

import android.databinding.BaseObservable;

import com.example.phearom.mdesign.UI.model.User;

public class UserViewModel extends BaseObservable
{
    private final User model;

    public UserViewModel(User model)
    {
        this.model = model;
    }

    public User getModel()
    {
        return model;
    }

    public String getFirstName()
    {
        return model.getFirstName();
    }

    public String getLastName()
    {
        return model.getLastName();
    }
}