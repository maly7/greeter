package com.github.maly7.greeter

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class GreeterConfig {

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient = webClientBuilder.build()

    @Bean
    @LoadBalanced
    fun webClientBuilder(): WebClient.Builder = WebClient.builder()
}