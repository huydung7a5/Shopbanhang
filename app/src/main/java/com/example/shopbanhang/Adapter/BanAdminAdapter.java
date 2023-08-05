package com.example.shopbanhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Activity.DonHangDauBepActivity;
import com.example.shopbanhang.Activity.XuLyDonHangActivity;
import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;

import java.util.ArrayList;

public class BanAdminAdapter extends RecyclerView.Adapter<BanAdminAdapter.Viewholder> {
    Context context;
    ArrayList<DonHang> list;

    public BanAdminAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itembanadmin,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DonHang sb = list.get(position);
        holder.txttenban.setText("Tên Bàn: " + sb.getTenban());
        holder.txtsoban.setText("Số Bàn: " + sb.getSoban());
        holder.btnmuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, XuLyDonHangActivity.class);
                intent.putExtra("tenban",sb.getTenban());

                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {

        return list.size();

    }

    public class Viewholder extends  RecyclerView.ViewHolder{
        TextView txttenban, txtsoban;
        LinearLayout btnmuangay;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txttenban = itemView.findViewById(R.id.txttenban3);
            txtsoban = itemView.findViewById(R.id.txtsoban3);
            btnmuangay = itemView.findViewById(R.id.showdss);

        }
    }

}
