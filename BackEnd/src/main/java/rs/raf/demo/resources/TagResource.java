package rs.raf.demo.resources;


import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;
import rs.raf.demo.services.KategorijaService;
import rs.raf.demo.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tagovi")
public class TagResource {
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
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.tagService.all()).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) {
        return this.tagService.insert(tag);
    }

    @GET
    @Path("/{id}/vesti")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findVestiByTagId(@PathParam("id") int id) {

        return Response.ok(this.tagService.findVestiByTagId(id)).header("Access-Control-Allow-Origin", "*").build();
//        return this.tagService.findVestiByTagId(id);
    }

    

//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Integer id) {
//        this.postService.delete(id);
//    }

}
