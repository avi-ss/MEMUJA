/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Comentarios;

import com.daw.e2.memuja.daw.e2.Memes.Meme;
import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author alber
 */
@Entity(name = "Comentario")
public class Comentario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn()
    private final Usuario usuario;

    @ManyToOne
    @JoinColumn()
    private final Meme meme;
    
    private String texto;
    private LocalDate fechaSubida;

    public Comentario() {
        this.usuario = new Usuario();
        this.meme = new Meme();
        this.texto = "";
        this.fechaSubida = LocalDate.now();
    }

    public Comentario(Meme meme, Usuario usuario, String texto) {
        this.usuario = usuario;
        this.meme = meme;
        this.texto = texto;
        this.fechaSubida = LocalDate.now();
    }

    public Comentario(Comentario other) {
        this.id = other.id;
        this.usuario = other.usuario;
        this.meme = other.meme;
        this.texto = other.texto;
        this.fechaSubida = other.fechaSubida;
    }

    public String getTexto() {
        return texto;
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
    
    public void setTexto(String comentario) {
        this.texto = comentario;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }

    public Meme getMeme() {
        return meme;
    }
}