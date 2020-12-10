package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;

    public ProductAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView numView = (TextView) view.findViewById(R.id.num);

        Product product = products.get(position);

        nameView.setText(product.getName());
        numView.setText(product.getNum());

        return view;
    }
}