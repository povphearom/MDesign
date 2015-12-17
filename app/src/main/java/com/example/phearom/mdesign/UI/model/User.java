package com.example.phearom.mdesign.UI.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

public class User
{
    private String rank;
    private String population;
    private  String country;
    private  String imageUrl;

    public User(String rank, String population, String country, String flag) {
        this.rank = rank;
        this.population = population;
        this.country = country;
        this.imageUrl = flag;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Ion.with(view.getContext()).load(imageUrl).intoImageView(view);
    }
}