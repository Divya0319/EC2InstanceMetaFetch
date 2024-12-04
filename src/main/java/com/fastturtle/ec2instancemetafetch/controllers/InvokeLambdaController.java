package com.fastturtle.ec2instancemetafetch.controllers;

import com.fastturtle.ec2instancemetafetch.services.LambdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokeLambdaController {

    @Autowired
    private LambdaService lambdaService;

    @PostMapping("/next-greater-permutation")
    public String getNextGreaterPermutation(@RequestBody int[] array) {
        return lambdaService.invokeNextGreaterPermutationLambdaFunction(array);
    }

}
