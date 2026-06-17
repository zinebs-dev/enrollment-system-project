package com.demo.enrollement.clients;

import org.springframework.web.reactive.function.client.WebClient;

public class StudentClient {
	private final WebClient webClient ; 
	
	public StudentClient(WebClient.Builder builder ) {
		this.webClient = builder.baseUrl("http://localhost:8081").build();
	}
	
	/*public String getCnieById(Long id) {
		return this.webClient.get();
	}
	*/

}
