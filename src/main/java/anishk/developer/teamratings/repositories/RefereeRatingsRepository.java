package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.RefereeRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeRatingsRepository extends JpaRepository<RefereeRatings, Long> {
    List<RefereeRatings> findAllByMatchId(Long matchId);
    List<RefereeRatings> findAllByRefereeId(Integer refereeId);
}
