package com.example.shopbanhang.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.Model.SanPham;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamUserAdapter extends RecyclerView.Adapter<SanPhamUserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public SanPhamUserAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsanphamuser,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sp = list.get(position);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference donhang = database.getReference("DonHang");
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference donhangdaubep = database.getReference("DonHangDauBep");
        holder.txttensanpham.setText("Tên: "+ sp.getTensp());
        holder.txtgia.setText(String.valueOf("Giá: "+sp.getGia()));
        holder.edtsoluong.setText(String.valueOf(sp.getSoluong()));
        Picasso.with(context).load(sp.getAnh()).fit().centerCrop().into(holder.imganhuser);
        holder.btnthemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences tt = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
                String taikhoan = tt.getString("taikhoan",null);
                String trangthai = "Đang chờ";
                SharedPreferences b = context.getSharedPreferences("BAN", MODE_PRIVATE);
                String tenban = b.getString("tenban1",null);
                String soban = b.getString("soban1",null);
                String tensanpham = holder.txttensanpham.getText().toString();
                int gia = Integer.parseInt(holder.txtgia.getText().toString());
                int soluong = Integer.parseInt(holder.edtsoluong.getText().toString());
                String taikhoandaubep = "Đang chờ";


                   int tongTien = gia * soluong;


                   if (soluong ==0){
                       Toast.makeText(context, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();
                   }else {
                       donhang.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               donhang.child(tenban).child(tensanpham).child("tensp").setValue(tensanpham);
                               donhang.child(tenban).child(tensanpham).child("taikhoan").setValue(taikhoan);
                               donhang.child(tenban).child(tensanpham).child("trangthai").setValue(trangthai);
                               donhang.child(tenban).child(tensanpham).child("gia").setValue(gia);
                               donhang.child(tenban).child(tensanpham).child("soluong").setValue(soluong);
                               donhang.child(tenban).child(tensanpham).child("taikhoandaubep").setValue(taikhoandaubep);
                               donhang.child(tenban).child(tensanpham).child("soban").setValue(soban);
                               donhang.child(tenban).child(tensanpham).child("tongtien").setValue(tongTien);
                               donhang.child(tenban).child(tensanpham).child("tenban").setValue(tenban);

                               Toast.makeText(context, "Thêm món thành công", Toast.LENGTH_SHORT).show();




                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });
                   }


            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensanpham, txtgia;
        EditText edtsoluong;
        ImageView imganhuser , btnthemmon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txttensanpham= itemView.findViewById(R.id.txttensanpham);
            txtgia = itemView.findViewById(R.id.txtgiasanpham);
            btnthemmon = itemView.findViewById(R.id.btnthemmon);
            imganhuser = itemView.findViewById(R.id.imganhuser);
            edtsoluong = itemView.findViewById(R.id.edtsoluong);


        }
    }
}
