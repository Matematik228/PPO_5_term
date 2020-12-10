package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    TextView operationField;    // текстовое поле для вывода знака операции
    Double operand = null;  // операнд операции
    String lastOperation = "="; // последняя операция\
    boolean sost = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        resultField = (TextView) findViewById(R.id.resultField);
        numberField = (EditText) findViewById(R.id.numberField);
        operationField = (TextView) findViewById(R.id.operationField);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr, new simple());
        ft.commit();
        Bundle args = getIntent().getExtras();
        ((TextView) findViewById(R.id.textView2)).setText("Hello " + args.get("name").toString());
    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view) {

        Button button = (Button) view;
        numberField.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    // обработка нажатия на кнопку операции
    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }


    // обработка нажатия на доп кнопку операции
    public void onCustomOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if (number.length() == 0) {
            number = "0";
        }
        number = number.replace(',', '.');
        try {
            numberField.setText(performCustomOperation(Double.valueOf(number), op));
        } catch (NumberFormatException ex) {
            numberField.setText(number);
        }

    }

    private String performCustomOperation(Double number, String operation) {
        double result = 0;
            switch (operation) {
                case "sin":
                    result = Math.sin(number);
                    break;
                case "cos":
                    result = Math.cos(number);
                    break;
                case "tg":
                    result = Math.tan(number);
                    break;
                case "ctg":
                    result = 1 / Math.tan(number);
                    break;
                case "sqrt":
                    result = Math.sqrt(number);
                    break;
                case "sqr":
                    result = number * number;
                    break;
                case "cbrt":
                    result = Math.cbrt(number);
                    break;
                case "cbr":
                    result = number * number * number;
                    break;
                case "1/x":
                    result = 1 / number;
                    break;
                case "pi":
                    result = Math.PI;
                    break;
                case "e":
                    result = Math.E;
                    break;
                case "abs":
                    result = Math.abs(number);
                    break;
            }
        //resultField.setText(operand.toString().replace('.', ','));
        return String.valueOf(result).replace('.', ',');
    }

    private void performOperation(Double number, String operation) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }

    public void onFragmentChange(View view) {
        Fragment fragment = null;
        if (sost)
            fragment = new custom();
        else
            fragment = new simple();
        sost = !sost;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onBackClicked(View view)
    {
        super.finish();
    }
}