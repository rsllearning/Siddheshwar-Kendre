package com.android.recyclerviewassignment;

public class ItemClass {

    // Integers assigned to each layout
    // these are declared static so that they can
    // be accessed from the class name itself
    // And final so that they are not modified later
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;

    // This variable ViewType specifies
    // which out of the two layouts
    // is expected in the given item
    private int viewType;

    // String variable to hold the TextView
    // of the first item.
    private String Name,Followers,Contribution;

    // public constructor for the second layout
    public ItemClass(String Name, String Followers,
                     String Contribution,int viewType)
    {
        this.Name = Name;
        this.Followers = Followers;
        this.Contribution = Contribution;
        this.viewType = viewType;
    }

    public String getFollowers() {
        return Followers;
    }

    public void setFollowers(String followers) {
        Followers = followers;
    }

    public String getContribution() {
        return Contribution;
    }

    public void setContribution(String contribution) {
        Contribution = contribution;
    }

    // getter and setter methods for the text variable

    public String getName() { return Name; }

    public void setName(String name) { this.Name = name; }

    // Variables for the item of second layout
    private String Name2,Followers2,Contribution2,Location;


    // public constructor for the second layout
    public ItemClass(int viewType, String Name2, String Followers2,
                     String Contribution2,String Location)
    {
        this.viewType = viewType;
        this.Location = Location;
        this.Contribution2 = Contribution2;
        this.Name2 = Name2;
        this.Followers2 = Followers2;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public String getFollowers2() {
        return Followers2;
    }

    public void setFollowers2(String followers2) {
        Followers2 = followers2;
    }

    public String getContribution2() {
        return Contribution2;
    }

    public void setContribution2(String contribution2) {
        Contribution2 = contribution2;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getViewType() { return viewType; }

    public void setViewType(int viewType)
    {
        this.viewType = viewType;
    }
}
