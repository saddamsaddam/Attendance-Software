package org.example.spring;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LoginRepository extends JpaRepository<LoginData, Long> {
    // Add custom queries if needed
}