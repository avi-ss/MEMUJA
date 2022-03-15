/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Comentarios;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Memes.Meme;
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
 * @author fjavi
 */
@RequestScoped
@DAOJpa //Qualifier para esta implementación
@Transactional
public class ComentarioDAOJpa implements ComentarioDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(ComentarioDAOJpa.class.getName());

    @Override
    public Comentario busca(long id) {
        return em.find(Comentario.class, id);
    }

    @Override
    public List<Comentario> buscaPorMeme(Meme meme) {
        List<Comentario> comentarios;

        try {
            comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.meme.id=:meme_id", Comentario.class).setParameter("meme_id", meme.getId()).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los comentarios", e);
            comentarios = new ArrayList<>();
        }

        return comentarios;
    }

    @Override
    public boolean crea(Comentario comentario) {
        try {
            em.persist(comentario);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede crear el comentario", e);
            return false;
        }
    }

    @Override
    public boolean borra(long id) {
        try {
            Comentario m = em.find(Comentario.class, id);
            em.remove(m);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede borrar el comentario", e);
            return false;
        }
    }
}