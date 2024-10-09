package com.test.eurekatest.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class DogControllerTest {
    private DiscoveryClient discoveryClient;
    private RestClient restClient;

    public DogControllerTest(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();

        ServiceInstance serviceInstance = discoveryClient.getInstances("EUREKACLIENT").get(0);
        String serviceResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/dogs")
                .retrieve()
                .body(String.class);

        System.out.println(serviceResponse);
    }
}
