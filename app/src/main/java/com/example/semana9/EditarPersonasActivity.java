package com.example.semana9;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditarPersonasActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextNumero;
    private EditText editTextImagenURL;
//    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_personas);

        // Obtén las referencias de los elementos de la interfaz de usuario
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextImagenURL = findViewById(R.id.editTextImagenURL);
        //btnGuardar = findViewById(R.id.btnGuardar);

        // Obtén los datos pasados desde la actividad anterior
        String nombre = getIntent().getStringExtra("nombre");
        String numero = getIntent().getStringExtra("numero");
        String imagenURL = getIntent().getStringExtra("imagenURL");

        // Establece los valores en los campos de texto
        editTextNombre.setText(nombre);
        editTextNumero.setText(numero);
        editTextImagenURL.setText(imagenURL);

//        // Establece el click listener para el botón Guardar
//        btnGuardar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Obtén los nuevos valores de los campos
//                int idContacto = getIntent().getIntExtra("id", 0);
//                String nuevoNombre = editTextNombre.getText().toString().trim();
//                String nuevoNumero = editTextNumero.getText().toString().trim();
//                String nuevaImagenURL = editTextImagenURL.getText().toString().trim();
//
//                // Crear un objeto User con los datos obtenidos
//                User user = new User(idContacto, nuevoNombre, nuevoNumero, nuevaImagenURL);
//
//                // Obtén la instancia de la actividad ListaAnimesActivity
//                ListaAnimesActivity listaAnimesActivity = (ListaAnimesActivity) getParent();
//
//                // Llama al método actualizarContacto() en la instancia de ListaAnimesActivity
//                listaAnimesActivity.actualizarContacto(user);
//
//                // Muestra un mensaje de éxito
//                Toast.makeText(EditarPersonasActivity.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
//
//                // Cierra la actividad y vuelve a la actividad anterior
//                finish();
//            }
//        });
    }
}
