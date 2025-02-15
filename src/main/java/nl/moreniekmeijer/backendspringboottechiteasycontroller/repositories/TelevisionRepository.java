package nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    Optional<Television> findByName(String name);
}
