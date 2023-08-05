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

import com.example.shopbanhang.Model.DonHang;
import com.example.shopbanhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangDauBepAdapter extends RecyclerView.Adapter<DonHangDauBepAdapter.ViewHolder> {
    Context context;
    ArrayList<DonHang> list;
    DonHang donHang;
    String tinhtrang = "Đã chế biến";

    public DonHangDauBepAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
        this.donHang = donHang;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdaubep,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        DonHang dh = list.get(position)
        holder.txttaikhoan.setText(list.get(position).getTaikhoan());
        holder.txttensp.setText(list.get(position).getTensp());
        holder.txttrangthai.setText(list.get(position).getTrangthai());
        holder.txtsoban.setText(list.get(position).getSoban());
        holder.txttenban.setText(list.get(position).getTenban());
        holder.txtgia.setText(String.valueOf(list.get(position).getGia()));
        holder.txtsoluong.setText(String.valueOf(list.get(position).getSoluong()));
        holder.txttongtien.setText(String.valueOf(list.get(position).getTongtien()));
        holder.txttaikhoandaubep.setText(String.valueOf(list.get(position).getTaikhoandaubep()));
        if(((list.get(position).getTrangthai()).equals(tinhtrang))){
            holder.btnxacnhan.setVisibility(View.GONE);
        }else {
            holder.btnxacnhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseDatabase v1 = FirebaseDatabase.getInstance();
                    DatabaseReference data2 = v1.getReference("DonHang");
                    FirebaseDatabase v2 = FirebaseDatabase.getInstance();
                    DatabaseReference data1 = v2.getReference("DauBep");

                    data2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            SharedPreferences tt = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
                            String taikhoandaubep = tt.getString("taikhoan",null);
                            String taikhoan = holder.txttaikhoan.getText().toString();
                            String tensp = holder.txttensp.getText().toString();
                            String trangthai1 = "Đã chế biến";
                            String soban = holder.txtsoban.getText().toString();
                            String tenban = holder.txttenban.getText().toString();
                            int gia = Integer.parseInt(holder.txtgia.getText().toString());
                            int soluong = Integer.parseInt(holder.txtsoluong.getText().toString());
                            int tongtien = Integer.parseInt(holder.txttongtien.getText().toString());
                            DonHang donhang = new DonHang(trangthai1,gia,tensp,soban,tenban,taikhoan,tongtien,soluong,taikhoandaubep);

                            data1.child(tenban).push().setValue(donhang);
                            data2.child(tenban).child(tensp).child("tensp").setValue(tensp);
                            data2.child(tenban).child(tensp).child("taikhoan").setValue(taikhoan);
                            data2.child(tenban).child(tensp).child("trangthai").setValue(trangthai1);
                            data2.child(tenban).child(tensp).child("gia").setValue(gia);
                            data2.child(tenban).child(tensp).child("soluong").setValue(soluong);
                            data2.child(tenban).child(tensp).child("taikhoandaubep").setValue(taikhoandaubep);
                            data2.child(tenban).child(tensp).child("soban").setValue(soban);
                            data2.child(tenban).child(tensp).child("tongtien").setValue(tongtien);
                            data2.child(tenban).child(tensp).child("tenban").setValue(tenban);





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
        Button btnxacnhan;
        LinearLayout colordonhang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttaikhoan = itemView.findViewById(R.id.txttaikhoandonhang2);
            txtsoban = itemView.findViewById(R.id.txtsobandonhang2);
            txttenban = itemView.findViewById(R.id.txttenbandonhang2);
            txtsoluong = itemView.findViewById(R.id.txtsoluongdonhang2);
            txtgia = itemView.findViewById(R.id.txtgiadonhang2);
            txttongtien = itemView.findViewById(R.id.txttongtiendonhang2);
            txttrangthai = itemView.findViewById(R.id.txttrangthaidonhang2);
            txttensp = itemView.findViewById(R.id.txttenmondonhang2);
            txttaikhoandaubep = itemView.findViewById(R.id.txttaikhoandaubep);
            btnxacnhan = itemView.findViewById(R.id.btnxacnhan2);
            colordonhang = itemView.findViewById(R.id.colerdonhang);

        }

    }

}
