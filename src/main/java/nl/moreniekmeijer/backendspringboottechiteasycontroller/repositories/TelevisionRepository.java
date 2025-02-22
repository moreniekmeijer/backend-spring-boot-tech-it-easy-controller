package nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findByBrand(String brand, Sort sort);
}
