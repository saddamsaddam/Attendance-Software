package org.example.spring.repository;

import org.example.spring.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    // You can add custom query methods here if needed
}
