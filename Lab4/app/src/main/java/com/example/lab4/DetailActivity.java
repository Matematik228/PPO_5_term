package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle args = getIntent().getExtras();
        Product product = (Product) args.getSerializable(Product.class.getSimpleName());
        ((EditText) findViewById(R.id.editTextName)).setText(product.getName());
        ((EditText) findViewById(R.id.editTextNum)).setText(product.getNum());
    }
    public void onSaveClicked(View view) {
        Intent data = new Intent();
        data.putExtra(Product.class.getSimpleName(), new Product(((EditText) findViewById(R.id.editTextName)).getText().toString(), ((EditText) findViewById(R.id.editTextNum)).getText().toString()));
        setResult(1, data);
        finish();
    }
    public void onDeleteClicked(View view) {
        Intent data = new Intent();
        setResult(2, data);
        finish();
    }
    public void onBackClicked(View view) {
        Intent data = new Intent();
        setResult(0, data);
        finish();
    }
}