package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

import java.util.ArrayList;

import Objetos.Log;

public class History_act extends AppCompatActivity {
    private String address;
    private ListView lv_transacciones;
    private ArrayList<Log> registros = new ArrayList<>();
    private Log registroSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent ad = getIntent();
        address = ad.getStringExtra("address");
        lv_transacciones = findViewById(R.id.lv_transacciones);

        this.fetch();

        lv_transacciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registroSelected = (Log) adapterView.getItemAtPosition(i);
            }
        });
    }



    public void Delete(View view) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();
            int id = registroSelected.getTransactionId();
            db.delete("transacciones", "id=" + id, null);
            Toast.makeText(getBaseContext(), "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
            this.fetch();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fetch();
    }


    protected void fill() {
            ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, registros);
            lv_transacciones.setAdapter(adapter);
    }
    protected void fetch() {
        try {
            registros = new ArrayList<Log>();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor file = db.rawQuery("SELECT * FROM transacciones", null);

            if(file.moveToFirst()) {
                Log r = new Log(file.getInt(0), file.getString(1), file.getString(2), file.getInt(3));
                registros.add(r);
                while(file.moveToNext()) {
                    registros.add(new Log(file.getInt(0), file.getString(1), file.getString(2), file.getInt(3)));
                }
            } else {
                Toast.makeText(getBaseContext(), "No hay registros", Toast.LENGTH_SHORT).show();
            }
            fill();
            file.close();
        } catch(Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void Show(View v) {
        fetch();
    }
}