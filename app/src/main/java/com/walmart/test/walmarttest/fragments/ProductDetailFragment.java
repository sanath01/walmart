package com.walmart.test.walmarttest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.walmart.test.walmarttest.R;
import com.walmart.test.walmarttest.activities.ProductDetailActivity;
import com.walmart.test.walmarttest.data.Product;

public class ProductDetailFragment extends Fragment {


    TextView titleTextView;
    TextView numRatingsTextView;
    TextView longDescription;
    TextView priceTextView;
    Button addToCartButton;
    ImageView productImageView;
    RatingBar ratingBar;
    Product product;
    int orientation;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment create(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ProductDetailActivity.PARAM_PRODUCT_CONTENT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = (Product) getArguments().getSerializable(ProductDetailActivity.PARAM_PRODUCT_CONTENT);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        product = (Product) args.getSerializable(ProductDetailActivity.PARAM_PRODUCT_CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        titleTextView = (TextView) view.findViewById(R.id.product_title);
        numRatingsTextView = (TextView) view.findViewById(R.id.num_of_ratings);
        longDescription = (TextView) view.findViewById(R.id.long_description);
        priceTextView = (TextView) view.findViewById(R.id.price_text);
        addToCartButton = (Button) view.findViewById(R.id.add_button);
        productImageView = (ImageView) view.findViewById(R.id.product_image);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        if (product != null) {
            titleTextView.setText(product.getProductName());
            String numReviewsText = "(" + product.getReviewCount() + " Reviews)";
            numRatingsTextView.setText(numReviewsText);
            if (product.getLongDescription() != null) {
                longDescription.setText(Html.fromHtml(product.getLongDescription()));
            } else if (product.getShortDescription() != null) {
                longDescription.setText(Html.fromHtml(product.getShortDescription()));
            } else {
                longDescription.setVisibility(View.GONE);
            }
            priceTextView.setText(this.product.getPrice());
            if (product.isInStock()) {
                addToCartButton.setBackgroundColor(getResources().getColor(R.color.orange));
                addToCartButton.setActivated(false);
            }
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (product.isInStock()) {
                        Toast.makeText(getActivity(), "Item Added to Cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Item Not Available!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Picasso.with(getActivity()).load(this.product.getProductImage()).into(productImageView);
            ratingBar.setRating(this.product.getReviewCount());
        }
        return view;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (product != null) {
            outState.putSerializable(ProductDetailActivity.PARAM_PRODUCT_CONTENT, product);
        }
    }
}
