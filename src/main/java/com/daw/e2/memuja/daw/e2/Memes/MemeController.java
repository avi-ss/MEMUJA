/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.Memes;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import com.daw.e2.memuja.daw.e2.Usuarios.UsuariosDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author fjavi
 */
@Named(value = "ctrlMemes")
@ViewScoped
public class MemeController implements Serializable {

    @Inject
    @DAOJpa
    private MemeDAO memesDAO;

    @Inject
    @DAOJpa
    private UsuariosDAO usuariosDAO;

    @Inject
    HttpServletRequest req; //Current http request
    
    @Inject
    FacesContext fc;

    private Meme meme;
    private List<Meme> memes;

    //Images filesystem path and URL as defined in /WEB-INF/glassfish-web.xml
    private final String imagesPath="/tmp/images";   //enter the folder absolute path in server filesystem
    private final String imagesUrl="/images";       //Images public URL 
                                                       
    private File imageFolder;
    private Part file;
    
    private static final Logger logger = Logger.getLogger(MemeController.class.getName());

    public MemeController() {

    }

    @PostConstruct
    private void init() {
        logger.log(Level.INFO, "Inicializando MemeController");
        meme = new Meme();
        memes = memesDAO.buscaTodos();

        imageFolder=new File(imagesPath);
    }

    public Meme getMeme() {
        logger.log(Level.INFO, "Recuperando Meme {0}", meme.getId());
        return meme;
    }

    public void setMeme(Meme meme) {
        logger.log(Level.INFO, "Estableciendo Meme {0}", meme.getId());
        this.meme = meme;
    }

    public List<Meme> getMemes() {
        logger.log(Level.INFO, "Recuperando Memes {0}", memes.size());
        return memes;
    }

    public void recupera() {
        logger.log(Level.INFO, "Recuperando Meme (DAO) {0}", meme.getId());
        meme = memesDAO.busca(meme.getId());
    }

    public void recupera(long id) {
        logger.log(Level.INFO, "Recuperando Meme {0}", id);
        meme = memesDAO.busca(id);
    }

    public void reset() {
        meme = new Meme();
    }

    public String borra() {
        return borra(meme.getId());
    }

    public String borra(long id) {
        logger.log(Level.INFO, "Borrando Meme {0}", id);
        memesDAO.borra(id);
        return "admin?faces-redirect=true"; //@TODO: Esto habrá que cambiarlo
    }

    public String crea() {
        Usuario usuario = usuariosDAO.buscaPorUsuario(req.getRemoteUser());
        meme.setUsuario(usuario);
        
        String sendFile = sendFile();
        logger.log(Level.INFO, "Creando Meme: {0}", sendFile);
        meme.setRutaImg(sendFile);
        
        memesDAO.crea(meme);

        return "publication?faces-redirect=true&id=" + meme.getId();
    }

    public String edita(long id) {
        recupera(id);

        if (this.meme != null) {
            logger.log(Level.INFO, "Editando meme {0}", id);
            return "edita?faces-redirect=true&isbn=" + meme.getId();
        } else {
            logger.log(Level.SEVERE, "ERROR AL EDITAR: No existe meme {0}", id);
            return "index?faces-redirect=true";
        }
    }

    public Meme getMejorMeme() {
        Meme mejorMeme = memesDAO.buscaMejorMeme();
        logger.log(Level.INFO, "Recuperando Mejor Meme: {0}", mejorMeme.getTitulo());

        return mejorMeme;
    }
    
    public int numComentarios(long id){
        return memesDAO.numComentarios(id);
    }
    
    public int numComentarios(){
        return memesDAO.numComentarios(meme.getId());
    }
    
    //view-model properties
    
    public String getImagesUrl() {
        return imagesUrl;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    //view actions
    public String sendFile() {
            String newFileName = file.getSubmittedFileName();

            //Move temp file to web public folder
            try (InputStream input = file.getInputStream()) {
                File destFile = new File(imageFolder, newFileName);
                Files.copy(input, destFile.toPath());
                fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Archivo enviado correctamente",""));
                logger.log(Level.INFO, "Uploaded file: {0}", newFileName);
            } catch (IOException e) {
                fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se ha podido enviar el archivo",""));
            }
        
        return newFileName;
    }
}
