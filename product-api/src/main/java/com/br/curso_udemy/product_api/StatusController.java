package com.br.curso_udemy.product_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping()
    public ResponseEntity<HashMap<String, Object>> getStatus() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("service", "product-api");
        response.put("status", "up");
        response.put("version", "1.0.0");
        response.put("httpStatus", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
