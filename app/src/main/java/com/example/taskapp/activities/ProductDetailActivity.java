package com.example.taskapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.adaptor.AllProductAdaptor;
import com.example.taskapp.controllers.ProductController;
import com.example.taskapp.services.DataMediater;
import com.example.taskapp.services.IController;

import com.taskApp.task.db.ProductDetail;


import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements DataMediater {
    ImageView btnAdd,btnSortByName,btnSortByPrice;
    RecyclerView rvProductList;
    Context context;
    ProductDetail entity;
    List<ProductDetail> productDetailList;
    IController iProductController;
    AllProductAdaptor adapter;
    TextView tvTotalProduct,tvTotalPrice;
    boolean isAsc = true;
    boolean sortByPrice = true;
    boolean sortByName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initUI();
        initobject();
        loadData();

        if (ContextCompat.checkSelfPermission(
                ProductDetailActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            ProductDetailActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0);
        }
    }


    private void loadData() {
        if (sortByPrice) {
            if (isAsc) {
                btnSortByPrice.setImageDrawable(getResources().getDrawable(R.drawable.up));
                productDetailList = iProductController.getAllByAsec();
                Double totalPrice = 0.0;
                for (int i = 0; i < productDetailList.size(); i++) {
                    double amount = productDetailList.get(i).getTotalPrice();
                    totalPrice = totalPrice + amount;
                }
                tvTotalProduct.setText("Total Product\n" + productDetailList.size());
                tvTotalPrice.setText("Total Price\n" + totalPrice.toString());
                adapter = new AllProductAdaptor(this, productDetailList, this);
                rvProductList.setAdapter(adapter);
                rvProductList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            } else {
                btnSortByPrice.setImageDrawable(getResources().getDrawable(R.drawable.down));
                productDetailList = iProductController.getAllByDesc();
                Double totalPrice = 0.0;
                for (int i = 0; i < productDetailList.size(); i++) {
                    double amount = productDetailList.get(i).getTotalPrice();
                    totalPrice = totalPrice + amount;
                }
                tvTotalProduct.setText("Total Product\n" + productDetailList.size());
                tvTotalPrice.setText("Total Price\n" + totalPrice.toString());
                adapter = new AllProductAdaptor(this, productDetailList, this);
                rvProductList.setAdapter(adapter);
                rvProductList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            }
        }else{
            if (sortByName) {
                btnSortByName.setImageDrawable(getResources().getDrawable(R.drawable.up));
                productDetailList = iProductController.getAllByAsecName();
                Double totalPrice = 0.0;
                for (int i = 0; i < productDetailList.size(); i++) {
                    double amount = productDetailList.get(i).getTotalPrice();
                    totalPrice = totalPrice + amount;
                }
                tvTotalProduct.setText("Total Product\n" + productDetailList.size());
                tvTotalPrice.setText("Total Price\n" + totalPrice.toString());
                adapter = new AllProductAdaptor(this, productDetailList, this);
                rvProductList.setAdapter(adapter);
                rvProductList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            } else {
                btnSortByName.setImageDrawable(getResources().getDrawable(R.drawable.down));
                productDetailList = iProductController.getAllByDescName();
                Double totalPrice = 0.0;
                for (int i = 0; i < productDetailList.size(); i++) {
                    double amount = productDetailList.get(i).getTotalPrice();
                    totalPrice = totalPrice + amount;
                }
                tvTotalProduct.setText("Total Product\n" + productDetailList.size());
                tvTotalPrice.setText("Total Price\n" + totalPrice.toString());
                adapter = new AllProductAdaptor(this, productDetailList, this);
                rvProductList.setAdapter(adapter);
                rvProductList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            }
        }
    }

    private void initobject() {
        entity = new ProductDetail();
        context = ProductDetailActivity.this;
        iProductController = new ProductController(context);
    }

    private void initUI() {
        tvTotalProduct = findViewById(R.id.tvTotalProduct);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnSortByName = findViewById(R.id.btnSortByName);
        btnSortByPrice = findViewById(R.id.btnSortByPrice);
        rvProductList = findViewById(R.id.rcProductList);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    callProductDialog();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        btnSortByPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                        if (isAsc == true) {
                            sortByPrice = true;
                            isAsc = false;
                        } else {
                            sortByPrice = true;
                            isAsc = true;
                        }
                    loadData();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        btnSortByName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (sortByName == true) {
                        sortByPrice = false;
                        sortByName = false;
                    } else {
                        sortByPrice = false;
                        sortByName = true;
                    }
                    loadData();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private void callProductDialog() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_product, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ADD PRODUCT");
        alert.setView(view);
        AddProductActivity addProductActivity = new AddProductActivity();
        final AlertDialog dialog = alert.create();
        addProductActivity.addProduct(context,view,dialog);

    }

    private void onRefresh() {
        this.onRestart();
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public void mediateDate(Object data1, Object data2) {

    }


}
