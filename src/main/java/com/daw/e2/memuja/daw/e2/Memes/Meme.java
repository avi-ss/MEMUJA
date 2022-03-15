/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Memes;

import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author fjavi
 */
@Entity(name = "Meme")
public class Meme implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Size(min = 8, message = "La longitud del titulo debe ser mayor de {min} caracteres")
    private String titulo;
    private String rutaImg;
    private long likes;

    private final LocalDate fechaSubida;

    // Guarda el usuario que es propietario del meme
    @ManyToOne
    @JoinColumn
    private Usuario usuario;
    
    public Meme() {
        this.titulo = "";
        this.rutaImg = "";
        this.likes = 0;
        this.fechaSubida = LocalDate.now();
        this.usuario = null;
    }

    public Meme(String titulo, String rutaImg, long likes, Usuario usuario) {
        this.titulo = titulo;
        this.rutaImg = rutaImg;
        this.likes = likes;
        this.fechaSubida = LocalDate.now();
        this.usuario = usuario;
    }

    public Meme(Meme other) {
        this.titulo = other.titulo;
        this.rutaImg = other.rutaImg;
        this.likes = other.likes;
        this.fechaSubida = other.fechaSubida;
        this.id = other.id;
        this.usuario = other.usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}