package com.example.sqliteqlsv;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqliteqlsv.R;
import com.example.sqliteqlsv.SinhVienModel;

import java.util.ArrayList;

public class AdapterJava extends BaseAdapter {
    ArrayList<SinhVienModel> a;
    Context context;

    public int getCount() {
        return a.size();
    }

    @Override
    public Object getItem(int position) {
        return a.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public AdapterJava(ArrayList<SinhVienModel> a, Context context) {
        this.a = a;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imgXoa, imgSua;
        TextView txtID, txtTenSV, txtNS, txtTruong, txtGT, txtST;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.items_custom_sv, null);
        imgXoa = convertView.findViewById(R.id.imgXoa);
        imgSua = convertView.findViewById(R.id.imgSua);
        txtTenSV = convertView.findViewById(R.id.txtTenSV);
        txtID = convertView.findViewById(R.id.txtID);
        txtNS = convertView.findViewById(R.id.txtNS);
        txtTruong = convertView.findViewById(R.id.txtTruong);
        txtGT = convertView.findViewById(R.id.txtGT);
        txtST = convertView.findViewById(R.id.txtST);

        final SinhVienModel DS = a.get(position);
        txtID.setText(DS.getID() + "");
        txtTenSV.setText(DS.getHoTen());
        txtNS.setText(DS.getNamSinh());
        txtTruong.setText(DS.getTruong());
        txtGT.setText(DS.getGioiTinh() + "");
        txtST.setText(DS.getSoThich());

        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.Xoa(DS.getID());
                a.remove(position);
                notifyDataSetChanged();
            }
        });
        imgSua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.sua_sinh_vien);
                final EditText edtTenSV, edtNS, edtTruong, edtGT, edtST;
                Button btnXN, btnHuy;
                edtTenSV = dialog.findViewById(R.id.edtTenSV);
                edtNS = dialog.findViewById(R.id.edtNS);
                edtTruong = dialog.findViewById(R.id.edtTruong);
                edtGT = dialog.findViewById(R.id.edtGT);
                edtST = dialog.findViewById(R.id.edtST);
                btnHuy = dialog.findViewById(R.id.btnHuy);
                btnXN = dialog.findViewById(R.id.btnXN);
                edtTenSV.setText(DS.getHoTen());
                edtNS.setText(DS.getNamSinh());
                edtTruong.setText(DS.getTruong());
                edtGT.setText(DS.getGioiTinh() + "");
                edtST.setText(DS.getSoThich());
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnXN.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SinhVienModel SV = new SinhVienModel();
                        SV.setID(DS.getID());
                        SV.setHoTen(edtTenSV.getText().toString());
                        SV.setNamSinh(edtNS.getText().toString());
                        SV.setTruong(edtTruong.getText().toString());
                        SV.setGioiTinh(Integer.parseInt(edtGT.getText().toString()));
                        SV.setSoThich(edtST.getText().toString());
                        a.set(position,SV);
                        MainActivity.db.SuaDL(SV,DS.getID());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        return convertView;
    }

}
