/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Usuarios;

import com.daw.e2.memuja.daw.e2.Comentarios.Comentario;
import com.daw.e2.memuja.daw.e2.Memes.Meme;
import java.util.List;

/**
 *
 * @author alber
 */
public interface UsuariosDAO {
    
    public int numUsuarios();
    
    public Usuario buscaPorUsuario(String usuario);
    
    public Usuario busca(long id);

    public List<Usuario> buscaTodos();
    
    public List<Usuario> buscaMejoresUsuarios();
    
    public List<Usuario> buscaRolUsuarios();

    public boolean crea(Usuario usuario);

    public boolean borra(long id);
    
    public boolean edita(Usuario usuario);
    
    public List<Meme> buscaMemes(String usuario);
    
    public long numLikes(String usuario);
    
    public List<Comentario> buscaComentarios(String usuario);
}
