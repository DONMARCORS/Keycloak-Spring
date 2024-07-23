package com.keycloak.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.api.service.LoadBalancerService;

@RestController
@RequestMapping
@PreAuthorize("hasRole('admin_client_role')")
public class LoadBalancerController {
    
    @Autowired
    private LoadBalancerService loadBalancerService;

    @GetMapping("/request")
    public String handleRequest(){
        String endpoint = loadBalancerService.getEndpoint();

        if (loadBalancerService.isRequestAllowed(endpoint)) {
            return "Request sent to " + endpoint;
        } else {
            return "Rate limit exceeded for " + endpoint;
        }
    }

    @GetMapping("/hola")
    public String main(){
        return "Hola";
    }
}
