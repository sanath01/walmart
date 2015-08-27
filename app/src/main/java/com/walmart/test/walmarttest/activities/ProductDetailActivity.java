package com.walmart.test.walmarttest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.walmart.test.walmarttest.R;
import com.walmart.test.walmarttest.WalmartApplication;
import com.walmart.test.walmarttest.adapter.ProductDetailPagerAdapter;
import com.walmart.test.walmarttest.data.Product;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String PRODUCT_ID_PARAM = ProductDetailActivity.class.getSimpleName() + ".ProductId";
    public static final String PARAM_PRODUCT_CONTENT = ProductDetailActivity.class.getSimpleName() + ".ProductContent";
    private ViewPager productPager;
    private ProductDetailPagerAdapter productDetailPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        Intent intent = getIntent();


        setContentView(R.layout.activity_product_detail);
        productPager = (ViewPager) findViewById(R.id.product_view_pager);
        List<Product> productList = WalmartApplication.getInstance().getCache().getProductDataList();
        if (productList == null || productList.size() == 0) {
            finish();
            return;
        }

        int currentIndex = intent.getIntExtra(PRODUCT_ID_PARAM, 0);
        productDetailPagerAdapter = new ProductDetailPagerAdapter(getSupportFragmentManager(), productList);
        productPager.setAdapter(productDetailPagerAdapter);
        productPager.setOffscreenPageLimit(1);
        productPager.setPageMargin(2);

        if (currentIndex > 0 && currentIndex < productList.size()) {
            if (productPager.getCurrentItem() != currentIndex) {
                productPager.setCurrentItem(currentIndex, false);
            }
        }

    }
}
