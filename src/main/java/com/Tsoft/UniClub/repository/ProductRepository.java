package com.Tsoft.UniClub.repository;

import com.Tsoft.UniClub.entity.ProductEntity;
import com.Tsoft.UniClub.entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
