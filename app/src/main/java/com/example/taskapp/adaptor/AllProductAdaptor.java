package com.example.taskapp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.activities.ProductDetailActivity;
import com.example.taskapp.controllers.ProductController;
import com.example.taskapp.services.DataMediater;
import com.example.taskapp.services.IController;
import com.example.taskapp.utility.Helper;
import com.taskApp.task.db.ProductDetail;

import java.util.List;

public class AllProductAdaptor extends RecyclerView.Adapter<AllProductAdaptor.DetailHolder>  {

    private List<ProductDetail> productDetailList;
    private final DataMediater dataMediater;
    private Context context;
    IController productController;


    public  AllProductAdaptor(Context context, List<ProductDetail> data, DataMediater dataMediater){
        this.productDetailList = data;
        this.context = context;
        this.dataMediater = dataMediater;
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View detailItem = inflater.inflate(R.layout.activity_product_list, parent, false);
        initObj();
        DetailHolder viewHolder = new DetailHolder(detailItem);
        return viewHolder;
    }

    private void initObj() {
        productController = new ProductController(context);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        if(productDetailList.size() !=0){
            final ProductDetail productDetail = productDetailList.get(position);
            String ProductName = productDetail.getProductName();
            String TotalPrice = String.valueOf(productDetail.getTotalPrice());
            String Qauntity = String.valueOf(productDetail.getQauntity());
            holder.tvProductName.setText(ProductName);
            holder.tvTotalPrice.setText("Total Price :-"+TotalPrice +"\n" + "Quantity :-"+Qauntity);
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (productDetail.getId() != null) {
                        productDetail.setIsActive(false);
                        productController.edit(productDetail);
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productDetailList.size();
    }

    public static class DetailHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvTotalPrice;
        View itemView;
        ImageView btnDelete;


        public DetailHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            btnDelete = itemView.findViewById(R.id.btDelete);
            this.setIsRecyclable(true);
        }
    }
}
