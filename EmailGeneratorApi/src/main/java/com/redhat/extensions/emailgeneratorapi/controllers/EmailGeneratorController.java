package com.redhat.extensions.emailgeneratorapi.controllers;


import com.redhat.extensions.emailgeneratorapi.models.EmailRequest;
import com.redhat.extensions.emailgeneratorapi.services.EmailGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }
    

    //send generation request
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        String response = emailGeneratorService.generateEmailResponse(emailRequest);
        return ResponseEntity.ok().body(response);
    }
}
