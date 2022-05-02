package com.android.shoppingapp;

/**
 * Model class to store values of Game list API response.
 */
public class DataModel {

    private String gameName,description,price,category,image;

    public String getImage() {
        return image;
    }

    public DataModel(String gameName, String description, String price, String category, String image) {
        this.gameName = gameName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public DataModel(String gameName, String description, String price) {
        this.gameName = gameName;
        this.description = description;
        this.price = price;
    }
    public DataModel(String gameName, String description, String price, String category) {
        this.gameName = gameName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    DataModel(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
