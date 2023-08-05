package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKyActivity extends AppCompatActivity {
      EditText edttaikhoan, edtmatkhau, edtnhaplaimatkhau;
      Button btndangnhap, btndangki;
    boolean check = true ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edttaikhoan = findViewById(R.id.edttaikhoan);
        edtmatkhau = findViewById(R.id.edtnhapmatkhau);
        edtnhaplaimatkhau = findViewById(R.id.edtnhaplaimatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        btndangki  = findViewById(R.id.btndangky);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangkytaikhoan();
            }
        });
    }
    private void dangkytaikhoan(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dangky = database.getReference("ThongTin");
        dangky.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 String phanquyen = "nguoidung";
                  String taikhoan = edttaikhoan.getText().toString();
                  String matkhau = edtmatkhau.getText().toString();
                  String matkhau2 =  edtnhaplaimatkhau.getText().toString();
                  if (!matkhau.equals(matkhau2)){
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }else {
                      for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                          if (dataSnapshot.getKey().equals(taikhoan)){
                              check = false;
                              Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                          }
                      }
                      if (check){
                          dangky.child(taikhoan).child("taikhoan").setValue(taikhoan);
                          dangky.child(taikhoan).child("matkhau").setValue(matkhau);
                          dangky.child(taikhoan).child("phanquyen").setValue(phanquyen);
                          Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
                      }else {
                          check = true;
                          Toast.makeText(DangKyActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                      }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}