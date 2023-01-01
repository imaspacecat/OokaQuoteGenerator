package org.dubiner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mitchellbosecke.pebble.PebbleEngine;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinPebble;

import static io.javalin.apibuilder.ApiBuilder.get;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        QuoteParser parser = new QuoteParser();
        List<JsonObject> quotesList = parser.getQuotesList();
        Gson gson = new Gson();
        JavalinPebble.init();

        var app = Javalin.create()
                .start(7070);

        app.routes(() -> {
            get("/", ctx -> ctx.render("index.peb", Map.of("test", "asd")));
            get("/quotes", ctx -> ctx.json(gson.toJson(quotesList)));
            get("/quote", ctx -> ctx.json(gson.toJson(quotesList.get(
                    rand.nextInt(quotesList.size()-1)))));
        });
    }
}