package com.github.maly7.greeter

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class GreeterController(
    @Value("\${SPRING_APPLICATION_NAME: name not set") val applicationName: String,
    val webClient: WebClient,
    val discoveryClient: ReactiveDiscoveryClient
) {

    @GetMapping("/service-instances/{applicationName}")
    fun serviceInstanceByAppName(@PathVariable applicationName: String): Flux<ServiceInstance> = discoveryClient.getInstances(applicationName)

    @GetMapping("/greeting/{applicationName}")
    fun getGreetingMessage(@PathVariable applicationName: String): Mono<String> {
        val uri = UriComponentsBuilder.fromUriString("/${applicationName}/message-for-eureka-client")
            .build()
            .toUri()

        return webClient.get().uri(uri).retrieve().bodyToMono(String::class.java)
    }

    @GetMapping("/message-for-eureka-client")
    fun generateGreetingMessage(): Mono<String> = Mono.just("Greeting from $applicationName")
}