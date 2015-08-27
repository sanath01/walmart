package com.walmart.test.walmarttest;

import android.app.Application;

import com.walmart.test.walmarttest.data.Cache;

/**
 * Created by muppallav on 8/26/15.
 */
public class WalmartApplication extends Application {

    public static final int PRODUCTS_PER_PAGE = 10;
    private static WalmartApplication instance;
    private Cache cache;

    public static WalmartApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        cache = new Cache();

    }

    public Cache getCache() {
        return cache;
    }
}
