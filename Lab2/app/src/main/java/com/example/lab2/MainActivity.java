package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClicked(View view)
    {
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        if(editText.getText().length() > 0)
        {
            textView2.setText("Привет, " + editText.getText() + ".");
            textView2.setVisibility(View.VISIBLE);
        }
        else
        {
            textView2.setText("");
            textView2.setVisibility(View.GONE);
        }

    }
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        int dark = getResources().getColor(R.color.darkColor);
        int light = getResources().getColor(R.color.lightColor);
        switch(view.getId())
        {
            case R.id.light:
                if(checked)
                {
                    ((ConstraintLayout) findViewById(R.id.background)).setBackgroundColor(light);
                    ((TextView) findViewById(R.id.textView)).setTextColor(dark);
                    ((TextView) findViewById(R.id.textView2)).setTextColor(dark);
                    ((TextView) findViewById(R.id.textView3)).setTextColor(dark);
                    ((RadioButton) findViewById(R.id.dark)).setTextColor(dark);
                    ((RadioButton) findViewById(R.id.light)).setTextColor(dark);
                    ((EditText) findViewById(R.id.editTextTextPersonName)).setTextColor(dark);
                }
                break;
            case R.id.dark:
                if(checked)
                {
                    ((ConstraintLayout) findViewById(R.id.background)).setBackgroundColor(dark);
                    ((TextView) findViewById(R.id.textView)).setTextColor(light);
                    ((TextView) findViewById(R.id.textView2)).setTextColor(light);
                    ((TextView) findViewById(R.id.textView3)).setTextColor(light);
                    ((RadioButton) findViewById(R.id.dark)).setTextColor(light);
                    ((RadioButton) findViewById(R.id.light)).setTextColor(light);
                    ((EditText) findViewById(R.id.editTextTextPersonName)).setTextColor(light);
                }
                break;
        }
    }
}