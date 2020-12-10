package com.example.lab4;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Product (String name, String num) {
        this.name = name;
        this.num = num;
    }

}
