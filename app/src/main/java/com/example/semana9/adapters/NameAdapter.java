package com.example.semana9.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9.EditarPersonasActivity;
import com.example.semana9.ListaAnimesActivity;
import com.example.semana9.R;
import com.example.semana9.entities.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    private List<User> items;
    private ListaAnimesActivity listaAnimesActivity;
    private Context context;

    private String imageUrl;

    public NameAdapter(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public NameAdapter(List<User> items, ListaAnimesActivity listaAnimesActivity) {
        this.items = items;
        this.listaAnimesActivity = listaAnimesActivity;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_string, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        User item = items.get(position);
        View view = holder.itemView;

        TextView tvName = view.findViewById(R.id.tvName);
        ImageView imageView = view.findViewById(R.id.imageView);
        tvName.setText(item.descripcion);

//        String numero = tvName.getText().toString();
//        String imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numero + ".png";
//        Picasso.get().load(imageUrl).into(imageView);

//        Picasso.get()
//                .load(item.getImagenUrl()) // Carga la imagen desde el enlace proporcionado en el objeto Libro
//                .into(imageView);

        Picasso.get().load(item.getImagenUrl()).into(imageView);

//        Picasso.get()
//                .load(item.img) // Carga la imagen desde el enlace proporcionado en el objeto Libro
//                .into(imageView);

        // Eliminar
        Button btnEliminar = holder.itemView.findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idContacto = item.getId();

                // Utiliza la referencia a la actividad ListaAnimesActivity existente
                listaAnimesActivity.eliminarContacto(idContacto);

                items.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        // Editar
        Button btnEditar = holder.itemView.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                User clickedItem = items.get(clickedPosition);

                String nombre = tvName.getText().toString();

                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, EditarPersonasActivity.class);
                intent.putExtra("id", clickedItem.getId());
                intent.putExtra("nombre", clickedItem.getDescripcion());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void actualizarLista(List<User> nuevaLista) {
        items.clear();
        items.addAll(nuevaLista);
        notifyDataSetChanged();
    }
}
