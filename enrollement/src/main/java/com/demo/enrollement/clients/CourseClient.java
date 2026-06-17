package com.demo.enrollement.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CourseClient {
	private final WebClient webClient ; 
	
	public CourseClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://course").build();
    }
	
	public String getCourseById(Long id) {
		return this.webClient.get().uri("/api/course/" + id).retrieve().bodyToMono(String.class).block();
	}
	
	public String getCourseByTitle(String title) {
		return this.webClient.get().uri("/api/course/title/" + title).retrieve().bodyToMono(String.class).block();
	}
}
