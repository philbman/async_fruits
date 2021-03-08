package org.acme.getting.started.async;

import io.vertx.core.json.JsonObject;


public class FruitBox {


    private JsonObject fruitA;
    private JsonObject fruitB;


    public FruitBox() {

    }

    public JsonObject getFruitA() {
        return fruitA;
    }

    public void setFruitA(JsonObject fruitA) {
        this.fruitA = fruitA;
    }

    public JsonObject getFruitB() {
        return fruitB;
    }

    public void setFruitB(JsonObject fruitB) {
        this.fruitB = fruitB;
    }
}
