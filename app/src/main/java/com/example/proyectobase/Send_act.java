package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

public class Send_act extends AppCompatActivity {
    private EditText et_sendto, et_sendamount;
    private TextView tv_senderror;
    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        et_sendto = findViewById(R.id.et_sendto);
        et_sendamount = findViewById(R.id.et_sendamount);
        tv_senderror = findViewById(R.id.tv_senderror);
        Intent ad = getIntent();
        address = ad.getStringExtra("address");
    }

    public void transfer(String from, String to, int amount) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues cont = new ContentValues();
        cont.put("sender", from);
        cont.put("receiver", to);
        cont.put("amount", amount);
        db.insert("transacciones", null, cont);
        int balance = 0;
        Cursor file = db.rawQuery("SELECT icebalance FROM balances WHERE address='" + address + "'", null);

        if(file.moveToFirst()) {
            balance = file.getInt(0);
        }
        if(balance < amount) {
            Toast.makeText(getBaseContext(), "No posees suficientes fondos!", Toast.LENGTH_SHORT).show();
        }
        et_sendto.setText("");
        et_sendamount.setText("");

        tv_senderror.setVisibility(View.INVISIBLE);
        ContentValues updatedBalance = new ContentValues();
        updatedBalance.put("icebalance", balance - amount);
        db.update("balances", updatedBalance, "address= '" + address + "'", null);
        Toast.makeText(getBaseContext(), "Enviaste " + amount + " ICE a " + to + "!", Toast.LENGTH_SHORT).show();
    }

    public void Send(View view) {
        try {
            String to = et_sendto.getText().toString().trim();
            final String am = String.valueOf(et_sendamount.getText());
            int amount = Integer.parseInt(am);
            transfer(address, to, amount);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}