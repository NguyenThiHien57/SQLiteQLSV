package com.example.sqliteqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtHoten,edtNgaysinh,edtTruonghoc;
    RadioButton rdNam,rdNu;
    CheckBox cbTheThao,cbDuLich,cbDocSach;
    Button btnNhap,btnXem;
    static SinhVienDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHoten = findViewById(R.id.edtHoten);
        edtNgaysinh = findViewById(R.id.edtNgaysinh);
        edtTruonghoc = findViewById(R.id.edtTruonghoc);
        cbTheThao = findViewById(R.id.cbTheThao);
        cbDuLich = findViewById(R.id.cbDuLich);
        cbDocSach= findViewById(R.id.cbDocSach);
        btnNhap = findViewById(R.id.btnNhap);
        btnXem = findViewById(R.id.btnXem);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        db = new SinhVienDatabase(this);
//        btnNhapLai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edtHoten.setText("");
//                edtNgaysinh.setText("");
//                edtTruonghoc.setText("");
//                rdNam.setChecked(true);
//                cbTheThao.setChecked(false);
//                cbDuLich.setChecked(false);
//                cbDocSach.setChecked(false);
//            }
//        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoten.getText().toString();
                String namsinh = edtNgaysinh.getText().toString();
                String truong = edtTruonghoc.getText().toString();
                String sothich = "";
                int gt=0;
                if(cbTheThao.isChecked()){
                    sothich +=";"+cbTheThao.getText().toString();
                }
                if(cbDuLich.isChecked()){
                    sothich +=";"+cbDuLich.getText().toString();
                }
                if(cbDocSach.isChecked()){
                    sothich +=";"+cbDocSach.getText().toString();
                }
                if(rdNam.isChecked()){
                    gt = 1;
                }
                SinhVienModel svThem = new SinhVienModel(hoten,namsinh,truong,sothich, gt);
                if(svThem != null){
                    db.ThemSinhVien(svThem);
                    Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                }
                edtHoten.setText("");
                edtNgaysinh.setText("");
                edtTruonghoc.setText("");
                cbDocSach.setChecked(false);
                cbTheThao.setChecked(false);
                cbDuLich.setChecked(false);
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,dsSV.class);
                startActivity(intent);
            }
        });
    }
}
