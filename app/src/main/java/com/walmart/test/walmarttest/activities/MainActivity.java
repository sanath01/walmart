package com.walmart.test.walmarttest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.walmart.test.walmarttest.R;
import com.walmart.test.walmarttest.WalmartApplication;
import com.walmart.test.walmarttest.adapter.ProductListAdapter;
import com.walmart.test.walmarttest.data.Product;
import com.walmart.test.walmarttest.data.ProductListResponse;
import com.walmart.test.walmarttest.listener.OnProductsReadyListener;
import com.walmart.test.walmarttest.network.ProductsAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnProductsReadyListener {

    ListView productListView;
    ProductListAdapter productListAdapter;
    List<Product> productList;
    FrameLayout loadingCuratin;
    ProgressBar productsLoadingprogressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productListView = (ListView) findViewById(R.id.product_list_view);
        loadingCuratin = (FrameLayout) findViewById(R.id.loading_layout);
        productsLoadingprogressBar = (ProgressBar) findViewById(R.id.progressBar_list);
        productListAdapter = new ProductListAdapter(this);
        productListView.setAdapter(productListAdapter);
        productList = WalmartApplication.getInstance().getCache().getProductDataList();
        if (productList == null
                || productList.size() == 0) {
            loadingCuratin.setVisibility(View.VISIBLE);
            getMoreProducts();
        } else {
            productListAdapter.setProductList(WalmartApplication.getInstance().getCache().getProductDataList());
        }

        productListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                final int lastItem = productListView.getAdapter().getCount() - 1;
                final int lastVisiblePos = productListView.getLastVisiblePosition();
                if (lastItem > 1) {
                    if (lastVisiblePos == lastItem && WalmartApplication.getInstance().getCache().hasMoreProducts()) {
                        WalmartApplication.getInstance().getCache().PAGE_NUMBER++;
                        getMoreProducts();
                    }

                }

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        productListView.setOnItemClickListener(this);

    }


    public void getMoreProducts() {
        productsLoadingprogressBar.setVisibility(View.VISIBLE);
        new ProductsAsyncTask(this).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT_ID_PARAM, i);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


    @Override
    public void onDownloadComplete(ProductListResponse productListResponse) {
        loadingCuratin.setVisibility(View.GONE);
        productsLoadingprogressBar.setVisibility(View.GONE);
        productList = WalmartApplication.getInstance().getCache().getProductDataList();
        productListAdapter.setProductList(productList);
        productListAdapter.notifyDataSetChanged();
    }
}
