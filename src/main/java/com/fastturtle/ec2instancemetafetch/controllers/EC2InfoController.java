package com.fastturtle.ec2instancemetafetch.controllers;

import com.fastturtle.ec2instancemetafetch.services.EC2InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ec2")
public class EC2InfoController {

    private final EC2InfoService ec2InfoService;

    public EC2InfoController(EC2InfoService ec2InfoService) {
        this.ec2InfoService = ec2InfoService;
    }

    @GetMapping("/getInstanceDetails")
    public Map<String, String> getInstanceDetails() {

        Map<String, String> response = new HashMap<>();
        try {
            // Fetch token
            String token = ec2InfoService.getToken();

            // Get the instance ID and availability zone
            String instanceId = ec2InfoService.getMetadata("instance-id", token);
            String availabilityZone = ec2InfoService.getMetadata("placement/availability-zone", token);

            // Output the details
            response.put("instanceID", instanceId);
            response.put("availabilityZone", availabilityZone);

        } catch (IOException e) {
            response.put("error", e.getMessage());
        }

        return response;
    }


}
