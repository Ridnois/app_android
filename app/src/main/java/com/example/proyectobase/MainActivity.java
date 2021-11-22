package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import Objetos.Administrador;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private TextView msg;

    private Administrador admin = new Administrador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.t_username);
        password = findViewById(R.id.t_password);
        msg = findViewById(R.id.tv_error);
    }

    public boolean filledAuth(String user, String pass) {
        return !user.isEmpty() && !pass.isEmpty();
    }

    public void LoadSession(View view) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(!filledAuth(user, pass)) {
            msg.setText("Debes rellenar todos los campos");
        } else {
            if(admin.isAdmin(user, pass)) {
                msg.setVisibility(View.INVISIBLE);
                callback(view, user);
            } else {
                msg.setText("Usuario o contraseña incorrectos");
            }
        }
    }
    // Cambiar de activity
    public void callback(View view, String user) {
        Intent i = new Intent(this, Dashboard.class);
        i.putExtra("address", user);
        startActivity(i);
    }

    public void Facebook(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://wwww.facebook.com"));
        startActivity(i);
    }

    public void Youtube(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com"));
        startActivity(i);
    }

    public void Twitter(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://twitter.com"));
        startActivity(i);
    }
}