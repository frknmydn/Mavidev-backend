package Repository;
import java.util.List;

import model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {
    List<Data> findByCityContaining(String city);
}
