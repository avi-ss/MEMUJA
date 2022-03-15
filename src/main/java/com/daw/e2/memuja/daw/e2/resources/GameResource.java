package com.daw.e2.memuja.daw.e2.resources;

import com.daw.e2.memuja.daw.e2.DAOJpa;
import com.daw.e2.memuja.daw.e2.Memes.Meme;
import com.daw.e2.memuja.daw.e2.Memes.MemeDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alberto Longo
 */
@Path("game")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, javax.enterprise.context.RequestScoped
public class GameResource {

    @Inject
    @DAOJpa
    private MemeDAO memeDAO;

    @GET
    @Path("/{id}")
    public Response obtenerMeme(@PathParam("id") long id) {
        Response response;
        Meme m = memeDAO.busca(id);
        if (m != null) { //return status 200 and JSON {"id": 1, "nombre":...}
            response = Response.ok(m).build();
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @GET
    public List<Meme> listado() {
        return memeDAO.buscaTodos();
    }
}
