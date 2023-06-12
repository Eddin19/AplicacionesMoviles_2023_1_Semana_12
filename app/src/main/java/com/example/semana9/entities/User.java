package com.example.semana9.entities;


import java.util.ArrayList;
import java.util.List;

public class User {
    public int id;
    private String imagenUrl;
    public String descripcion;
    private List<Comentario> comentarios;
    public User(String imagenUrl, String descripcion) {
        this.id = id;
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
        this.comentarios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
