package com.example.acer.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //AppCompatActivity
    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<DataBangunan> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton btn_add = (ImageButton) findViewById(R.id.add_btn);
        btn_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,FormActivity.class);
                startActivity(i);
            }
        });

        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllBarang();

        // masukkan data barang ke array adapter
        ArrayAdapter<DataBangunan> adapter = new ArrayAdapter<DataBangunan>(this,
                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        ListView listview =(ListView) findViewById(R.id.list1);
        listview.setAdapter(adapter);

    }
}
