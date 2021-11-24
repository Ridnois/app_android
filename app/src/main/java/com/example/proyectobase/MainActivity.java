package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import Objetos.Administrador;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private TextView msg;
    private ProgressBar progress;
    private Button btn;

    private Administrador admin = new Administrador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.t_username);
        password = findViewById(R.id.t_password);
        msg = findViewById(R.id.tv_error);
        btn = findViewById(R.id.button);
        progress = findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acá corre mi tarea asincrona
                LoadSession(v);
            }
        });
    }

    // Tarea asincrona
    class Task extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.setVisibility(View.INVISIBLE);
            Intent i = new Intent(getBaseContext(), Dashboard_act.class);
            i.putExtra("address", username.getText().toString().trim());
            startActivity(i);
        }
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
        new Task().execute();
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