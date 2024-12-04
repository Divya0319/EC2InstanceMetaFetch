package com.fastturtle.ec2instancemetafetch.controllers;

import com.fastturtle.ec2instancemetafetch.models.LambdaRequest;
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

@RestController
public class InvokeLambdaController {

    @Autowired
    private LambdaService lambdaService;

    @PostMapping("/next-greater-permutation")
    public String getNextGreaterPermutation(@RequestBody int[] array) {
        return lambdaService.invokeNextGreaterPermutationLambdaFunction(array);
    }

    @PostMapping("/linked-list-common")
    public String printCommonElementsInLinkedList(HttpServletRequest request) {

        try (InputStream inputStream = request.getInputStream()) {
            // Use the fromJson method to deserialize the input stream
            LambdaRequest lambdaRequest = LinkedListDeserializer.fromJson(inputStream);
            LinkedList<Integer> linkedList1 = lambdaRequest.getLinkedList1();
            LinkedList<Integer> linkedList2 = lambdaRequest.getLinkedList2();
            return lambdaService.invokePrintCommonElementsInLinkedListFunction(linkedList1, linkedList2);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process input stream", e);
        }
    }

}
