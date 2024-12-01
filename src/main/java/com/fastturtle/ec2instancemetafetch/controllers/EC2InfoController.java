package com.fastturtle.ec2instancemetafetch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EC2InfoController {

    @GetMapping("/getIp")
    public Map<String, String> getEC2InstanceDetails() {
        Map<String, String> response = new HashMap<>();

        response.put("welcomeMessage", "Welcome to EC2 instance");

        String ip;

        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            response.put("ipAddressOfMachine", ip);
        } catch(SocketException | UnknownHostException e) {
            response.put("error", e.getMessage());
        }
        return response;
    }


}
