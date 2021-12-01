package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

import Objetos.Token;

public class Swap_act extends AppCompatActivity {
    private Token token;
    private EditText et_amount;
    private TextView tv_result;
    private String[] tokens = {"ICE", "USD"};
    private Spinner spinner;
    private String address;
    private String selected;
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

        checkBalance();
    }

    protected void Clean() {
        et_amount.setText("");
    }

    public void checkBalance() {
        try {
            AdminSQLiteOpenHelper token = new AdminSQLiteOpenHelper(this, "icecream", null, 2);
            SQLiteDatabase _token = token.getWritableDatabase();
            Cursor addressData = _token.rawQuery("SELECT * FROM balances WHERE address ='" + address + "'", null);
            if(addressData.moveToFirst()) {
                icebalance = addressData.getInt(2);
                usdbalance = addressData.getInt(3);
                tv_result.setText("Posees " + icebalance + " ICE y " + usdbalance + " USD");
            }
        } catch (Exception e) {
            tv_result.setText(e.toString());
        }
    }

    public void Swap(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2 );
            SQLiteDatabase db = admin.getWritableDatabase();
            final String am = String.valueOf(et_amount.getText());
            int amount = Integer.parseInt(am);
            int ratio = token.getIcePerUSD();
            String selected = spinner.getSelectedItem().toString();
            boolean ice = selected.equals("ICE");
            if(ice) {
                icebalance -= amount;
                usdbalance += amount / ratio;
                tv_result.setText("Intercambiar " + amount + " ICE por " + amount / ratio + " USD");
            } else {
                icebalance += amount * ratio;
                usdbalance -= amount;
                tv_result.setText("Intercambiar " + amount + " USD por " + amount * ratio + " ICE");
            }

            ContentValues cont = new ContentValues();
            cont.put("icebalance", icebalance);
            cont.put("usdbalance", usdbalance);

            db.update("balances", cont, "address='"+ address + "'", null);
            Toast.makeText(getBaseContext(), "Swap realizado exitosamente!", Toast.LENGTH_SHORT).show();
            this.Clean();
        } catch (Exception e) {
            tv_result.setText(e.toString());
        }
    }

    public void Preview(View v) {
        try {
            final String am = String.valueOf(et_amount.getText());
            int amount = Integer.parseInt(am);
            int ratio = token.getIcePerUSD();
            String selected = spinner.getSelectedItem().toString();

            if(selected.equals("ICE")) {
                tv_result.setText("Recibes " + amount / ratio + " USD por tus " + amount + " ICE");
            } else {
                tv_result.setText("Recibes " + amount * ratio + " ICE por tus " + amount + " USD");
            }
        } catch (Exception e) {
            tv_result.setText("Debes ingresar un monto");
        }
    }
}