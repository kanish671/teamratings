package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.TeamRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRatingsRepository extends JpaRepository<TeamRatings, Long> {
    List<TeamRatings> findAllByMatchIdAndTeamId(Long matchId, Integer teamId);
}
