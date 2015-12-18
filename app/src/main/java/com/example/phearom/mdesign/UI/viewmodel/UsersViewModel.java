package com.example.phearom.mdesign.UI.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.example.phearom.mdesign.UI.model.User;

public class UsersViewModel extends BaseObservable {

    @Bindable
    public ObservableArrayList<UserViewModel> users;

    public UsersViewModel() {
        this.users = new ObservableArrayList<>();
    }

    public void addUser(String rank, String population, String country, String image) {
        this.users.add(new UserViewModel(new User(rank, population, country, image)));
    }
}