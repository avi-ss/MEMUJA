/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Memes;

import com.daw.e2.memuja.daw.e2.Comentarios.Comentario;
import com.daw.e2.memuja.daw.e2.DAOJpa;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author alber
 */
@RequestScoped
@DAOJpa //Qualifier para esta implementación
@Transactional
public class MemeDAOJpa implements MemeDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(MemeDAOJpa.class.getName());
    
    public MemeDAOJpa(){
    }
    
    @Override
    public Meme busca(long id) {
        return em.find(Meme.class, id);
    }

    @Override
    public List<Meme> buscaTodos() {
        List<Meme> memes;
        try {
            memes = em.createQuery("SELECT m FROM Meme m", Meme.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los memes", e);
            memes = new ArrayList<>();
        }
        return memes;
    }

    @Override
    public boolean crea(Meme meme) {
        try {
            em.persist(meme);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede crear el meme", e);
            return false;
        }
    }

    @Override
    public boolean borra(long id) { // Modificar para cumplir restriccion clave foránea
        try {
            Meme m = em.find(Meme.class, id);
            em.createQuery("DELETE FROM Comentario c WHERE c.meme.id =" + m.getId()).executeUpdate();
            em.createQuery("DELETE FROM Meme m WHERE m.id =" + m.getId()).executeUpdate();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede borrar el meme", e);
            return false;
        }
    }

    @Override
    public boolean edita(Meme meme) {
        try {
            em.merge(meme);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede editar el meme", e);
            return false;
        }
    }

    @Override
    public Meme buscaMejorMeme() {
        List<Meme> mejorMeme;
        try {
            mejorMeme = em.createQuery("SELECT m FROM Meme m WHERE m.likes=(SELECT MAX(m1.likes) FROM Meme m1)", Meme.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los memes", e);
            mejorMeme = new ArrayList<>();
        }
        
        if(mejorMeme.isEmpty()){
            return null;
        }
        
        return mejorMeme.get(0);
    }

    @Override
    public int numComentarios(long id) {
        List<Comentario> comentarios;
        
        try {
            comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.meme.id = " + id, Comentario.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los memes", e);
            comentarios = new ArrayList<>();
        }
        
        return comentarios.size();
    }
}