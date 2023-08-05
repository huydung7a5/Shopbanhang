package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopbanhang.Adapter.BanAdapter;
import com.example.shopbanhang.Adapter.SanPhamAdminAdapter;
import com.example.shopbanhang.Adapter.SanPhamUserAdapter;
import com.example.shopbanhang.Model.SanPham;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainChinh extends AppCompatActivity {
    RecyclerView recyclerView2;
    SanPhamUserAdapter sanPhamUserAdapter;
    ArrayList<SanPham> list;
    Button btngiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chinh);

       btngiohang = findViewById(R.id.btngiohang);



        recyclerView2 = findViewById(R.id.recyclerView2);
        list = new ArrayList<>();


        GridLayoutManager grid = new GridLayoutManager(MainChinh.this,2);
        recyclerView2.setLayoutManager(grid);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sanPham = database.getReference("SanPham");


        sanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    SanPham sanPham = postSnapshot.getValue(SanPham.class);
                    list.add(sanPham);
                }
                sanPhamUserAdapter = new SanPhamUserAdapter(MainChinh.this, list);

                recyclerView2.setAdapter(sanPhamUserAdapter);
                sanPhamUserAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btngiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences b = getSharedPreferences("BAN", MODE_PRIVATE);
                String tenban = b.getString("tenban1",null);
                Intent intent = new Intent(MainChinh.this, DonHangActivity.class);
                intent.putExtra("tenban",tenban);
                startActivity(intent);
            }
        });

    }
}