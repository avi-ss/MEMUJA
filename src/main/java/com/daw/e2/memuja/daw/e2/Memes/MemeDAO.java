/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Memes;

import java.util.List;

/**
 *
 * @author alber
 */
public interface MemeDAO {

    public Meme busca(long id);

    public List<Meme> buscaTodos();
    
    public Meme buscaMejorMeme();

    public boolean crea(Meme meme);

    public boolean borra(long id);

    public boolean edita(Meme meme);
    
    public int numComentarios(long id);
}
