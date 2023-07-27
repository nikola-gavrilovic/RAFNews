package rs.raf.demo.resources;


import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Vest;
import rs.raf.demo.services.KomentarService;
import rs.raf.demo.services.VestService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vesti")
public class VestResource {

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }

    @Inject
    private VestService vestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.vestService.all()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/najcitanije")
    @Produces(MediaType.APPLICATION_JSON)
    public Response najcitanijeVesti() {
        return Response.ok(this.vestService.najcitanijeVesti()).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest create(@Valid Vest vest) {
        return this.vestService.insert(vest);
    }
//    public Vest create(@Valid Vest vest, int kategorijaId) {
//        return this.vestService.insert(vest, kategorijaId);
//    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.vestService.findById(id)).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/add-comment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest addComment(Komentar komentar,@PathParam("id") int id) {
        return this.vestService.addKomentar(komentar,id);
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        this.vestService.delete(id);
        return Response.ok(this.vestService.all()).header("Access-Control-Allow-Origin", "*").build();
    }

}
