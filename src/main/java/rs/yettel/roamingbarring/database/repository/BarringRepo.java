package rs.yettel.roamingbarring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.yettel.roamingbarring.database.entity.Barring;

@Repository
public interface BarringRepo extends JpaRepository<Barring, Integer> {
}
