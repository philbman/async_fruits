package org.acme.getting.started.async;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.ext.web.client.WebClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;

@ApplicationScoped
public class FruitsClient {

    @Inject
    Vertx vertx;

    private WebClient client;

    @PostConstruct
    public void init() {
            client = WebClient.create(vertx,
            new WebClientOptions().setDefaultHost("fruityvice.com").setDefaultPort(443).setSsl(true)
                    .setTrustAll(true));
        }


    public Uni<JsonObject> getFruits (String name){
        return client.get("/api/fruit/" + name)
                        .send()
                        .onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject();
                    } else {
                        return new JsonObject()
                                .put("code", resp.statusCode())
                                .put("message", resp.bodyAsString());
                    }
                }).onFailure().transform(failure -> {
                   throw new InternalServerErrorException();
                });
    }

}
