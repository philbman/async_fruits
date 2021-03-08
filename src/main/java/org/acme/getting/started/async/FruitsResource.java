package org.acme.getting.started.async;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Uni;

@Path("/fruits")
public class FruitsResource {


    @Inject
    FruitsService fruitsService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> giveMeFruits() {
        return fruitsService.getFruits();
    }
}
