package com.walmart.test.walmarttest.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muppallav on 8/26/15.
 */
public class Cache {

    public int PAGE_NUMBER = 1;
    private List<Product> productDataList;
    private int totalProducts;

    public List<Product> getProductDataList() {
        if (productDataList == null) {
            return new ArrayList<>();
        }
        return productDataList;
    }

    public void addToProductDataList(List<Product> newList) {
        if (productDataList == null) {
            productDataList = new ArrayList<>();
        }
        productDataList.addAll(newList);
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public boolean hasMoreProducts() {
        return productDataList != null
                && productDataList.size() < totalProducts;
    }

}
