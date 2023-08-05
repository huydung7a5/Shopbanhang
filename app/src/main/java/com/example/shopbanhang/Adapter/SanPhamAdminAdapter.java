package com.example.shopbanhang.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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

public class SanPhamAdminAdapter extends RecyclerView.Adapter<SanPhamAdminAdapter.Viewholder> {
    private Context context;
    private FirebaseStorage mStorage;
    private ArrayList<SanPham> list;

    public SanPhamAdminAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsanpham,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dangky = database.getReference("SanPham");
          SanPham sp = list.get(position);
          holder.txtten.setText("Tên sản phẩm" + sp.getTensp());
        holder.txtgia.setText(String.valueOf(sp.getGia()));
        Picasso.with(context).load(sp.getAnh()).fit().centerInside().into(holder.imganh);
        holder.btnmuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorage = FirebaseStorage.getInstance();
                dangky.child(sp.getTensp()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Xóa á nha");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SanPham selectedItem = list.get(position);
                                final String selectedKey = selectedItem.getTensp();

                                StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getAnh());
                                imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dangky.child(selectedKey).removeValue();
                                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

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

    @Override
    public int getItemCount() {

        return list.size();

    }

    public class Viewholder extends  RecyclerView.ViewHolder{
       TextView txtten, txtgia;
       Button btnmuangay;
       ImageView imganh;
       public Viewholder(@NonNull View itemView) {
           super(itemView);
           txtten = itemView.findViewById(R.id.txtten);
           txtgia = itemView.findViewById(R.id.txtgia);
           btnmuangay = itemView.findViewById(R.id.btnmuangay);
           imganh = itemView.findViewById(R.id.imganhne);
       }
   }

}
