package com.example.shopbanhang.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
     Context context;
     ArrayList<DonHang> list;
     String trangthai = "Đã chế biến";

    public DonHangAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdonhang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           DonHang dh = list.get(position);
           holder.txttaikhoan.setText(dh.getTaikhoan());
           holder.txttensp.setText(dh.getTensp());
           holder.txttrangthai.setText(dh.getTrangthai());
           holder.txtsoban.setText(dh.getSoban());
           holder.txttenban.setText(dh.getTenban());
           holder.txtgia.setText(String.valueOf(dh.getGia()));
           holder.txtsoluong.setText(String.valueOf(dh.getSoluong()));
           holder.txttongtien.setText(String.valueOf(dh.getTongtien()));
           holder.txttaikhoandaubep.setText(String.valueOf(dh.getTaikhoandaubep()));
           if(dh.getTrangthai().equals(trangthai)){
               holder.btnxoa.setVisibility(View.GONE);

           }else {
               holder.btnxoa.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                       DatabaseReference remove = firebaseDatabase.getReference("DonHang");
                       remove.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               String tensp = holder.txttensp.getText().toString();
                               String tenban = holder.txttenban.getText().toString();
                               remove.child(tenban).child(tensp).removeValue();
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });
                   }
               });
           }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txttaikhoan, txtsoban,txttenban,txtsoluong,txtgia,txttongtien, txttrangthai, txttensp,txttaikhoandaubep;
         Button btnxoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttaikhoan = itemView.findViewById(R.id.txttaikhoandonhang);
            txtsoban = itemView.findViewById(R.id.txtsobandonhang);
            txttenban = itemView.findViewById(R.id.txttenbandonhang);
            txtsoluong = itemView.findViewById(R.id.txtsoluongdonhang);
            txtgia = itemView.findViewById(R.id.txtgiadonhang);
            txttongtien = itemView.findViewById(R.id.txttongtiendonhang);
            txttrangthai = itemView.findViewById(R.id.txttrangthaidonhang);
            txttensp = itemView.findViewById(R.id.txttenmondonhang);
            txttaikhoandaubep = itemView.findViewById(R.id.txttaikhoandaubep);
            btnxoa = itemView.findViewById(R.id.btnxoa);


        }
    }

}
