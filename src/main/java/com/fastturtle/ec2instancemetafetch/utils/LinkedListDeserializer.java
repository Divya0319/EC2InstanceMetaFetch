package com.fastturtle.ec2instancemetafetch.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fastturtle.ec2instancemetafetch.models.CommonElementsInLinkedListsRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LinkedListDeserializer {
	
	private static final Gson gson;
	
	static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LinkedList.class, new CustomLinkedListDeserializer());
        gson = gsonBuilder.create();
    }

	
	// Factory method for deserialization
    public static CommonElementsInLinkedListsRequest fromJson(InputStream json) {
        return gson.fromJson(new InputStreamReader(json), CommonElementsInLinkedListsRequest.class);
    }
    
    public static CommonElementsInLinkedListsRequest fromJson(JsonObject json) {
        return gson.fromJson(json, CommonElementsInLinkedListsRequest.class);
    }
    
    public static void main(String[] args) throws IOException {
        // Example JSON input
//    	String json = "{\"linkedList1\": [1, 3, 8], \"linkedList2\": [3, 6, 8, 12]}";
    	JsonObject jObj = new JsonObject();
    	JsonArray jArr1 = new JsonArray();
    	JsonArray jArr2 = new JsonArray();
    	jArr1.add(1);
    	jArr1.add(3);
    	jArr1.add(8);
    	jArr2.add(3);
    	jArr2.add(6);
    	jArr2.add(8);
    	jArr2.add(12);
    	jObj.add("linkedList1", jArr1);
    	jObj.add("linkedList2", jArr2);
    	
    	

        // Deserialize JSON input using factory method
        CommonElementsInLinkedListsRequest request = fromJson(jObj);

        // Print deserialized object
        System.out.println("LinkedList1: " + request.getLinkedList1());
        System.out.println("LinkedList2: " + request.getLinkedList2());
    }

}
