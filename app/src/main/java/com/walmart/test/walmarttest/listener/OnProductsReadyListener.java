package com.walmart.test.walmarttest.listener;

import com.walmart.test.walmarttest.data.ProductListResponse;

/**
 * Created by muppallav on 8/27/15.
 */
public interface OnProductsReadyListener {
    void onDownloadComplete(ProductListResponse productListResponse);
}
