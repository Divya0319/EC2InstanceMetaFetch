package com.fastturtle.ec2instancemetafetch.services;


import com.fastturtle.ec2instancemetafetch.models.LeftRotateArrayRequest;
import com.fastturtle.ec2instancemetafetch.utils.LinkedList;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.Arrays;

@Service
public class LambdaService {

    private final LambdaClient lambdaClient;

    public LambdaService() {
        this.lambdaClient = LambdaClient.builder().build();
    }

    public String invokeNextGreaterPermutationLambdaFunction(int[] array) {
        String payload = "{\"arr\": " + Arrays.toString(array) + "}";
        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName("nextGreaterPermutation")
                .payload(SdkBytes.fromUtf8String(payload))
        .build();

        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        return response.payload().asUtf8String();
    }
    public String invokePrintCommonElementsInLinkedListFunction(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        String payload = "{"
                + "\"linkedList1\": " + l1.toString() + ","
                + "\"linkedList2\": " + l2.toString()
                + "}";

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName("printCommonElementsInLinkedLists")
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        return response.payload().asUtf8String();
    }

    public String invokeLeftRotateByDPlacesFunction(LeftRotateArrayRequest leftRotateArrayRequest) {
        String payload = "{"
                + "\"arr\": " + Arrays.toString(leftRotateArrayRequest.getArr()) + ","
                + "\"d\": " + leftRotateArrayRequest.getD()
                + "}";

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName("leftRotateArrayByDPlaces")
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        return response.payload().asUtf8String();
    }
}
