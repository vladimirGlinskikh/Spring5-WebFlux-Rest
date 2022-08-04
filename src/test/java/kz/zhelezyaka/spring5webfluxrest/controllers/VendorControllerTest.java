package kz.zhelezyaka.spring5webfluxrest.controllers;

import kz.zhelezyaka.spring5webfluxrest.domain.Vendor;
import kz.zhelezyaka.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

public class VendorControllerTest {
    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder()
                        .firstName("Vladimir")
                        .lastName("Glinskikh")
                        .build()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(1);
    }

    @Test
    public void getById() {
        BDDMockito.given(vendorRepository.findById("myId"))
                .willReturn(Mono.just(Vendor.builder()
                        .firstName("Vladimir")
                        .lastName("Glinskikh")
                        .build()));

        webTestClient.get()
                .uri("/api/v1/vendors/myId")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void create() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> nameToSaveMono =
                Mono.just(Vendor.builder()
                        .firstName("Vladimir")
                        .lastName("Glinskikh")
                        .build());

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(nameToSaveMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void update() {
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> nameToUpdateMono =
                Mono.just(Vendor.builder()
                        .firstName("Vladimir")
                        .lastName("Vladimirov")
                        .build());

        webTestClient.put()
                .uri("/api/v1/vendors/Vladimir")
                .body(nameToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}