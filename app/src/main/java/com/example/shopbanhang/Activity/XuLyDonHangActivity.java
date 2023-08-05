package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopbanhang.Adapter.DonHangDauBepAdapter;
import com.example.shopbanhang.Adapter.XuLyDonHangAdapter;
import com.example.shopbanhang.Model.DauBep;
import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class XuLyDonHangActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DauBep> list;
    DauBep daubep;
    XuLyDonHangAdapter xulydonhangadapter;
    String tenban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_don_hang);
        recyclerView= findViewById(R.id.recyclerView7);
        list = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        tenban = intent.getStringExtra("tenban");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference donhang = database.getReference("DauBep");
        donhang.child(tenban).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    DauBep dh = postSnapshot.getValue(DauBep.class);
                    list.add(dh);
                }

                xulydonhangadapter = new XuLyDonHangAdapter(XuLyDonHangActivity.this, list);
                recyclerView.setAdapter(xulydonhangadapter);
                xulydonhangadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}