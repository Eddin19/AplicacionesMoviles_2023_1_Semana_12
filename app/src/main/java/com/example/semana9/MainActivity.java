package com.example.semana9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Button btnCrear = this.findViewById(R.id.btnCrear);
        Button btnContactos = this.findViewById(R.id.btnContactos);

        btnCrear.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CrearPersonasActivity.class);
            startActivity(intent);

        });

        btnContactos.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListaAnimesActivity.class);
            startActivity(intent);

        });

    }
}