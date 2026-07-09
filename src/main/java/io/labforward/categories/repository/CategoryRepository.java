package io.labforward.categories.repository;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(value = "category-with-attributes")
    Optional<Category> findById(Long id);
}
