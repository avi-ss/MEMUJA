/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Venza
 */
@ViewScoped
@Named(value = "ctrlLogin")
public class UsuariosLoginController implements Serializable {

    @Inject
    FacesContext fc;

    @Inject
    SecurityContext sc; //JEE security API access

    @Inject
    ExternalContext ec; //JEE web server

    @Inject
    HttpServletRequest req; //Current http request

    //view-model
    @Size(min = 1, message = "Nombre de usuario no puede ser vacío")
    private String username;

    @NotEmpty(message = "Introduzca una clave válida")
    private String password;

    private static final Logger logger = Logger.getLogger(UsuariosLoginController.class.getName());

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() { //Login form action
        String view = "";
        //Prepare data for programmatic authentication
        AuthenticationParameters ap = new AuthenticationParameters();
        Credential cred = new UsernamePasswordCredential(username, password);
        ap.credential(cred).newAuthentication(true);
        HttpServletResponse resp = (HttpServletResponse) ec.getResponse();

        //Programmatic authentication: pass auth request to server
        if (sc.authenticate(req, resp, ap) == AuthenticationStatus.SUCCESS) {
            view = "/index?faces-redirect=true";
            logger.log(Level.INFO, "Usuario autenticado");
        } else {
            fc.addMessage("", new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "Error de autenticación", "")
            );
            logger.log(Level.WARNING, "Error de autenticación");
        }
        return view;
    }

    public String logout() throws ServletException {
        req.logout(); //Clear Principal info
        req.getSession().invalidate();
        //ec.invalidateSession();
        return "/index?faces-redirect=true";
    }

    public boolean esADMIN() {
        return req.isUserInRole("ADMINISTRADORES");
    }

    public boolean isConnected() {
        return req.isUserInRole("ADMINISTRADORES") || req.isUserInRole("USUARIOS");
    }
}
