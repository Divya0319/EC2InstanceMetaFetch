package com.fastturtle.ec2instancemetafetch.utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CustomLinkedListDeserializer implements JsonDeserializer<LinkedList<Integer>> {
    @Override
    public LinkedList<Integer> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        LinkedList<Integer> list = new LinkedList<>();

        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                list.insert(element.getAsInt());
            }
        } else {
            throw new JsonParseException("Expected JSON array but found " + json.getClass());
        }

        return list;
    }
}

