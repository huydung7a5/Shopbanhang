package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shopbanhang.Adapter.BanAdminAdapter;
import com.example.shopbanhang.Adapter.BanDauBepAdapter;
import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BanAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DonHang> list;
    BanAdminAdapter banadminadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_admin);
        recyclerView= findViewById(R.id.recyclerView8);
        list = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference donhang = database.getReference("Ban");
        donhang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    DonHang dh = postSnapshot.getValue(DonHang.class);
                    list.add(dh);
                }

                banadminadapter = new BanAdminAdapter(BanAdmin.this, list);
                recyclerView.setAdapter(banadminadapter);
                banadminadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}