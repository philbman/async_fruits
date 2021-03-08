package org.acme.getting.started.async;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class FruitsService {

    @Inject
    FruitsClient fruitsClient;

    @Inject
    GreetingService greetingService;

    public Uni<Response> getFruits() {
        Uni<JsonObject> apple = fruitsClient.getFruits("apple");
        Uni<JsonObject> banana = fruitsClient.getFruits("banana");

        return Uni.combine().all().unis(apple, banana)
                .combinedWith(tuple -> {

                    FruitBox fruitBox = new FruitBox();
                    fruitBox.setFruitA((JsonObject) tuple.get(0));
                    fruitBox.setFruitB((JsonObject) tuple.get(1));

                    greetingService.foo();

                    return fruitBox;
                })
                .onItem().transform(fruitBox -> Response.ok(fruitBox).build())
                .onFailure().recoverWithItem(Response.serverError().build());

    }
}
