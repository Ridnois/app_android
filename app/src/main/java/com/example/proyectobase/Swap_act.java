package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

import Objetos.Token;

public class Swap_act extends AppCompatActivity {
    private Token token;
    private EditText et_amount;
    private Button preview;
    private TextView tv_result;
    private String[] tokens = {"ICE", "USD"};
    private Spinner spinner;
    private String address;

    private int icebalance, usdbalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tokens);
        spinner.setAdapter(adapter);
        token = new Token();
        tv_result = findViewById(R.id.tv_result);
        et_amount = findViewById(R.id.et_amount);
        Intent ad = getIntent();
        address = ad.getStringExtra("address");
        /*
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2 );
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor file = db.rawQuery("SELECT * FROM balances WHERE address=" + address, null);
        if(file.moveToFirst()) {
            icebalance = file.getInt(3);
            usdbalance = file.getInt(4);
        }
        */
    }
    /*
    public void Swap(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2 );
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor file = db.rawQuery("SELECT * FROM balances", null);
        } catch (Exception e) {
            tv_result.setText("Error");
        }
    }

     */
    public void Preview(View v) {
        try {
            String selected = spinner.getSelectedItem().toString();
            final String am = String.valueOf(et_amount.getText());
            int amount = Integer.parseInt(am);
            int ratio = token.getIcePerUSD();
            if(selected.equals("ICE")) {
                tv_result.setText("You receive " + amount / ratio + " USD per " + amount + " ICE");
            } else {
                tv_result.setText("You receive " + amount * ratio + " ICE per " + amount + " USD");
            }
        } catch (Exception e) {
            tv_result.setText("Debes ingresar un monto");
        }

    }
}