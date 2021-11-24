package com.example.proyectobase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

import org.w3c.dom.Text;

public class History_act extends AppCompatActivity {
    private String address;
    private LinearLayout ll_transacciones;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent ad = getIntent();
        address = ad.getStringExtra("address");
        ll_transacciones = findViewById(R.id.ll_transacciones);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Show(View view) {
        try {

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "icecream", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor file = db.rawQuery("SELECT * FROM transacciones", null);

            if(file.moveToFirst()) {
                while(file.moveToNext()) {
                    TextView item = new TextView(History_act.this);
                    String from = file.getString(1);
                    String to = file.getString(2);
                    int amount = file.getInt(3);

                    item.setText(from + " envió " + amount + " ICE a " + to);
                    item.setTextAppearance(R.style.Transaction);
                    ll_transacciones.addView(item);
                }
                file.close();
            }
        } catch(Exception e) {
            // do somethin
        }
    }
}