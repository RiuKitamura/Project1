package com.example.acer.project1;

//deklarasi import package
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    /**
     * deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel,
     * nama-nama kolom, nama database, dan versi dari database
     **/
    public static final String TABLE_NAME = "data_bangunan";
    public static final String COLUMN_KODE= "_kode";
    public static final String COLUMN_NAMA_BANGUNAN = "nama_bangunan";
    public static final String COLUMN_JML_LANTAI = "jml_lantai";
    public static final String COLUMN_THN_DIBUAT = "thn_dibuat";
    public static final String COLUMN_ALAMAT_BANGUNAN = "alamat_bangunan";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_ALAMAT = "alamt";
    public static final String COLUMN_HP = "hp";
    private static final String db_name = "data_bangunan.db";
    private static final int db_version = 1;

    // Perintah SQL untuk membuat tabel database baru
    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_KODE + " integer primary key autoincrement, "
            + COLUMN_NAMA_BANGUNAN + " varchar(50) not null, "
            + COLUMN_JML_LANTAI + " integer not null, "
            + COLUMN_THN_DIBUAT + " integer not null, "
            + COLUMN_ALAMAT_BANGUNAN + " varchar(100) not null, "
            + COLUMN_LATITUDE + " double not null, "
            + COLUMN_LONGITUDE + " double not null, "
            + COLUMN_NAMA + " varchar(50) not null, "
            + COLUMN_ALAMAT + " varchar(100) not null, "
            + COLUMN_HP + " integer not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
