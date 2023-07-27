package rs.raf.demo.resources;


import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Korisnik;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.response.LoginResponse;
import rs.raf.demo.services.KategorijaService;
import rs.raf.demo.services.KorisniciService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
@Path("/korisnici")
public class KorisniciResource {

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
    private KorisniciService korisniciService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.korisniciService.all()).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik create(@Valid Korisnik korisnik) {
        return this.korisniciService.insert(korisnik);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik find(@PathParam("id") Integer id) {
        return this.korisniciService.findById(id);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        LoginResponse loginResponse = this.korisniciService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginResponse == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").header("Access-Control-Allow-Origin", "*").entity(response).build();
        }

        response.put("jwt", loginResponse.getJwt());
        response.put("imeprezime", loginResponse.getImePrezime());
        response.put("role", loginResponse.getRole());

        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers",
//                        "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods",
//                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(response)
                .build();
    }

    @PUT
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response aktivirajDeaktiviraj(@Valid Korisnik korisnik) {
        return Response.ok(this.korisniciService.aktivirajDeaktiviraj(korisnik)).header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik edit(@Valid Korisnik korisnik) {
        return this.korisniciService.edit(korisnik);
    }
    

//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Integer id) {
//        this.postService.delete(id);
//    }

}
