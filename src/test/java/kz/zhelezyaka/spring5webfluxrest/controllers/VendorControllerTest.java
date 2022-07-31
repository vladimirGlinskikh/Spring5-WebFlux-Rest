package kz.zhelezyaka.spring5webfluxrest.controllers;

import kz.zhelezyaka.spring5webfluxrest.domain.Vendor;
import kz.zhelezyaka.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

public class VendorControllerTest {
    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {
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
}