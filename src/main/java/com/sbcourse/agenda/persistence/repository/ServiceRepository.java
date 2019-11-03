package com.sbcourse.agenda.persistence.repository;

import com.sbcourse.agenda.persistence.model.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ProductService, Long> {
}
