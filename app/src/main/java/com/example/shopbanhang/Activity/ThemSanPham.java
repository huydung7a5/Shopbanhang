package com.example.shopbanhang.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopbanhang.Model.SanPham;
import com.example.shopbanhang.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ThemSanPham extends AppCompatActivity {

    private static final  int PICK_IMAGE_REQUEST = 1;
    private Button btnthem, btnlayanh;

    private EditText edtten, edtgia;
    private ImageView imganh;
    private Uri mimageUri;
    private StorageReference storageReference;
    private DatabaseReference data;
    private ProgressBar  mProgressBar;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        edtten = findViewById(R.id.edtten);
        edtgia = findViewById(R.id.edtgia);
        btnthem = findViewById(R.id.btnthem);
        btnlayanh = findViewById(R.id.btnanh);
        imganh = findViewById(R.id.imganh);
        mProgressBar = findViewById(R.id.progress_bar);
        storageReference = FirebaseStorage.getInstance().getReference("SanPham");
        data = FirebaseDatabase.getInstance().getReference("SanPham");

        btnlayanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layanh();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(ThemSanPham.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadanh();
                }
            }
        });

    }
    private  String getFile(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private  void  layanh(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() !=null){
            mimageUri = data.getData();

            Picasso.with(this).load(mimageUri).into(imganh);
        }
    }
    private  void uploadanh(){
        data = FirebaseDatabase.getInstance().getReference("SanPham");
        if(mimageUri != null){
            StorageReference filestore = storageReference.child(System.currentTimeMillis()
                    + "." + getFile(mimageUri));
            filestore.putFile(mimageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                 filestore.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {
                         int soluong = 1;
                         String ten =  edtten.getText().toString().trim();
                         int gia = Integer.parseInt(edtgia.getText().toString().trim());
                         SanPham sanPham = new SanPham(ten,gia,uri.toString(),soluong,0);
                         data.push().setValue(sanPham);

                         mProgressBar.setVisibility(View.INVISIBLE);
                         Toast.makeText(ThemSanPham.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(ThemSanPham.this, AdminMainActivity.class);
                         startActivity(intent);
                         finish();
                     }
                 });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ThemSanPham.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(this, "Không Được để trống ảnh", Toast.LENGTH_SHORT).show();
        }
    }

}