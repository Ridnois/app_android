package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues cont = new ContentValues();
        cont.put("sender", from);
        cont.put("receiver", to);
        cont.put("amount", amount);
        db.insert("transacciones", null, cont);
        et_sendto.setText("");
        et_sendamount.setText("");
    }
    public void Send(View view) {
        try {
            String to = et_sendto.getText().toString().trim();
            final String am = String.valueOf(et_sendamount.getText());
            int amount = Integer.parseInt(am);
            transfer(address, to, amount);

        } catch (Exception e) {
            tv_senderror.setText(e.toString());
        }
    }
}