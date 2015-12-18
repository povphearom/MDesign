package com.example.phearom.mdesign.UI.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.phearom.mdesign.BR;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class User extends BaseObservable{
    private String rank;
    private String population;
    private String country;
    private String imageUrl;
    private static boolean Loading;

//    private static User Single
    public User(String rank, String population, String country, String flag) {
        this.rank = rank;
        this.population = population;
        this.country = country;
        this.imageUrl = flag;
    }

    public void setLoading(boolean loading){
        Loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public boolean isLoading(){
        return Loading;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageView view, String imageUrl) {
//        Loading = true;
        Ion.with(view.getContext()).load(imageUrl).intoImageView(view).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView result) {
                Loading = false;
            }
        });
    }
}