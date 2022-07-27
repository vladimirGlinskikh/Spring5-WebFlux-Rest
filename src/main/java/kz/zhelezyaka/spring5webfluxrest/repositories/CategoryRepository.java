package kz.zhelezyaka.spring5webfluxrest.repositories;

import kz.zhelezyaka.spring5webfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
