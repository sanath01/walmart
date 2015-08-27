package com.walmart.test.walmarttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.walmart.test.walmarttest.R;
import com.walmart.test.walmarttest.data.Product;

import java.util.List;

/**
 * Created by muppallav on 8/26/15.
 */
public class ProductListAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<Product> productList;

    public ProductListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList != null ? productList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return productList != null ? productList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.product_list_item_view, viewGroup, false);
        }

        TextView productTitle = (TextView) view.findViewById(R.id.product_title_list_view);
        ImageView thumbImage = (ImageView) view.findViewById(R.id.product_image_list_view);
        TextView productPrice = (TextView) view.findViewById(R.id.price_list_view);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating_bar_list_view);

        Product product = productList.get(i);
        productTitle.setText(product.getProductName());
        Picasso.with(context).load(product.getProductImage()).into(thumbImage);
        productPrice.setText(product.getPrice());
        ratingBar.setRating(product.getReviewRating());

        return view;
    }
}
