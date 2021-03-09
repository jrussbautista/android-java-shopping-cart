package com.example.ecommerce.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.ProductDetailsActivity;
import com.example.ecommerce.R;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Feature;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {

    private  Context context;
    private List<Feature> features;

    public FeatureAdapter(Context context, List<Feature> features) {
        this.context = context;
        this.features = features;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName, productPrice;
        private LinearLayout productContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imgProduct);
            productName = itemView.findViewById(R.id.tvProductName);
            productPrice = itemView.findViewById(R.id.tvProductPrice);
            productContainer = itemView.findViewById(R.id.productContainer);
        }
    }

    @NonNull
    @Override
    public FeatureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product_item, parent, false);
        return new FeatureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(features.get(position).getImage_url()).into(holder.productImage);
        holder.productName.setText(features.get(position).getName());
        holder.productPrice.setText(features.get(position).getPrice() + "$");

        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
