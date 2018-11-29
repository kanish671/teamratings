package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Match, Long> {
    Match findBySeasonIdAndLeagueIdAndTeamId(Integer seasonId, Integer leagueId, Integer teamId);
    Match findByTeamIdAndFixtureDate(Integer teamId, Date fixtureDate);
    Match findByMatchId(Long matchId);
    List<Match> findAllByTeamIdAndFixtureDateBetween(Integer teamId, Date startDate, Date endDate);
    List<Match> findAllByRefereeIdAndFixtureDateBetween(Integer refereeId, Date startDate, Date endDate);
}
