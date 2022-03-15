/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Usuarios;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Memes.Meme;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alber
 */
@Named(value = "ctrlUsuarios")
@ViewScoped
public class UsuariosController implements Serializable {

    @Inject
    @DAOJpa
    private UsuariosDAO usuariosDAO;

    private Usuario usuario;
    private List<Usuario> usuarios;

    private static final Logger logger = Logger.getLogger(UsuariosController.class.getName());

    public UsuariosController() {
    }

    @PostConstruct
    private void init() {
        logger.log(Level.INFO, "Inicializando controlador de usuarios!");
        usuario = new Usuario();
        usuarios = usuariosDAO.buscaTodos();
    }

    public Usuario getUsuario() {
        logger.log(Level.INFO, "Recuperando Usuario: {0}", usuario.getCorreo());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        logger.log(Level.INFO, "Estableciendo Usuario: {0}", usuario.getUsuario());
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        logger.log(Level.INFO, "Recuperando Usuarios: {0}", usuarios.size());
        return usuarios;
    }

    public List<Usuario> getRolUsuarios() {
        List<Usuario> listaUsuarios = usuariosDAO.buscaRolUsuarios();
        return listaUsuarios;

    }

    public void recupera() {
        logger.log(Level.INFO, "Recuperando Usuario del DAO: {0}", usuario.getUsuario());

        usuario = usuariosDAO.buscaPorUsuario(usuario.getUsuario());
    }

    public void recupera(long id) {
        logger.log(Level.INFO, "Recuperando Usuario del DAO: {0}", id);
        usuario = usuariosDAO.busca(id);
    }

//    public void reset() {
//        usuario.setCorreo("");
//    }
    public String borra() {
        return borra(usuario.getId());
    }

    public String borra(long id) {
        logger.log(Level.INFO, "Borrando Usuario: {0}", id);
        usuariosDAO.borra(id);
        return "admin?faces-redirect=true";

    }

    public String crea() {
        logger.log(Level.INFO, "Creando Usuario: {0}", usuario.getUsuario());
        if(usuariosDAO.buscaPorUsuario(usuario.getUsuario()) != null){
            logger.log(Level.INFO,"NO SE PUEDE REGISTRAR.YA EXISTE NOMBRE USUARIO ASI");
            return "register?faces-redirect=true";
            
        }else{
        usuariosDAO.crea(usuario);
        return "profile?faces-redirect=true&id=" + usuario.getUsuario();
        }
      
        // TODO: La vista profile debe de tener el parametro ID para que lleve a la vista correspondiente

        // IMPORTANTE: Debemos asegurar que el nombre de usuario sea único para las vistas de perfil, 
        // o generar un identificador único siguiendo una secuencia.
    }

    public String edita(long id) {

        recupera(id);
        if (this.usuario != null) {
            logger.log(Level.INFO, "Editando usuario: {0}", usuario.getUsuario());
            return "edit?faces-redirect=true&id=" + usuario.getUsuario();
        } else {
            logger.log(Level.SEVERE, "ERROR AL EDITAR: No existe usuario: {0}", id);
            return "index?faces-redirect=true";
        }
    }

    public String guarda() {
        logger.log(Level.INFO, "Guardando usuario editado: {0}", usuario.getUsuario());
        usuariosDAO.edita(usuario);
        return "profile?faces-redirect=true&id=" + usuario.getUsuario();
        // TODO: La vista profile debe de tener el parametro ID para que lleve a la vista correspondiente
    }

    public List<Usuario> getMejoresUsuarios() {

        List<Usuario> mejores = usuariosDAO.buscaMejoresUsuarios();
        logger.log(Level.INFO, "Recuperando 3 mejores usuarios, NUM USUARIOS TOTAL: {0}", mejores.size());

        return mejores;
    }

    public List<Meme> getMemes() {
        List<Meme> memes = usuariosDAO.buscaMemes(usuario.getUsuario());

        logger.log(Level.INFO, "Recuperando los memes del usuario. Memes: ", memes.size());

        return memes;
    }
    
    public int getNumMemes(){
        return getMemes().size();
    }
    
    public long getNumLikes(){
        return usuariosDAO.numLikes(usuario.getUsuario());
    }
    
    public int getNumComentarios(){
        return usuariosDAO.buscaComentarios(usuario.getUsuario()).size();
    }
}
