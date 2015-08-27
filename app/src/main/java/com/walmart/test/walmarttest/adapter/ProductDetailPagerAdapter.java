package com.walmart.test.walmarttest.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.walmart.test.walmarttest.data.Product;
import com.walmart.test.walmarttest.fragments.ProductDetailFragment;

import java.util.List;

/**
 * Created by muppallav on 8/26/15.
 */
public class ProductDetailPagerAdapter extends FragmentPagerAdapter {

    List<Product> products;
    SparseArray<ProductDetailFragment> productFragmentArray = new SparseArray<>();

    public ProductDetailPagerAdapter(FragmentManager fm, List<Product> products) {
        super(fm);
        this.products = products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductDetailFragment getItem(int i) {
        if (products.size() <= i)
            throw new IllegalArgumentException("position " + i + " is outside the bounds of the article array");
        ProductDetailFragment fragment = productFragmentArray.get(i);
        if (fragment == null) {
            fragment = ProductDetailFragment.create(products.get(i));
            productFragmentArray.put(i, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return products != null ? products.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        productFragmentArray.remove(position);
    }

}
