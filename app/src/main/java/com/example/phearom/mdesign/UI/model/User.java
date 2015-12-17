package com.example.phearom.mdesign.UI.model;

public class User
{
    private  String firstName;
    private  String lastName;

    public User(String firstname, String lastname)
    {
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}