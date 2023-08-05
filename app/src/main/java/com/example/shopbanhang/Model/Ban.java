package com.example.shopbanhang.Model;

import java.io.Serializable;

import kotlinx.coroutines.Job;

public class Ban implements Serializable {
    private String tenban;
    private  String soban;


    public Ban() {
    }

    public Ban(String tenban, String soban) {
        this.tenban = tenban;
        this.soban = soban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public String getSoban() {
        return soban;
    }

    public void setSoban(String soban) {
        this.soban = soban;
    }
}
