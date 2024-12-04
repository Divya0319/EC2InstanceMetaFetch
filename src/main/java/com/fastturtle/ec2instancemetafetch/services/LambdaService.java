package com.fastturtle.ec2instancemetafetch.services;


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
}
