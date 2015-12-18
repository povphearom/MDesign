package com.example.phearom.mdesign.UI.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.phearom.mdesign.UI.model.User;

public class UserViewModel extends BaseObservable
{
    private final User model;

    public UserViewModel(User model)
    {
        this.model = model;
    }

    public User getModel() {
        return model;
    }

    public String getCountry()
    {
        return model.getCountry();
    }

    public String getImageUrl()
    {
        return model.getImageUrl();
    }

    public String getRank(){
        return model.getRank();
    }

    public String getPopulation(){
        return model.getPopulation();
    }

    @Bindable
    public boolean isLoading(){
        return model.isLoading();
    }
}