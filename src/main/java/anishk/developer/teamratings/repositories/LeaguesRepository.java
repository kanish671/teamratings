package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaguesRepository extends JpaRepository<League, Integer> {
    League findByLeagueId(Integer leagueId);
}
