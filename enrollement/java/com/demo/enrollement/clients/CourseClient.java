package com.demo.enrollement.clients;

import org.springframework.web.reactive.function.client.WebClient;

public class CourseClient {
	private final WebClient webClient ; 
	
	public CourseClient(WebClient.Builder builder ) {
		this.webClient = builder.baseUrl("http://localhost:8082").build();
	}
	/*
	public String getById(Long id) {
		return this.webClient.get();
	}*/
}
