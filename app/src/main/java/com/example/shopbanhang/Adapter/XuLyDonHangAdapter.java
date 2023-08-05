package com.example.shopbanhang.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Model.DauBep;
import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class XuLyDonHangAdapter extends RecyclerView.Adapter<XuLyDonHangAdapter.ViewHolder> {
    Context context;
    ArrayList<DauBep> list;



    public XuLyDonHangAdapter(Context context, ArrayList<DauBep> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemxulydonhang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          DauBep db = list.get(position);
        holder.txttaikhoan.setText(db.getTaikhoan());
        holder.txttensp.setText(db.getTensp());
        holder.txttrangthai.setText(db.getTrangthai());
        holder.txtsoban.setText(db.getSoban());
        holder.txttenban.setText(db.getTenban());
        holder.txtgia.setText(String.valueOf(db.getGia()));
        holder.txtsoluong.setText(String.valueOf(db.getSoluong()));
        holder.txttongtien.setText(String.valueOf(db.getTongtien()));
        holder.txttaikhoandaubep.setText(String.valueOf(db.getTaikhoandaubep()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txttaikhoan, txtsoban,txttenban,txtsoluong,txtgia,txttongtien, txttrangthai, txttensp,txttaikhoandaubep;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttaikhoan = itemView.findViewById(R.id.txttaikhoandonhang1);
            txtsoban = itemView.findViewById(R.id.txtsobandonhang1);
            txttenban = itemView.findViewById(R.id.txttenbandonhang1);
            txtsoluong = itemView.findViewById(R.id.txtsoluongdonhang1);
            txtgia = itemView.findViewById(R.id.txtgiadonhang1);
            txttongtien = itemView.findViewById(R.id.txttongtiendonhang1);
            txttrangthai = itemView.findViewById(R.id.txttrangthaidonhang1);
            txttensp = itemView.findViewById(R.id.txttenmondonhang1);
            txttaikhoandaubep = itemView.findViewById(R.id.txttaikhoandaubep1);


        }

    }

}
