package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Referees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereesRepository extends JpaRepository<Referees, Integer> {
    Referees findByRefereeId(Integer refereeId);
    List<Referees> findAllByLeagueId(Integer leagueId);
}
