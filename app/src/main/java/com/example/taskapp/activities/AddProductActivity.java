package com.example.taskapp.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskapp.adaptor.AllProductAdaptor;
import com.example.taskapp.controllers.ProductController;
import com.example.taskapp.services.DataMediater;
import com.example.taskapp.services.IController;
import com.example.taskapp.utility.Helper;
import com.taskApp.task.db.ProductDetail;
import  com.example.taskapp.R;
import java.util.List;

public class AddProductActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener, DataMediater {
    String[] qauntity = { "1", "2", "3", "4", "5","6","7","8","9","10"};
    EditText etProductName,etProductCost,etTotalPrice;
    Button btnSave;
    Spinner spQauntity;
    ProductDetail entity;
    List<ProductDetail> productDetailList;
    IController iProductController;
    AllProductAdaptor adapter;
    private Context context;

    protected void addProduct(Context context, View view, AlertDialog dialog) {
        this.context = context;
        dialog.show();
        initUI(view,dialog);
        initobject();
    }



    private void initUI(View view, final AlertDialog alert) {
        btnSave = view.findViewById(R.id.btnSave);
        etProductName = view.findViewById(R.id.etProductName);
        etProductCost = view.findViewById(R.id.etProductCost);
        etTotalPrice = view.findViewById(R.id.etTotalPrice);
        spQauntity = view.findViewById(R.id.spQauntity);
        spQauntity.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item,qauntity);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQauntity.setAdapter(arrayAdapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    saveProduct();
                    alert.dismiss();

                } catch (Exception e) {
                    alert.dismiss();
                    System.out.println("add product activity "+e);
                   Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initobject() {
        entity = new ProductDetail();
        iProductController = new ProductController(context);
    }

    public void saveProduct() {
        if (isValidate() == true) {
            entity.setProductName(etProductName.getText().toString());
            entity.setProductCost(Helper.convertToInt(etProductCost.getText().toString()));
            entity.setTotalPrice(Helper.convertToInt(etTotalPrice.getText().toString()));
            entity.setQauntity(Helper.convertToInt(spQauntity.getSelectedItem().toString()));
            entity.setIsActive(true);
            if (entity.getId() == null) {
                iProductController.save(entity);
                makeToast("Saved Successfully");

            } else {
                iProductController.edit(entity);
                makeToast("Updated Successfully");
            }

        }
    }
    public void makeToast(String message) {
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }

    public boolean isValidate() {

        String errorMessage = "";
        if (etProductName.getText().toString().trim().equals("")) {
            errorMessage = errorMessage + "Product Name Required " + "\n";
            etProductName.setError("required");
        }
        if (etProductCost.getText().toString().trim().equals("")) {
            errorMessage = errorMessage + "Product Cost Required " + "\n";
            etProductCost.setError("required");
        }

        if (errorMessage.equals(""))
            return true;
        else {
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(etProductCost.getText() != null){
            Integer productCost =Helper.convertToInt(etProductCost.getText().toString());
            Integer quantity = Helper.convertToInt(spQauntity.getSelectedItem().toString());
            Integer totalCost = productCost * quantity;
            etTotalPrice.setText(totalCost.toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void mediateDate(Object data1, Object data2) {

    }
}
