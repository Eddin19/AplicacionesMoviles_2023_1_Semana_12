package com.example.semana9.entities;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private String imagenUrl;
    private String descripcion;
    private List<Comentario> comentarios;

    public Publicacion(String imagenUrl, String descripcion) {
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
        this.comentarios = new ArrayList<>();
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
}

