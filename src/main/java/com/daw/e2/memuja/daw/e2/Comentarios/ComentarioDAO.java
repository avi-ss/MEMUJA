/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Comentarios;

import com.daw.e2.memuja.daw.e2.Memes.Meme;
import java.util.List;

/**
 *
 * @author alber
 */
public interface ComentarioDAO {
    
    public Comentario busca(long id);

    public List<Comentario> buscaPorMeme(Meme meme);

    public boolean crea(Comentario id);

    public boolean borra(long id);
}