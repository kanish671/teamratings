package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Long> {
    Matches findBySeasonIdAndLeagueIdAndTeamId(Integer seasonId, Integer leagueId, Integer teamId);
    Matches findByTeamIdAndFixtureDate(Integer teamId, Date fixtureDate);
    Matches findByMatchId(Long matchId);
    List<Matches> findAllByTeamIdAndFixtureDateBetween(Integer teamId, Date startDate, Date endDate);
}
