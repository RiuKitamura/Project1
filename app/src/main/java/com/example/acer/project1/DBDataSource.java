package com.example.acer.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBDataSource {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_KODE,
            DBHelper.COLUMN_NAMA_BANGUNAN, DBHelper.COLUMN_JML_LANTAI,DBHelper.COLUMN_THN_DIBUAT,
            DBHelper.COLUMN_ALAMAT_BANGUNAN, DBHelper.COLUMN_LATITUDE, DBHelper.COLUMN_LONGITUDE,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_ALAMAT, DBHelper.COLUMN_HP
    };

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert barang ke database
    public DataBangunan createDtaBangunan(String namaB, String jmlLti, String thn, String almtB,
                                          String lati, String longi, String namaP, String almtP, String noP) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA_BANGUNAN, namaB);
        values.put(DBHelper.COLUMN_JML_LANTAI, jmlLti);
        values.put(DBHelper.COLUMN_THN_DIBUAT, thn);
        values.put(DBHelper.COLUMN_ALAMAT_BANGUNAN, almtB);
        values.put(DBHelper.COLUMN_LATITUDE, lati);
        values.put(DBHelper.COLUMN_LONGITUDE, longi);
        values.put(DBHelper.COLUMN_NAMA, namaP);
        values.put(DBHelper.COLUMN_ALAMAT, almtP);
        values.put(DBHelper.COLUMN_HP, noP);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_KODE + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek barang
        DataBangunan newDtaBangunan = cursorToDtaBangunan(cursor);

        // close cursor
        cursor.close();

        // mengembalikan barang baru
        return newDtaBangunan;
    }

    private DataBangunan cursorToDtaBangunan(Cursor cursor)
    {
        // buat objek barang baru
        DataBangunan barang = new DataBangunan();
        // debug LOGCAT
        Log.v("info", "The getInt "+cursor.getInt(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getInt(2)+
                ","+cursor.getInt(3)+","+cursor.getString(4)+","+cursor.getDouble(5)+
                ","+cursor.getDouble(6)+","+cursor.getString(7)+","+cursor.getString(8)+
                ","+cursor.getInt(9));

        /* Set atribut pada objek barang dengan
         * data kursor yang diambil dari database*/
        barang.setId((int) cursor.getInt(0));
        barang.setNama_bangunan(cursor.getString(1));
        barang.setJml_lantai(cursor.getInt(2));
        barang.setThn_dibuat(cursor.getInt(3));
        barang.setAlamat_bangunan(cursor.getString(4));
        barang.setLatitude(cursor.getDouble(5));
        barang.setLongitude(cursor.getDouble(6));
        barang.setNama(cursor.getString(7));
        barang.setAlamat(cursor.getString(8));
        barang.setHp(cursor.getInt(9));

        //kembalikan sebagai objek barang
        return barang;
    }

    //mengambil semua data barang
    public ArrayList<DataBangunan> getAllBarang() {
        ArrayList<DataBangunan> daftarBarang = new ArrayList<DataBangunan>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            DataBangunan barang = cursorToDtaBangunan(cursor);
            daftarBarang.add(barang);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarBarang;
    }
}
