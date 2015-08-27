package com.walmart.test.walmarttest.data;

import java.io.Serializable;

/**
 * Created by muppallav on 8/26/15.
 */
public class Product implements Serializable {
    private String productId; //Unique Id of the product
    private String productName; //Product Name
    private String shortDescription; //Short Description of the product
    private String longDescription; //Long Description of the product
    private String price; //Product price
    private String productImage; //Image url for the product
    private float reviewRating; //Average review rating for the product
    private int reviewCount; //Number of reviews
    private boolean inStock; // Returns true if item in stock

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
