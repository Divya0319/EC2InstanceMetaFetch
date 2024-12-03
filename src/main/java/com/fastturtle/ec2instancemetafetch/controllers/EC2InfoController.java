package com.fastturtle.ec2instancemetafetch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;

import java.io.BufferedReader;
import java.io.IOException;
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

            try (Ec2Client ec2Client = Ec2Client.create()) {
                String instanceName = getInstanceName(ec2Client, instanceId);

                response.put("instanceName", instanceName);
            } catch(Exception e) {
                response.put("error1", e.getMessage());
            }

            // Output the details

            response.put("instanceID", instanceId);
            response.put("availabilityZone", availabilityZone);

        } catch (IOException e) {
            response.put("error2", e.getMessage());
        }

        return response;
    }

    private static String getMetadata(String path) throws IOException {
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

    private static String getInstanceName(Ec2Client ec2Client, String instanceId) {
        // Describe instance to fetch its tags
        DescribeInstancesRequest request = DescribeInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        DescribeInstancesResponse response = ec2Client.describeInstances(request);

        // Extract the "Name" tag from the instance details
        return response.reservations().stream()
                .flatMap(reservation -> reservation.instances().stream())
                .filter(instance -> instance.instanceId().equals(instanceId))
                .flatMap(instance -> instance.tags().stream())
                .filter(tag -> tag.key().equals("Name"))
                .map(tag -> tag.value())
                .findFirst()
                .orElse("No Name Tag Found");
    }


}
