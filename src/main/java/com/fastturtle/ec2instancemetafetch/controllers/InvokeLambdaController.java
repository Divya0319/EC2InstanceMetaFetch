package com.fastturtle.ec2instancemetafetch.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastturtle.ec2instancemetafetch.models.CommonElementsInLinkedListsRequest;
import com.fastturtle.ec2instancemetafetch.models.LeftRotateArrayRequest;
import com.fastturtle.ec2instancemetafetch.services.LambdaService;
import com.fastturtle.ec2instancemetafetch.utils.LinkedList;
import com.fastturtle.ec2instancemetafetch.utils.LinkedListDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class InvokeLambdaController {

    @Autowired
    private LambdaService lambdaService;

    @PostMapping("/next-greater-permutation")
    public List<Integer> getNextGreaterPermutation(@RequestBody int[] array) {
        String lambdaResponse = lambdaService.invokeNextGreaterPermutationLambdaFunction(array);
        if (lambdaResponse.startsWith("\"") && lambdaResponse.endsWith("\"")) {
            lambdaResponse = lambdaResponse.substring(1, lambdaResponse.length() - 1);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Integer> result = objectMapper.readValue(lambdaResponse, List.class);
//            for(Integer num : result) {
//                System.out.println(num);
//
//            }
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/linked-list-common")
    public String printCommonElementsInLinkedList(HttpServletRequest request) {

        try (InputStream inputStream = request.getInputStream()) {
            // Use the fromJson method to deserialize the input stream
            CommonElementsInLinkedListsRequest commonElementsInLinkedListsRequest = LinkedListDeserializer.fromJson(inputStream);
            LinkedList<Integer> linkedList1 = commonElementsInLinkedListsRequest.getLinkedList1();
            LinkedList<Integer> linkedList2 = commonElementsInLinkedListsRequest.getLinkedList2();
            return lambdaService.invokePrintCommonElementsInLinkedListFunction(linkedList1, linkedList2);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process input stream", e);
        }
    }

    @PostMapping("/leftRotateByDPlaces")
    public List<Integer> printLeftRotateByDPlaces(@RequestBody LeftRotateArrayRequest leftRotateArrayRequest) {
        String lambdaResponse = lambdaService.invokeLeftRotateByDPlacesFunction(leftRotateArrayRequest);

        if (lambdaResponse.startsWith("\"") && lambdaResponse.endsWith("\"")) {
            lambdaResponse = lambdaResponse.substring(1, lambdaResponse.length() - 1);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Integer> result = objectMapper.readValue(lambdaResponse, List.class);
//            for(Integer num : result) {
//                System.out.println(num);
//
//            }
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
