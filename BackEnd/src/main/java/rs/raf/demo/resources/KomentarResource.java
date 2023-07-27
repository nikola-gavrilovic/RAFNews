package rs.raf.demo.resources;


import rs.raf.demo.entities.Komentar;
import rs.raf.demo.services.KomentarService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/komentari")
public class KomentarResource {

    @Inject
    private KomentarService komentarService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.komentarService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar create(@Valid Komentar komentar) {
        return this.komentarService.insert(komentar);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar find(@PathParam("id") Integer id) {
        return this.komentarService.findById(id);
    }

    

//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Integer id) {
//        this.postService.delete(id);
//    }

}
