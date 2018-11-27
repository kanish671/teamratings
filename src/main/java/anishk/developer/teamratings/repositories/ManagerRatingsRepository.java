package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.ManagerRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRatingsRepository extends JpaRepository<ManagerRatings, Long> {
    List<ManagerRatings> findAllByMatchId(Long matchId);
    List<ManagerRatings> findAllByManagerId(Integer managerId);
}
