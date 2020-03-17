package com.example.sqliteqlsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SinhVienDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QUANLYSINHVIEN.db";
    private static final String TABLE_NAME="SV";
    private static final String ID="ID";
    private static final String TEN="TEN";
    private static final String NAMSINH="TUOI";
    private static final String TRUONG="TRUONG";
    private static final String GIOITINH="gioiTinh";
    private static final String SOTHICH="soThich";
    public SinhVienDatabase(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cauTruyVan="CREATE TABLE IF NOT EXISTS SV(ID INTEGER PRIMARY KEY AUTOINCREMENT , TEN text , TUOI text, TRUONG text , gioiTinh integer , soThich text)";
        db.execSQL(cauTruyVan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void ThemSinhVien(SinhVienModel svMoi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,svMoi.getHoTen());
        values.put(NAMSINH,svMoi.getNamSinh());
        values.put(TRUONG,svMoi.getTruong());
        values.put(GIOITINH,svMoi.getGioiTinh());
        values.put(SOTHICH,svMoi.getSoThich());
        try{
            database.insert(TABLE_NAME,null,values);
        }catch (Exception ex){
            Log.d("TAG", "ThemSinhVien: "+ex.getMessage());
        }

        database.close();
    }
    public ArrayList<SinhVienModel> layDL(){
        ArrayList<SinhVienModel> sinhVienModels = new ArrayList<>();
        String layDL = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(layDL , null);
        while (cursor.moveToNext()){
            SinhVienModel SV = new SinhVienModel();
            SV.setID(cursor.getInt(0));
            SV.setHoTen(cursor.getString(1));
            SV.setNamSinh(cursor.getString(2));
            SV.setTruong(cursor.getString(3));
            SV.setGioiTinh(cursor.getInt(4));
            SV.setSoThich(cursor.getString(5));
            sinhVienModels.add(SV);
        }

            database.close();
        return sinhVienModels;
    }
    public int Xoa(int id){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, ID +" = ?", new String[]{String.valueOf(id)});
    }
    public int SuaDL(SinhVienModel SV , int id){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN,SV.getHoTen());
        contentValues.put(NAMSINH,SV.getNamSinh());
        contentValues.put(TRUONG,SV.getTruong());
        contentValues.put(GIOITINH,SV.getGioiTinh());
        contentValues.put(SOTHICH,SV.getSoThich());
        return database.update(TABLE_NAME,contentValues,ID+"=?" , new String[]{String.valueOf(id)});
    }
}
