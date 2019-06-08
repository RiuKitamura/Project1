package com.example.acer.project1;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private Button nextButton;
    private EditText namaBangunan;
    private EditText jmlLantai;
    private EditText thnDibuat;
    private EditText alamatBangunan;
    private EditText latitude;
    private EditText longitude;
    private EditText namaPerson;
    private EditText alamatPerson;
    private EditText nomorPerson;
    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setTitle("Formulir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner snr_struktur = (Spinner) findViewById(R.id.spinner_struktur);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.struktur));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snr_struktur.setAdapter(myAdapter);

        nextButton = (Button) findViewById(R.id.next_btn);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                submit(v);
            }
        });
        namaBangunan = (EditText) findViewById(R.id.nama_bangunan);
        jmlLantai = (EditText) findViewById(R.id.jml_lantai);
        thnDibuat = (EditText) findViewById(R.id.thn_dibuat);
        alamatBangunan = (EditText) findViewById(R.id.alamat_bangunan);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        namaPerson = (EditText) findViewById(R.id.nama_person);
        alamatPerson = (EditText) findViewById(R.id.alamat_person);
        nomorPerson = (EditText) findViewById(R.id.nomor_person);

        // instantiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        // membuat sambungan baru ke database
        dataSource.open();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed(){
        ViewDialog alert = new ViewDialog();
        alert.showDialog(FormActivity.this);

    }

    public class ViewDialog {
        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.popup);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Button mDialogNo = dialog.findViewById(R.id.frmNo);
            mDialogNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"No" ,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            Button mDialogOk = dialog.findViewById(R.id.frmOk);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Ok" ,Toast.LENGTH_SHORT).show();
                    FormActivity.super.onBackPressed();
                }
            });

            dialog.show();
        }
    }

    public void submit(View v) {
        // Inisialisasi data barang
        String namaB = null;
        String jmlLti = null;
        String thn = null;
        String almtB = null;
        String lati = null;
        String longi = null;
        String namaP = null;
        String almtP = null;
        String noP = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
                DataBangunan dtaBangunan = null;
        if(namaBangunan.getText()!=null && jmlLantai.getText()!=null && thnDibuat.getText()!=null
                && alamatBangunan.getText()!=null && latitude.getText()!=null && longitude.getText()!=null
                && namaPerson.getText()!=null && alamatPerson.getText()!=null && nomorPerson.getText()!=null)
        {
            /* jika field nama, merk, dan harga tidak kosong
             * maka masukkan ke dalam data barang*/
            namaB = namaBangunan.getText().toString();
            jmlLti = jmlLantai.getText().toString();
            thn = thnDibuat.getText().toString();
            almtB = alamatBangunan.getText().toString();
            lati = latitude.getText().toString();
            longi = longitude.getText().toString();
            namaP = namaPerson.getText().toString();
            almtP = alamatPerson.getText().toString();
            noP = nomorPerson.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.next_btn:
                // insert data barang baru
                dtaBangunan = dataSource.createDtaBangunan(namaB, jmlLti, thn, almtB, lati, longi, namaP, almtP, noP);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Barang\n" +
                        "nama" + dtaBangunan.getNama_bangunan() +
                        "lantai" + dtaBangunan.getAlamat_bangunan() +
                        "harga" + dtaBangunan.getThn_dibuat(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
