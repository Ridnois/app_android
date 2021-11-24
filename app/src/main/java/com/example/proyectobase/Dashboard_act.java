package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

import Objetos.Token;

public class Dashboard_act extends AppCompatActivity {
    private TextView tv_greeting, tv_icebalance, tv_usdbalance;
    private Token token = new Token();
    private String address;
    private int usdBalance, iceBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tv_greeting = findViewById(R.id.tv_greeting);
        tv_icebalance = findViewById(R.id.tv_icebalance);
        tv_usdbalance = findViewById(R.id.tv_usdbalance);
        Intent ad = getIntent();
        address = ad.getStringExtra("address");
        tv_greeting.setText("Hola, " + address + "!");

        usdBalance = token.balanceOfUSD(address);
        iceBalance = token.balanceOfICE(address);

        tv_icebalance.setText(iceBalance + " ICE");
        tv_usdbalance.setText( "+" + usdBalance + " USD");

    }

    public void GoToContact(View view) {
        Intent i = new Intent(this, Contact_act.class);
        startActivity(i);
    }

    public void GoToSwap(View view) {
        Intent i = new Intent(this, Swap_act.class);
        i.putExtra("address", address);
        startActivity(i);
    }

    public void GoToSend(View view) {
        Intent i = new Intent(this, Send_act.class);
        i.putExtra("address", address);
        startActivity(i);
    }

    public void GoToHistory(View view) {
        Intent i = new Intent(this, History_act.class);
        i.putExtra("address", address);
        startActivity(i);
    }
}