package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopbanhang.Model.Ban;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangNhapActivity extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau;
    Button btndangnhap, btndangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edttaikhoan = findViewById(R.id.edttaikhoan);
        edtmatkhau = findViewById(R.id.edtnhapmatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap();
            }
        });
    }
    private  void  dangnhap(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dangky = database.getReference("ThongTin");

        dangky.child(edttaikhoan.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String phanquyen = "nguoidung";
                String phanquyen1 = "daubep";
                   //     tìm key là pass và bắt đầu so sánh mật khẩu chỉ 1 lần
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals("matkhau")) {
                        if (dataSnapshot.getValue().toString().equals(edtmatkhau.getText().toString())) {
                            for (DataSnapshot role: snapshot.getChildren()){
                              if ( role.getKey().equals("phanquyen")){
                                  if (role.getValue().toString().equals(phanquyen)){
                                      SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sharedPreferences.edit();
                                      editor.putString("taikhoan", edttaikhoan.getText().toString().trim());
                                      editor.commit();
                                      Intent intent = new Intent(DangNhapActivity.this, BanActivity.class);
                                      startActivity(intent);
                                      finish();
                                  }else if(role.getValue().toString().equals(phanquyen1)){
                                      SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sharedPreferences.edit();
                                      editor.putString("taikhoan", edttaikhoan.getText().toString().trim());
                                      editor.commit();
                                      Intent intent = new Intent(DangNhapActivity.this, BanDauBepActivity.class);
                                      startActivity(intent);
                                      finish();
                                  }else {
                                      Intent intent = new Intent(DangNhapActivity.this, AdminMainActivity.class);
                                      startActivity(intent);
                                      finish();
                                  }


                              }
                            }
                        }else {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DangNhapActivity.this, "Lỗi Rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}