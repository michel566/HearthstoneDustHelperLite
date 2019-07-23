package br.com.michelbarbosa.hearthstonedusthelperlite.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.CLogger;

public class CardsDeserializer implements JsonDeserializer<List<Card>> {
    @Override
    public List<Card> deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<List<Card>>() {}.getType();

        List<Card> cards = null;
        try {
            cards = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            CLogger.e(e);
        }
        return cards;
    }
}
