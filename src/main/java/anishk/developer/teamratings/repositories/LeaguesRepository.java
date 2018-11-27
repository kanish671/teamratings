package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Leagues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Integer> {
    Leagues findByLeagueId(Integer leagueId);
}
