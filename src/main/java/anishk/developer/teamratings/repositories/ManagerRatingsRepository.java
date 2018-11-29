package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.ManagerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRatingsRepository extends JpaRepository<ManagerRating, Long> {
    List<ManagerRating> findAllByManagerIdAndMatchId(Integer managerId, Long matchId);
}
