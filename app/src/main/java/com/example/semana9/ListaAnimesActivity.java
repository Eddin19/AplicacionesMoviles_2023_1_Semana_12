package com.example.semana9;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9.adapters.NameAdapter;
import com.example.semana9.entities.User;
import com.example.semana9.services.UserService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaAnimesActivity extends AppCompatActivity {

    private RecyclerView rvLista;
    private NameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animes);

        rvLista = findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        Call<List<User>> call = service.getAllUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("AM_APP", String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    List<User> users = response.body();

                    Log.i("AM_APP", String.valueOf(users.size()));
                    Log.i("AM_APP", new Gson().toJson(users));

                    adapter = new NameAdapter(users, ListaAnimesActivity.this);
                    rvLista.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Maneja el error de acuerdo a tus necesidades
            }
        });
    }

    public void actualizarContacto(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);

        Call<User> call = service.update(user.getId(), user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // El contacto se actualizó exitosamente en la API
                    User contactoActualizado = response.body();
                    Log.d("API", "Actualización exitosa. Contacto actualizado: " + contactoActualizado.toString());
                    // Realiza las acciones necesarias después de actualizar el contacto
                } else {
                    // Hubo un error al actualizar el contacto
                    int statusCode = response.code(); // Obtiene el código de respuesta HTTP
                    Log.e("API", "Error en la respuesta de la API. Código de respuesta: " + statusCode);
                    // Maneja el error de acuerdo a tus necesidades
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Hubo un error en la solicitud o en la comunicación con la API
                // Maneja el error de acuerdo a tus necesidades
            }
        });
    }

    public void eliminarContacto(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);

        Call<Void> call = service.delete(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // El contacto se eliminó exitosamente de la API
                    // Realiza las acciones necesarias después de eliminar el contacto
                } else {
                    // Hubo un error al eliminar el contacto
                    // Maneja el error de acuerdo a tus necesidades
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Hubo un error en la solicitud o en la comunicación con la API
                // Maneja el error de acuerdo a tus necesidades
            }
        });
    }

    public void agregarContacto(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<User> call = service.create(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // El nuevo contacto se agregó exitosamente a la API
                    User userAgregado = response.body();
                    // Realiza las acciones necesarias después de agregar el contacto
                } else {
                    // Hubo un error al agregar el contacto
                    // Maneja el error de acuerdo a tus necesidades
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Hubo un error en la solicitud o en la comunicación con la API
                // Maneja el error de acuerdo a tus necesidades
            }
        });
    }
}
