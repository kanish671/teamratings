package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereesRepository extends JpaRepository<Referee, Integer> {
    Referee findByRefereeId(Integer refereeId);
    List<Referee> findAllByLeagueId(Integer leagueId);
}
