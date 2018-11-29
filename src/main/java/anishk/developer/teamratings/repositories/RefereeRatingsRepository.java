package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.RefereeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeRatingsRepository extends JpaRepository<RefereeRating, Long> {
    List<RefereeRating> findAllByMatchId(Long matchId);
    List<RefereeRating> findAllByRefereeIdAndMatchId(Integer refereeId, Long matchId);
}
