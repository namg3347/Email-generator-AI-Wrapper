package com.redhat.extensions.emailgeneratorapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.extensions.emailgeneratorapi.models.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class EmailGeneratorService {

    private final WebClient webClient;
    private final String apiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder,
                                 @Value("${gemini.api.url}") String baseUrl,
                                 @Value("${gemini.api.key}") String api) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = api;
    }


    public String generateEmailResponse(EmailRequest emailRequest) {
        //generate prompt
        String prompt = generatePrompt(emailRequest);
        //raw json apiModel
        String requestBody = String.format("""
                {
                    "contents": [
                      {
                        "parts": [
                          {
                            "text": "%s"
                          }
                        ]
                      }
                    ]
                  }""", prompt);
        //sendRequest
        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v1beta/models/gemini-2.0-flash:generateContent")
                                .build())
                .header("Content-Type","application/json")
                .header("X-goog-api-key",apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        //extract response
        return extractResponse(response);
    }

    private String extractResponse(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generatePrompt(EmailRequest emailRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Write an email response based on the provided details. ");

        if (emailRequest.getTone() != null && !emailRequest.getTone().isBlank()) {
            sb.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        if (emailRequest.getRemarks() != null && !emailRequest.getRemarks().isBlank()) {
            sb.append("Consider these additional remarks while writing: ")
                    .append(emailRequest.getRemarks()).append(". ");
        }
        if(emailRequest.getEmailContent() != null)
        {
            sb.append("the email is : \n");
            sb.append(emailRequest.getEmailContent());
        }
        else {
            sb.append(" ");
        }
       return sb.toString();
    }
}
