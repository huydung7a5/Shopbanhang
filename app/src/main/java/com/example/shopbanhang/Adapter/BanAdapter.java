package com.example.shopbanhang.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Activity.MainChinh;
import com.example.shopbanhang.Model.Ban;
import com.example.shopbanhang.Model.SanPham;
import com.example.shopbanhang.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.Viewholder> {
    private Context context;
    private ArrayList<Ban> list1;

    public BanAdapter(Context context, ArrayList<Ban> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemban,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Ban sb = list1.get(position);
        holder.txttenban.setText("Tên Bàn: " + sb.getTenban());
        holder.txtsoban.setText("Số Bàn: " + sb.getSoban());
        holder.btnmuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainChinh.class);
                SharedPreferences thongtinban = context.getSharedPreferences("BAN", MODE_PRIVATE);
                SharedPreferences.Editor editor = thongtinban.edit();
                editor.putString("tenban1",sb.getTenban());
                editor.putString("soban1",sb.getSoban());
                editor.commit();

                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {

        return list1.size();

    }

    public class Viewholder extends  RecyclerView.ViewHolder{
        TextView txttenban, txtsoban;
        LinearLayout btnmuangay;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txttenban = itemView.findViewById(R.id.txttenban);
            txtsoban = itemView.findViewById(R.id.txtsoban);
            btnmuangay = itemView.findViewById(R.id.show);

        }
    }

}
