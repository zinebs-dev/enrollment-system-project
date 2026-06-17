package com.demo.enrollement.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StudentClient {
	private final WebClient webClient ; 
	
	public StudentClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://student").build();
    }
	
	public String getStudentById(Long id) {
		return this.webClient.get().uri("/api/students/"+id).retrieve().bodyToMono(String.class).block();
	}
	
	public String getStudentByCnie(String cnie) {
	    try {
	        return this.webClient.get()
	                .uri("/api/students/cnie/" + cnie)
	                .retrieve()
	                .onStatus(status -> status.value() == 404, 
	                          response -> reactor.core.publisher.Mono.empty())
	                .bodyToMono(String.class)
	                .block();
	    } catch (Exception e) {
	        return null;
	    }
	}

}
