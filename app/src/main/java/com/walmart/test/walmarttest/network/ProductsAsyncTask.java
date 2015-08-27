package com.walmart.test.walmarttest.network;

import android.os.AsyncTask;
import android.util.Log;

import com.walmart.test.walmarttest.WalmartApplication;
import com.walmart.test.walmarttest.data.ProductListResponse;
import com.walmart.test.walmarttest.listener.OnProductsReadyListener;

/**
 * Created by muppallav on 8/27/15.
 */
public class ProductsAsyncTask extends AsyncTask<Void, Void, ProductListResponse> {

    private static final String TAG = ProductsAsyncTask.class.getName();

    private OnProductsReadyListener listener;

    public ProductsAsyncTask(OnProductsReadyListener listener) {
        this.listener = listener;
    }

    @Override
    protected ProductListResponse doInBackground(Void... voids) {
        Log.d(TAG, "Starting to Download products");
        return NetworkUtil.getProductListByPage(WalmartApplication.getInstance().getCache().PAGE_NUMBER, WalmartApplication.PRODUCTS_PER_PAGE);
    }

    @Override
    protected void onPostExecute(ProductListResponse productListResponse) {
        if (productListResponse != null) {
            WalmartApplication.getInstance().getCache().addToProductDataList(productListResponse.getProducts());
            WalmartApplication.getInstance().getCache().setTotalProducts(productListResponse.getTotalProducts());
        }
        listener.onDownloadComplete(productListResponse);
        super.onPostExecute(productListResponse);
    }
}
