/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Usuarios;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author alber
 */
@Entity(name = "Usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Pattern(regexp = ".+@(red.)?ujaen.es", message = "Debe de ser un correo de la UJA (ejemplo@red.ujaen.es)")
    private String correo;

    @Size(min = 1, message = "La longitud del usuario debe ser mayor de {min} caracteres")
    private String usuario;

    @Size(min = 8, message = "La longitud de la contraseña debe der ser mayor de  {min} caracteres")
    private String contraseña;

    private final LocalDate fechaCreacion;
    private String rol;

    public Usuario() {
        this.usuario = null;
        this.contraseña = null;
        this.correo = null;
        this.fechaCreacion = LocalDate.now();
        this.rol = "USUARIOS";
    }

    public Usuario(String usuario, String contraseña, String correo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.fechaCreacion = LocalDate.now();
        this.rol = "USUARIOS";
    }

    public Usuario(Usuario other) {
        this.usuario = other.usuario;
        this.contraseña = other.contraseña;
        this.correo = other.correo;
        this.fechaCreacion = other.fechaCreacion;
        this.rol = other.rol;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol){
        this.rol = rol;
    }
}
