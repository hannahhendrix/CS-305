package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 




@SpringBootApplication
public class SslServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslServerApplication.class, args);
    }
}

@RestController
class ServerController {

    @RequestMapping("/hash")
    public String myHash() {
        try {
            String data = fetchData(); // Fetch data from a secure source or user input
            String hash = generateSHA256Hash(data);
            return "<p>Data: " + data + " : SHA-256 : " + hash + "</p>";
        } catch (NoSuchAlgorithmException e) {
            // Handle exception appropriately
            return "<p>Error generating hash</p>";
        }
    }

    private String fetchData() {
        // Example: Fetch data from a secure source or user input
        return "Hannah Hendrix 12/6/2023 CS-305: Project 2";
    }

    private String generateSHA256Hash(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}

