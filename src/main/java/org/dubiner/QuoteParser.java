package org.dubiner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class QuoteParser {
    private JsonElement json;
    private JsonObject quotes;
    private List<JsonObject> quotesList;

    public QuoteParser() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(
                "src/main/resources/quotes.json")) {
            json = gson.fromJson(reader, JsonElement.class);
            quotes = json.getAsJsonObject();
            quotesList = new ArrayList<>();

            for(int i = 0; i <= 56; i++){
                quotesList.add(quotes.get(""+i).getAsJsonObject());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<JsonObject> getQuotesList(){
        return quotesList;
    }
}
