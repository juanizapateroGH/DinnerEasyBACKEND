package DinnerEasy.demo1.Repository;

import DinnerEasy.demo1.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu , Long> {
}
