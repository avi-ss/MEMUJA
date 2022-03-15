/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2.resources;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import com.daw.e2.memuja.daw.e2.Usuarios.UsuariosDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alber
 */
@Path("memuja")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, javax.enterprise.context.RequestScoped
public class MemujaResource {
    
    @Inject
    @DAOJpa
    private UsuariosDAO usuarioDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoUsuario(@Valid Usuario u) {
        usuarioDAO.crea(u);
        return Response.ok(u).build();
    }
}
