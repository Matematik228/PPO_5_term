package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private float xs, ys, xf, yf;
    private Button[][] table;
    private int[][] field;
    private int xp=3, yp=3;
    private boolean refreshed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = new Button[][]{
                {((Button) findViewById(R.id.b1)), ((Button) findViewById(R.id.b2)), ((Button) findViewById(R.id.b3)), ((Button) findViewById(R.id.b4))},
                {((Button) findViewById(R.id.b5)), ((Button) findViewById(R.id.b6)), ((Button) findViewById(R.id.b7)), ((Button) findViewById(R.id.b8))},
                {((Button) findViewById(R.id.b9)), ((Button) findViewById(R.id.b10)), ((Button) findViewById(R.id.b11)), ((Button) findViewById(R.id.b12))},
                {((Button) findViewById(R.id.b13)), ((Button) findViewById(R.id.b14)), ((Button) findViewById(R.id.b15)), ((Button) findViewById(R.id.b16))}
        };
        field = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        for (Button[] x : table) {
            for (Button y : x) {
                ;
            }
        }
    }
    private void updateField(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++)
            {
                table[i][j].setText(field[i][j] != 0 ? String.valueOf(field[i][j]) : "");
            }
        }
    }
    private void refresh(){
        field = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        xp = 3;
        yp = 3;
        for(int i = 0; i < 1000; i++){
            move((int) (Math.random()*4));
        }
        refreshed = true;
    }
    private void move(int napr){
        if(napr == 0) { // DOWN
            if (xp > 0) {
                field[xp][yp] = field[xp - 1][yp];
                xp--;
                field[xp][yp] = 0;
            }
        }
        if(napr == 1) { // UP
            if (xp < 3) {
                field[xp][yp] = field[xp + 1][yp];
                xp++;
                field[xp][yp] = 0;
            }
        }
        if(napr == 2) { // RIGHT
            if (yp > 0) {
                field[xp][yp] = field[xp][yp - 1];
                yp--;
                field[xp][yp] = 0;
            }
        }
        if(napr == 3) { // LEFT
            if (yp < 3) {
                field[xp][yp] = field[xp][yp + 1];
                yp++;
                field[xp][yp] = 0;
            }
        }
        updateField();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean ret = false;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xs = event.getX();
            ys = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            xf = event.getX();
            yf = event.getY();
            ret = exec();
        }
        return ret;
    }

    private boolean exec() {
        TextView tv = ((TextView) findViewById(R.id.textView));
        if (xs == xf && ys == yf) {
            tv.setText("REFRESHED");
            refresh();
            showMessage("Field refreshed");
            return false;
        } else {
            if (Math.abs(xs - xf) > Math.abs(ys - yf)) {
                if (xs > xf) {
                    tv.setText("LEFT");
                    move(3);
                } else {
                    tv.setText("RIGHT");
                    move(2);
                }
            } else {
                if(ys > yf) {
                    tv.setText("UP");
                    move(1);
                } else {
                    tv.setText("DOWN");
                    move(0);
                }
            }
        }
        if(check()) {
            if(refreshed) {
                tv.setText("WIN!!!");
                showMessage("You win!");
                refreshed = false;
            }
        }
        return true;
    }
    private void showMessage(String s)
    {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }
    private boolean check() {
        if(xp != 3 || yp != 3)
            return false;
        boolean ret = true;
        int pred = 0;
        for (int[] x : field) {
            for (int y : x) {
                if (y != 0 && y < pred){
                    ret = false;
                }
                pred = y;
            }
        }
        return ret;
    }
}