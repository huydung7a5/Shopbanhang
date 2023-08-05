package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopbanhang.Adapter.DonHangDauBepAdapter;
import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangDauBepActivity extends AppCompatActivity {
    RecyclerView recyclerView3;
    ArrayList<DonHang> list;
    DonHang donhang;
    DonHangDauBepAdapter donHangAdapter;
    String tenban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_dau_bep2);
        recyclerView3= findViewById(R.id.recyclerView6);
        list = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView3.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        tenban = intent.getStringExtra("tenban");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference donhang = database.getReference("DonHang");
        donhang.child(tenban).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    DonHang dh = postSnapshot.getValue(DonHang.class);
                    list.add(dh);
                }

                donHangAdapter = new DonHangDauBepAdapter(DonHangDauBepActivity.this, list);
                recyclerView3.setAdapter(donHangAdapter);
                donHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}