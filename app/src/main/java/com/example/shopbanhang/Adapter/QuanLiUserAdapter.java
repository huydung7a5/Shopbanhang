package com.example.shopbanhang.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopbanhang.Model.NguoiDung;
import com.example.shopbanhang.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class QuanLiUserAdapter extends FirebaseRecyclerAdapter<NguoiDung,QuanLiUserAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuanLiUserAdapter(@NonNull FirebaseRecyclerOptions<NguoiDung> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull NguoiDung model) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dangky = database.getReference("ThongTin");
        holder.txttaikhoan.setText("Tài Khoản: " + model.getTaikhoan());
        holder.txtmatkhau.setText("Mật Khẩu: " + model.getMatkhau());
        holder.txtrole.setText("Vai trò: " + model.getPhanquyen());

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txttaikhoan.getContext());
                LayoutInflater inflater =((Activity)holder.txttaikhoan.getContext()).getLayoutInflater();
                View view =  inflater.inflate(R.layout.itemdoithongtinuser,null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();


                TextView txttaikhoan = view.findViewById(R.id.txttaikhoan);
                EditText edtmatkhau = view.findViewById(R.id.edtmatkhau);
                EditText edtrole = view.findViewById(R.id.edtrole);
                Button btncapnhat = view.findViewById(R.id.btncapnhat);
                Button btnhuy = view.findViewById(R.id.btnhuy);

                txttaikhoan.setText(model.getTaikhoan());
                edtmatkhau.setText(model.getMatkhau());
                edtrole.setText(model.getPhanquyen());

                btncapnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dangky.child(model.getTaikhoan()).addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   dangky.child(model.getTaikhoan()).child("matkhau").setValue(edtmatkhau.getText().toString());
                                   dangky.child(model.getTaikhoan()).child("phanquyen").setValue(edtrole.getText().toString());
                                   Toast.makeText(holder.txttaikhoan.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                   alertDialog.cancel();


                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        holder.btndeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky.child(model.getTaikhoan()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.txttaikhoan.getContext());
                        builder.setTitle("Xóa á nha");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dangky.child(model.getTaikhoan()).removeValue();
                                Toast.makeText(holder.txttaikhoan.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Hong nha", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemuser,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView txttaikhoan, txtmatkhau, txtrole;
        Button btnedit, btndeleta;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txttaikhoan = itemView.findViewById(R.id.txtuser);
            txtmatkhau = itemView.findViewById(R.id.txtpass);
            txtrole = itemView.findViewById(R.id.txtphanquyen);
            btnedit = itemView.findViewById(R.id.btnedit);
            btndeleta = itemView.findViewById(R.id.btndelete);

        }
    }


}
