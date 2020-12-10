package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList();
    private int svpos = 0;
    ProductAdapter adapter = null;
    ListView productsList;
    @Override
    protected void onCreate(Bundle savedInstanceProduct) {
        super.onCreate(savedInstanceProduct);
        setContentView(R.layout.activity_main);
/*
        products.add(new Product ("Гречка", "1 кг"));
        products.add(new Product ("Яйца", "10 шт."));
        products.add(new Product ("Молоко", "1 л"));
        products.add(new Product ("Курица", "0.5 кг"));
        products.add(new Product ("Масло", "100 г"));*/
        // начальная инициализация списка
        // получаем элемент ListView
        productsList = (ListView) findViewById(R.id.productsList);
        // создаем адаптер
        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.list_item, products);
        // устанавливаем адаптер
        adapter = productAdapter;
        productsList.setAdapter(productAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                openDetail(position);

            }
        };
        productsList.setOnItemClickListener(itemListener);
    }

    public void onAddClicked(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Product.class.getSimpleName(), new Product("", ""));
        startActivityForResult(intent, 2);
    }

    private void openDetail(int pos) {
        svpos = pos;
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Product.class.getSimpleName(), products.get(pos));
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1)
        {
            if(resultCode == 1) {
                Product pr = (Product) data.getSerializableExtra(Product.class.getSimpleName());
                products.set(svpos, pr);
            }
            else if (resultCode == 2) {
                products.remove(svpos);
            }
            adapter.notifyDataSetChanged();
        }else if(requestCode == 2)
        {
            if(resultCode == 1) {
                Product pr = (Product) data.getSerializableExtra(Product.class.getSimpleName());
                products.add(pr);
            }
            adapter.notifyDataSetChanged();
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    public void onLoadClicked(View view){

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS product (name TEXT, num TEXT);");
        Cursor query = db.rawQuery("SELECT * FROM product;", null);
        products.clear();
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                String num = query.getString(1);
                products.add(new Product(name, num));
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();
        adapter.notifyDataSetChanged();
    }
    public void onSaveClicked(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS product (name TEXT, num TEXT);");
        db.execSQL("DELETE FROM product;");
        for(Product pr : products) {
            db.execSQL("INSERT INTO product (name, num) VALUES ('" + pr.getName() + "', '" + pr.getNum() + "');");
        }
        db.close();
    }
}