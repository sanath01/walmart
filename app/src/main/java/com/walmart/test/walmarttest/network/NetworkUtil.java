package com.walmart.test.walmarttest.network;

import android.util.Log;

import com.google.gson.Gson;
import com.walmart.test.walmarttest.data.ProductListResponse;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by muppallav on 8/26/15.
 */
public class NetworkUtil {

    public static final String PRODUCTS_API_URL_TEMPLATE = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/walmartproducts/522acdc7-94e8-4094-aec1-7b38a0e51f77/%d/%d";
    private static final String TAG = NetworkUtil.class.getName();
    private static Gson gson = new Gson();

    /*
    * Calls the product API to get the
    * */
    public static ProductListResponse getProductListByPage(int pageNumber, int numProductsPerPage) {
        ProductListResponse productListResponse = new ProductListResponse();
        HttpURLConnection httpConnection = null;
        String formattedUrl = getProductApiURL(pageNumber, numProductsPerPage);
        try {
            URL url = new URL(formattedUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                byte[] data = IOUtils.toByteArray(httpConnection.getInputStream());
                productListResponse = gson.fromJson(new InputStreamReader(new ByteArrayInputStream(data)), ProductListResponse.class);
            }
        } catch (MalformedInputException e) {
            Log.e(TAG, "URL is invali please verify");
        } catch (Exception e) {
            Log.e(TAG, "Got exceptiong while fetching product list json", e);
        } finally {
            if (httpConnection != null) {
                try {
                    httpConnection.disconnect();
                } catch (Exception e) {
                    Log.e(TAG, "Weirdness while disconnecting", e);
                }
            }
        }
        return productListResponse;

    }

    public static String getProductApiURL(int pageNum, int pageSize) {
        return String.format(PRODUCTS_API_URL_TEMPLATE, pageNum, pageSize);
    }
}
