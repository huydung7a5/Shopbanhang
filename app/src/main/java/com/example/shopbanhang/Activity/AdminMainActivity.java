package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



import com.example.shopbanhang.Adapter.SanPhamAdminAdapter;
import com.example.shopbanhang.Model.Ban;
import com.example.shopbanhang.Model.SanPham;
import com.example.shopbanhang.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminMainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Intent intent ;
    String user,pass;
    RecyclerView recyclerView;
    SanPhamAdminAdapter sanPhamAdminAdapter;
    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private ArrayList<SanPham> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawaybolayout);
        navigationView = findViewById(R.id.navigationview);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        recyclerView = findViewById(R.id.recyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                        case R.id.menuthemsanpham:
                            Intent intent1 = new Intent(AdminMainActivity.this,ThemSanPham.class);
                            startActivity(intent1);
                            break;
                        case  R.id.menuthemloaisanpham:
                            break;
                            case R.id.menuthemban:
                                dialogthemban();
                                break;
                        case  R.id.menuXuLiDon:
                            Intent intent2 = new Intent(AdminMainActivity.this,BanAdmin.class);
                            startActivity(intent2);
                            break;
                        case  R.id.menuDoanhThu:
                            break;
                        case  R.id.menuDoiMatKhau:
                            doimatkhau();
                            break;
                        case  R.id.menuDangXuat:
                            dangxuat();
                            break;
                        case  R.id.menuthongtin:
                            startActivity(new Intent(AdminMainActivity.this,QuanLyUserActivity.class));
                            break;
                        case  R.id.menuThoat:
                            Intent intent7 = new Intent(AdminMainActivity.this,DangNhapActivity.class);
                            startActivity(intent7);
                            break;


                }


                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }

        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(AdminMainActivity.this,2);
        recyclerView.setLayoutManager(linearLayoutManager);

//        FirebaseRecyclerOptions<SanPham> options =
//                new FirebaseRecyclerOptions.Builder<SanPham>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SanPham"), SanPham.class)
//                        .build();
//        sanPhamAdapter = new SanPhamAdapter(options);
//        recyclerView.setAdapter(sanPhamAdapter);
        list = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SanPham");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    SanPham sanPham = postSnapshot.getValue(SanPham.class);
                    list.add(sanPham);
                }

                sanPhamAdminAdapter = new SanPhamAdminAdapter(AdminMainActivity.this, list);

                recyclerView.setAdapter(sanPhamAdminAdapter);
                sanPhamAdminAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminMainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

     private  void doimatkhau(){
         AlertDialog.Builder builder = new AlertDialog.Builder(AdminMainActivity.this)
                 .setPositiveButton("Hủy", null);
         LayoutInflater inflater = getLayoutInflater();
         View view = inflater.inflate(R.layout.dialogdoimatkhau, null);

         EditText edtmatkhau = view.findViewById(R.id.edtmatkhaucu);
         EditText edtmatkhaumoi = view.findViewById(R.id.edtmatkhaumoi);
         EditText edtnhaplaimatkhaumoi = view.findViewById(R.id.edtnhaplaimatkhau);
         Button btndoimatkhau = view.findViewById(R.id.btndoimatkhau);
         builder.setView(view);

         AlertDialog alertDialog = builder.create();
         alertDialog.show();

         btndoimatkhau.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                String matkhau  = edtmatkhau.getText().toString();
                String matkhaumoi = edtmatkhaumoi.getText().toString();
                String nhaplaimatkhaumoi = edtnhaplaimatkhaumoi.getText().toString();
                 if (matkhau.equals("") || matkhaumoi.equals("") || nhaplaimatkhaumoi.equals("")) {
                     Toast.makeText(AdminMainActivity.this, "Bạn cần nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                 }else {
                     if (matkhaumoi.equals(nhaplaimatkhaumoi)){
                         FirebaseDatabase database = FirebaseDatabase.getInstance();
                         DatabaseReference dangky = database.getReference("ThongTin");

                         dangky.child(user).addListenerForSingleValueEvent(new ValueEventListener() {

                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 //     tìm key là pass và bắt đầu so sánh mật khẩu chỉ 1 lần
                                 for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                     if (dataSnapshot.getKey().equals("matkhau")) {
                                         if (dataSnapshot.getValue().toString().equals(edtmatkhau.getText().toString())) {
                                             dangky.child(user).child("matkhau").setValue(matkhaumoi);
                                             Toast.makeText(AdminMainActivity.this, "Đổi Mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                             alertDialog.dismiss();

                                         }else {
                                             Toast.makeText(AdminMainActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                         }
                                     }

                                 }
                             }


                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {
                                 Toast.makeText(AdminMainActivity.this, "Lỗi Rồi", Toast.LENGTH_SHORT).show();
                             }
                         });
                     }else {
                         Toast.makeText(AdminMainActivity.this, "Nhập lại mật khẩu mới bị sai", Toast.LENGTH_SHORT).show();
                     }

                 }
             }
         });
     }
     private  void dangxuat(){
        startActivity(new Intent(AdminMainActivity.this,DangKyActivity.class));
     }
     private  void dialogthemban(){
              AlertDialog.Builder builder = new AlertDialog.Builder(AdminMainActivity.this);
              LayoutInflater inflater = getLayoutInflater();
              View view = inflater.inflate(R.layout.itemthemban,null);

         EditText edttenban = view.findViewById(R.id.edttenban);
         EditText edtsoban = view.findViewById(R.id.edtsoban);
         Button btnthemban = view.findViewById(R.id.btnthem);
         Button btnhuy = view.findViewById(R.id.btnhuy);
         builder.setView(view);

         AlertDialog alertDialog = builder.create();
         alertDialog.show();

         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference tenban1 = database.getReference("Ban");

         btnthemban.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tenban1.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       String tennban = edttenban.getText().toString().trim();
                       String soban = edtsoban.getText().toString().trim();
                       Ban ban = new Ban(tennban,soban);
                       tenban1.push().setValue(ban);
                       Toast.makeText(AdminMainActivity.this, "Thêm Bàn thành công", Toast.LENGTH_SHORT).show();
                       alertDialog.cancel();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }
       });
         btnhuy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 alertDialog.cancel();
                 alertDialog.dismiss();
             }
         });


     }



}