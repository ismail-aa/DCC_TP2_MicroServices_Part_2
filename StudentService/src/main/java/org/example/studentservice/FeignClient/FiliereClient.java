package org.example.studentservice.FeignClient;

import org.example.studentservice.dto.Filiere;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "Filiere-Service",
        url = "http://localhost:8082/v1/filieres" // URL de  microservice filiere
)
public interface FiliereClient {

    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable("id") Integer id);

}