package com.example.shopbanhang.Model;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    private String taikhoan;
    private String matkhau;
    private String phanquyen;

    public NguoiDung() {
    }

    public NguoiDung(String taikhoan, String matkhau, String phanquyen) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.phanquyen = phanquyen;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(String phanquyen) {
        this.phanquyen = phanquyen;
    }
}
