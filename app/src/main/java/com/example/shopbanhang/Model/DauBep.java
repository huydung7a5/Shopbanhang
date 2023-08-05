package com.example.shopbanhang.Model;

import java.io.Serializable;

import kotlinx.coroutines.Job;

public class DauBep implements Serializable {
    private String trangthai;
    private  int gia;
    private  String tensp;
    private  String soban;
    private String tenban;
    private  String taikhoan;
    private int tongtien;
    private  int soluong;
    private String taikhoandaubep;

    public String getTaikhoandaubep() {
        return taikhoandaubep;
    }

    public void setTaikhoandaubep(String taikhoandaubep) {
        this.taikhoandaubep = taikhoandaubep;
    }

    public DauBep(String trangthai, int gia, String tensp, String soban, String tenban, String taikhoan, int tongtien, int soluong, String taikhoandaubep) {
        this.trangthai = trangthai;
        this.gia = gia;
        this.tensp = tensp;
        this.soban = soban;
        this.tenban = tenban;
        this.taikhoan = taikhoan;
        this.tongtien = tongtien;
        this.soluong = soluong;
        this.taikhoandaubep = taikhoandaubep;

    }



    public DauBep() {
    }



    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getSoban() {
        return soban;
    }

    public void setSoban(String soban) {
        this.soban = soban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
