package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shopbanhang.Adapter.BanAdapter;
import com.example.shopbanhang.Model.Ban;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BanActivity extends AppCompatActivity {
     RecyclerView recyclerView1;
     private ArrayList<Ban> list1;
     BanAdapter banAdapter1;
// 123 test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list1 = new ArrayList<>();

        recyclerView1 = findViewById(R.id.recyclerView1);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(BanActivity.this,2);
        recyclerView1.setLayoutManager(linearLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ban = database.getReference("Ban");

        ban.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Ban ban = postSnapshot.getValue(Ban.class);
                    list1.add(ban);
                }

                banAdapter1 = new BanAdapter(BanActivity.this, list1);
                recyclerView1.setAdapter(banAdapter1);
                banAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }




}