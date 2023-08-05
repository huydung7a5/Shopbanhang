package com.example.shopbanhang.Model;

public class SanPham {
    private  String tensp;
    private  int gia;
    private  String anh;
    private  int soluong;

    private  int tongtien;

    public SanPham() {
    }
    public SanPham(String tensp, int gia, String anh, int soluong,int tongtien) {
        if(tensp.trim().equals("")){
            tensp = "No Name";
        }
        this.tensp = tensp;
        this.gia = gia;
        this.anh = anh;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
