package org.example.orderservice.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplate {
    @Bean
    @LoadBalanced // Sử dụng load balancer của Spring Cloud
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
