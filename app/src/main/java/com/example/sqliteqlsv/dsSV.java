package com.example.sqliteqlsv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class dsSV extends AppCompatActivity {
    ListView lvSV;
    ArrayList<SinhVienModel> a;
    AdapterJava adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachsv);
        a = MainActivity.db.layDL();
        adapter = new AdapterJava(a,this);
        lvSV = findViewById(R.id.lvSV);
        adapter.notifyDataSetChanged();
        lvSV.setAdapter(adapter);
    }
}
