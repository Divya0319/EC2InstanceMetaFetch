package com.fastturtle.ec2instancemetafetch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ec2")
public class EC2InfoController {

    private static final String METADATA_URL = "http://169.254.169.254/latest/meta-data/";

    @GetMapping("/getInstanceDetails")
    public Map<String, String> getInstanceDetails() {

        Map<String, String> response = new HashMap<>();
        try {
            // Get the instance ID
            String instanceId = getMetadata("instance-id");
            // Get the availability zone
            String availabilityZone = getMetadata("placement/availability-zone");

            // Output the details

            response.put("instanceID", instanceId);
            response.put("availabilityZone", availabilityZone);

        } catch (Exception e) {
            response.put("error", e.getMessage());
        }

        return response;
    }

    private static String getMetadata(String path) throws Exception {
        String fullUrl = METADATA_URL + path;
        URL url = new URL(fullUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }


}
