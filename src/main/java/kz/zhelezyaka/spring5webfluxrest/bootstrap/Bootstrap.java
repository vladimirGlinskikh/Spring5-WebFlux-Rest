package kz.zhelezyaka.spring5webfluxrest.bootstrap;

import kz.zhelezyaka.spring5webfluxrest.domain.Category;
import kz.zhelezyaka.spring5webfluxrest.domain.Vendor;
import kz.zhelezyaka.spring5webfluxrest.repositories.CategoryRepository;
import kz.zhelezyaka.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count().block() == 0) {
            System.out.println("---- LOADING DATA ON BOOTSTRAP ----");

            categoryRepository.save(Category.builder()
                    .description("Fruits").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Breads").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Meats").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).block();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());

            vendorRepository.save(Vendor.builder()
                    .firstName("Vladimir")
                    .lastName("Glinskikh").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Svetlana")
                    .lastName("Azimova").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Elena")
                    .lastName("Mishkina").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Nikolay")
                    .lastName("Romanov").build()).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());
        }
    }
}
