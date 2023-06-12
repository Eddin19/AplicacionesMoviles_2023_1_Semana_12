package com.example.semana9;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semana9.adapters.NameAdapter;
import com.example.semana9.entities.User;
import com.example.semana9.services.UserService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPersonasActivity extends AppCompatActivity {
    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;
    private ImageView ivAvatar;
    private EditText etDescripcion;
    String urlImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        ivAvatar = findViewById(R.id.ivAvatar);
        etDescripcion = findViewById(R.id.etDescripcion);

        Button btnCamara = findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenCamara();
            }
        });

        Button btnGaleria = findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenGaleria();
            }
        });

        Button btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = etDescripcion.getText().toString();
                String imagenUrl = "https://demo-upn.bit2bittest.com";

                if(!descripcion.isEmpty() && !imagenUrl.isEmpty()){
                    // Crear un objeto Contacto con los datos obtenidos
                    User user = new User(descripcion, imagenUrl);

                    ListaAnimesActivity listaAnimesActivity = new ListaAnimesActivity();

                    // Llamar al método agregarContacto() para agregar el nuevo contacto
                    listaAnimesActivity.agregarContacto(user);

                    // Limpiar
                    ivAvatar.setImageDrawable(null);
                    etDescripcion.setText("");
                }else {
                    Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivAvatar.setImageBitmap(photo);

            String base64Image = convertBitmapToBase64(photo);
            Retrofit retrofit123 = new Retrofit.Builder()
                    .baseUrl("https://demo-upn.bit2bittest.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService services = retrofit123.create(UserService.class);
            Call<UserService.ImageResponse> call = services.saveImage(new UserService.ImageToSave(base64Image));

            call.enqueue(new Callback<UserService.ImageResponse>() {
                @Override
                public void onResponse(Call<UserService.ImageResponse> call, Response<UserService.ImageResponse> response) {
                    Log.i("Respuesta activa", response.toString());
                    if (response.isSuccessful()) {
                        UserService.ImageResponse imageResponse = response.body();
                        Log.i("Respues", response.toString());
                        urlImage = imageResponse.getUrl();
                        Log.i("Imagen url:", urlImage);

                        NameAdapter adaptador = new NameAdapter(urlImage);

                    } else {

                        Log.e("Error cargar imagen",response.toString());
                    }
                }

                @Override
                public void onFailure(Call<UserService.ImageResponse> call, Throwable t) {
                    // Error de red o de la API
                    Log.i("Respuesta inactiva", "");
                }
            });
        }

        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ivAvatar.setImageBitmap(bitmap);
        }

    }

    private void onOpenCamara() {
        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            // abrir camara
            Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
            abrirCamara();
        } else {
            // solicitar el permiso
            Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            requestPermissions(permissions, 1000);
        }
    }

    private void onOpenGaleria() {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }
        else {
            String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, 2000);
        }
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    private void imprimirImagenEnLog(String base64Image) {
        Log.d("ImagenBase64", base64Image);
    }
    private void saveImageUrl(String imageUrl) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("imageUrl", imageUrl);
        editor.apply();
    }
}