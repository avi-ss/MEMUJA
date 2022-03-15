/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Usuarios;

import com.daw.e2.memuja.daw.e2.Comentarios.Comentario;
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
 * @author alber
 */
@RequestScoped
@DAOJpa //Qualifier para esta implementación
@Transactional
public class UsuariosDAOJpa implements UsuariosDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(UsuariosDAOJpa.class.getName());

    @Override
    public Usuario busca(long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> buscaTodos() {
        List<Usuario> usuarios;
        try {
            usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los usuarios", e);
            usuarios = new ArrayList<>();
        }
        return usuarios;
    }

    @Override
    public boolean crea(Usuario usuario) {
        try {
            em.persist(usuario);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede crear el usuario", e);
            return false;
        }
    }

    @Override
    public boolean borra(long id) {
        try {
            Usuario u = em.find(Usuario.class, id);
            em.createQuery("DELETE FROM Comentario c WHERE c.usuario.id =" + u.getId()).executeUpdate();
            em.createQuery("DELETE FROM Comentario c WHERE c.meme.usuario.id =" + u.getId()).executeUpdate();
            em.createQuery("DELETE FROM Meme m WHERE m.usuario.id =" + u.getId()).executeUpdate();
            em.createQuery("DELETE FROM Usuario u WHERE u.id=" + u.getId()).executeUpdate();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede borrar el usuario", e);
            return false;
        }
    }

    @Override
    public boolean edita(Usuario usuario) {
        try {
            em.merge(usuario);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede editar el usuario", e);
            return false;
        }
    }

    @Override
    public List<Usuario> buscaMejoresUsuarios() {

        List<Usuario> potencialesMejores;
        try {
            potencialesMejores = em.createQuery("SELECT u FROM Usuario u WHERE "
                    + "(SELECT COUNT(m) FROM Meme m WHERE m.usuario.id=u.id) > "
                    + "(SELECT COUNT(m1.id) FROM Meme m1, Usuario u1 WHERE u1.id = m1.usuario.id)/(SELECT COUNT(u2) FROM Usuario u2)", Usuario.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los usuarios", e);
            potencialesMejores = new ArrayList<>();
        }

        int NUM_MEJORES_USUARIOS = (potencialesMejores.size() > 3) ? 3 : potencialesMejores.size();

        List<Usuario> mejores = new ArrayList<>();

        for (int i = 0; i < NUM_MEJORES_USUARIOS; i++) {
            mejores.add(potencialesMejores.get(i));
        }

        return mejores;
    }

    @Override
    public List<Usuario> buscaRolUsuarios() {
        List<Usuario> usuarios;
        try {
            usuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.rol='USUARIOS'", Usuario.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los usuarios", e);
            usuarios = new ArrayList<>();
        }
        return usuarios;
    }

    @Override
    public int numUsuarios() {
        return buscaTodos().size();
    }

    @Override
    public Usuario buscaPorUsuario(String usuario) {
        List<Usuario> user = new ArrayList<>();
        try {
            user = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario =:usuario").setParameter("usuario", usuario).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede recuperar el usuario", e);

        }
        if (user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }

    @Override
    public List<Meme> buscaMemes(String usuario) {
        List<Meme> memes = new ArrayList<>();
        
        try {
            memes = em.createQuery("SELECT m FROM Meme m WHERE m.usuario.usuario =:usuario").setParameter("usuario", usuario).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los memes", e);
        }
        
        return memes;
    }
    
    @Override
    public List<Comentario> buscaComentarios(String usuario) {
        List<Comentario> comentarios = new ArrayList<>();
        
        try {
            comentarios = em.createQuery("SELECT c FROM Comentario c, Meme m WHERE m.usuario.usuario=:usuario AND c.meme.id=m.id").setParameter("usuario", usuario).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los comentarios", e);
        }
        
        return comentarios;
    }

    @Override
    public long numLikes(String usuario) {
        List<Object> likes = new ArrayList<>();
        
        try {
            likes = em.createQuery("SELECT SUM(m.likes) FROM Meme m WHERE m.usuario.usuario=:usuario GROUP BY m.usuario.usuario").setParameter("usuario", usuario).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar el numero de likes", e);
        }
        
        long numLikes = 0;
        if(!likes.isEmpty()){
            numLikes = (long)likes.get(0);
            logger.log(Level.INFO, "Encontrados likes");
        }
        
        return numLikes;
    }
}
