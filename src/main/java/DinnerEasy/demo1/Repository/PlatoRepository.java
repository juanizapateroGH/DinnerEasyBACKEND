package DinnerEasy.demo1.Repository;

import DinnerEasy.demo1.Entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato , Long> {
}
