package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onCalcClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", ((EditText) findViewById(R.id.editTextTextPersonName)).getText());
        startActivity(intent);
    }
    public void onAuthClicked(View view) {
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivity(intent);
    }
}