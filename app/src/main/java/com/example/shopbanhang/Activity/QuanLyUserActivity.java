package com.example.shopbanhang.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Adapter.QuanLiUserAdapter;
import com.example.shopbanhang.Model.NguoiDung;
import com.example.shopbanhang.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class QuanLyUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    QuanLiUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_user);
        // adapter nè
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuanLyUserActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<NguoiDung> options =
                new FirebaseRecyclerOptions.Builder<NguoiDung>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ThongTin"), NguoiDung.class)
                        .build();
        adapter = new QuanLiUserAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}