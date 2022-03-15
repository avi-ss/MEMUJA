/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Comentarios;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Memes.Meme;
import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import com.daw.e2.memuja.daw.e2.Usuarios.UsuariosDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alber
 */
@Named(value = "ctrlComentarios")
@ViewScoped
public class ComentarioController implements Serializable {

    @Inject
    @DAOJpa
    private ComentarioDAO comentariosDAO;

    @Inject
    @DAOJpa
    private UsuariosDAO usuariosDAO;
    
    @Inject
    HttpServletRequest req; //Current http request
    
    private Meme meme;

    private Comentario comentario;
    private List<Comentario> comentarios;

    private static final Logger logger = Logger.getLogger(ComentarioController.class.getName());

    public ComentarioController() {
    }

    @PostConstruct
    private void init() {
        logger.log(Level.INFO, "Inicializando controlador de comentarios!");
        comentario = new Comentario();
        comentarios = new ArrayList<>();
    }

    public void reset() {
        comentario = new Comentario();
        recupera(meme);
    }

    public String crea() {
        logger.log(Level.INFO, "Creando Comentario: {0}", comentario.getTexto());

        
        Usuario usuario = usuariosDAO.buscaPorUsuario(req.getRemoteUser());
        Comentario c = new Comentario(meme, usuario, comentario.getTexto());
        comentariosDAO.crea(c);
        
        reset();
        
        return "meme?faces-redirect=true&id=" + meme.getId();
    }

    public String borra() {
        return borra(comentario.getId());
    }

    public String borra(long id) {
        logger.log(Level.INFO, "Borrando Comentario {0}", id);
        comentariosDAO.borra(id);
        return "publication?faces-redirect=true&id=" + meme.getId();
    }

    public void recupera() {
        logger.log(Level.INFO, "Recuperando Comentario del DAO: {0}", comentario.getId());
        comentario = comentariosDAO.busca(comentario.getId());
    }

    public void recupera(Meme meme) {
        this.meme = meme;
        comentarios = comentariosDAO.buscaPorMeme(meme);
        logger.log(Level.INFO, "Recuperando Comentarios del DAO: {0}", comentarios.size());
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public List<Comentario> getComentarios() {
        logger.log(Level.INFO, "Recuperando Comentarios: {0}", comentarios.size());
        return comentarios;
    }
}