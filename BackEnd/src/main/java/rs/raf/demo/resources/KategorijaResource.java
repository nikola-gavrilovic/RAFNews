package rs.raf.demo.resources;


import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.services.KategorijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kategorije")
public class KategorijaResource {
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
    private KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.kategorijaService.all()).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Kategorija kategorija) {
        return Response.ok(this.kategorijaService.insert(kategorija)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija find(@PathParam("id") Integer id) {
        return this.kategorijaService.findById(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija edit(@Valid Kategorija kategorija) {
        return this.kategorijaService.edit(kategorija);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.kategorijaService.delete(id);
    }

}
