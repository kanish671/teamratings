package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Long> {
    Matches findBySeasonIdAndLeagueIdAndTeamId(Integer seasonId, Integer leagueId, Integer teamId);
    Matches findByTeamIdAndFixtureDate(Integer teamId, Date fixtureDate);
}
