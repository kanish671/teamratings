package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.TeamRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRatingsRepository extends JpaRepository<TeamRating, Long> {
    List<TeamRating> findAllByTeamIdAndMatchId(Integer teamId, Long matchId)    ;
}
